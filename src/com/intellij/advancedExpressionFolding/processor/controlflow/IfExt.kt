package com.intellij.advancedExpressionFolding.processor.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ElvisExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.IfExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ShortElvisExpression
import com.intellij.advancedExpressionFolding.expression.literal.InterpolatedString
import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.expression.math.basic.Add
import com.intellij.advancedExpressionFolding.processor.argumentExpressions
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.BinaryExpressionExt
import com.intellij.advancedExpressionFolding.processor.isNull
import com.intellij.advancedExpressionFolding.processor.language.kotlin.IfNullSafeExt
import com.intellij.advancedExpressionFolding.processor.language.kotlin.LetReturnExt
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiConditionalExpression
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiPolyadicExpression
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.PsiStatement
import com.intellij.psi.PsiSwitchStatement
import com.intellij.psi.SyntaxTraverser

object IfExt {

    fun getSwitchStatement(element: PsiSwitchStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val lParenth = element.lParenth ?: return null
        val rParenth = element.rParenth ?: return null
        return if (element.expression != null && settings.state.compactControlFlowSyntaxCollapse) {
            CompactControlFlowExpression(
                element,
                TextRange.create(lParenth.textRange.startOffset, rParenth.textRange.endOffset)
            )
        } else {
            null
        }
    }

    fun getIfExpression(element: PsiIfStatement, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        LetReturnExt.getIfExpression(element)?.let { return it }

        val condition = element.condition
        if (settings.state.checkExpressionsCollapse && condition is PsiBinaryExpression) {
            if (condition.operationSign.text == "!=" && element.elseBranch == null && element.thenBranch != null) {
                val lNull = isNull(condition.lOperand.type)
                val rNull = isNull(condition.rOperand?.type)
                if ((lNull && condition.rOperand != null) || (condition.rOperand != null && rNull)) {
                    var thenStatement: PsiStatement? = element.thenBranch
                    if (thenStatement != null && thenStatement.children.size == 1 && thenStatement.children[0] is PsiCodeBlock) {
                        val statements = (thenStatement.children[0] as PsiCodeBlock).statements
                        thenStatement = if (statements.size == 1) statements[0] else return null
                    }
                    val targetStatement = thenStatement ?: return null
                    val qualifierElement = (if (lNull) condition.rOperand else condition.lOperand) ?: return IfExpression(
                        element,
                        element.textRange
                    )
                    val isSupportedQualifier = when (qualifierElement) {
                        is PsiReferenceExpression -> true
                        is PsiMethodCallExpression ->
                            Helper.startsWith(qualifierElement.methodExpression.referenceName, "get") &&
                                qualifierElement.argumentExpressions.isEmpty()
                        else -> false
                    }
                    if (isSupportedQualifier) {
                        val sameQualifier = Helper.findSameQualifier(targetStatement, qualifierElement)
                        if (sameQualifier != null) {
                            return ShortElvisExpression(
                                element,
                                element.textRange,
                                BuildExpressionExt.getAnyExpression(targetStatement, document),
                                listOf(sameQualifier.textRange)
                            )
                        }
                    }
                }
            }
        }
        return IfExpression(element, element.textRange)
    }

    fun getConditionalExpression(element: PsiConditionalExpression, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val condition = element.condition
        if (settings.state.checkExpressionsCollapse && condition is PsiBinaryExpression) {
            if (condition.operationSign.text == "!=" && condition.rOperand != null &&
                (isNull(condition.lOperand.type) || isNull(condition.rOperand?.type)) &&
                element.thenExpression != null && element.elseExpression != null
            ) {
                val qualifier = if (isNull(condition.lOperand.type)) {
                    condition.rOperand
                } else {
                    condition.lOperand
                }
                val qualifierElement = qualifier ?: return null
                val isSupportedQualifier = when (qualifierElement) {
                    is PsiReferenceExpression -> true
                    is PsiMethodCallExpression ->
                        Helper.startsWith(qualifierElement.methodExpression.referenceName, "get") &&
                            qualifierElement.argumentExpressions.isEmpty()
                    else -> false
                }
                if (isSupportedQualifier) {
                    val reference: PsiReference? = when (qualifierElement) {
                        is PsiReferenceExpression -> qualifierElement
                        is PsiMethodCallExpression -> qualifierElement.methodExpression
                        else -> return null
                    }
                    val references = SyntaxTraverser.psiTraverser(element.thenExpression)
                        .filter { candidate ->
                            when (candidate) {
                                is PsiReferenceExpression -> candidate.parent !is PsiMethodCallExpression &&
                                    Helper.isReferenceToReference(candidate, reference)
                                is PsiMethodCallExpression ->
                                    Helper.isReferenceToReference(candidate.methodExpression, reference)
                                else -> false
                            }
                        }
                        .toList()
                    if (references.isNotEmpty()) {
                        return ElvisExpression(
                            element,
                            element.textRange,
                            BuildExpressionExt.getAnyExpression(element.thenExpression!!, document),
                            BuildExpressionExt.getAnyExpression(element.elseExpression!!, document),
                            references.map { it.textRange }
                        )
                    }
                }
            }
        }
        return null
    }

    fun getPolyadicExpression(element: PsiPolyadicExpression, document: Document): Expression? {
        getAndTwoBinaryExpressions(element, document)?.let { return it }
        getAddExpression(element, document)?.let { return it }
        getBinaryExpression(element, document)?.let { return it }
        return getNullSafeExpression(element, document)
    }

    private fun getAndTwoBinaryExpressions(
        element: PsiPolyadicExpression,
        document: Document
    ): Expression? {
        val operands = element.operands
        for (i in 0 until operands.size - 1) {
            val a = operands[i]
            val b = operands[i + 1]
            val token = element.getTokenBeforeOperand(b)
            if (token != null && token.text == "&&" && a is PsiBinaryExpression && b is PsiBinaryExpression) {
                val expression = BinaryExpressionExt.getAndTwoBinaryExpressions(element, a, b, document)
                if (expression != null) {
                    return expression
                }
            }
        }
        return null
    }

    private fun getAddExpression(element: PsiPolyadicExpression, document: Document): Expression? {
        val psiOperands = element.operands
        var add = true
        var hasString = false
        var operands: Array<Expression?>? = null
        for (i in 0 until psiOperands.size - 1) {
            val token = element.getTokenBeforeOperand(psiOperands[i + 1])
            if (token != null && token.text == "+" && add) {
                if (operands == null) {
                    operands = arrayOfNulls(psiOperands.size)
                }
                val expr = BuildExpressionExt.getAnyExpression(psiOperands[i], document)
                operands[i] = expr
                if (expr is StringLiteral) {
                    hasString = true
                }
            } else {
                add = false
            }
        }
        if (add && operands != null) {
            val lastIndex = psiOperands.size - 1
            val lastExpr = BuildExpressionExt.getAnyExpression(psiOperands[lastIndex], document)
            operands[lastIndex] = lastExpr
            if (lastExpr is StringLiteral) {
                hasString = true
            }
            val operandList = operands.mapNotNull { it }
            val settings = AdvancedExpressionFoldingSettings.getInstance()
            if (hasString && settings.state.concatenationExpressionsCollapse) {
                return InterpolatedString(element, element.textRange, operandList)
            }
            return Add(element, element.textRange, operandList)
        }
        return null
    }

    private fun getBinaryExpression(element: PsiPolyadicExpression, document: Document): Expression? {
        return if (element is PsiBinaryExpression) {
            BinaryExpressionExt.getBinaryExpression(element, document)
        } else {
            null
        }
    }

    private fun getNullSafeExpression(element: PsiPolyadicExpression, document: Document): Expression? {
        return IfNullSafeExt.createExpression(element, document)
    }
}
