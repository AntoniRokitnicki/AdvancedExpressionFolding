package com.intellij.advancedExpressionFolding.processor.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ElvisExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.IfExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ShortElvisExpression
import com.intellij.advancedExpressionFolding.expression.literal.InterpolatedString
import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.expression.math.basic.Add
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.BinaryExpressionExt
import com.intellij.advancedExpressionFolding.processor.language.kotlin.IfNullSafeExt
import com.intellij.advancedExpressionFolding.processor.language.kotlin.LetReturnExt
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import java.util.*
import java.util.stream.Collectors

object IfExt {

    fun getSwitchStatement(element: PsiSwitchStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        return if (element.expression != null && element.lParenth != null && element.rParenth != null &&
            settings.state.compactControlFlowSyntaxCollapse
        ) {
            CompactControlFlowExpression(
                element,
                TextRange.create(
                    element.lParenth!!.textRange.startOffset,
                    element.rParenth!!.textRange.endOffset
                )
            )
        } else null
    }

    fun getIfExpression(element: PsiIfStatement, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val getIfExpression = LetReturnExt.getIfExpression(element)
        if (getIfExpression != null) {
            return getIfExpression
        }
        if (settings.state.checkExpressionsCollapse && element.condition is PsiBinaryExpression) {
            val condition = element.condition as PsiBinaryExpression
            if (condition.operationSign.text == "!=" && element.elseBranch == null &&
                (BaseExtension.isNull(condition.lOperand.type) && condition.rOperand != null ||
                    condition.rOperand != null && BaseExtension.isNull(condition.rOperand!!.type)) &&
                element.thenBranch != null
            ) {
                var thenStatement = element.thenBranch
                if (thenStatement!!.children.size == 1 && thenStatement.children[0] is PsiCodeBlock) {
                    val statements = (thenStatement.children[0] as PsiCodeBlock).statements
                    thenStatement = if (statements.size == 1) {
                        statements[0]
                    } else {
                        return null
                    }
                }
                val qualifier = if (BaseExtension.isNull(condition.lOperand.type)) {
                    condition.rOperand
                } else {
                    condition.lOperand
                }
                if (qualifier is PsiReferenceExpression || qualifier is PsiMethodCallExpression &&
                    Helper.startsWith(
                        (qualifier as PsiMethodCallExpression).methodExpression.referenceName,
                        "get"
                    ) && qualifier.argumentList.expressions.isEmpty()
                ) {
                    val r = Helper.findSameQualifier(thenStatement, qualifier)
                    if (r != null) {
                        return ShortElvisExpression(
                            element,
                            element.textRange,
                            BuildExpressionExt.getAnyExpression(thenStatement, document),
                            listOf(r.textRange)
                        )
                    }
                }
            }
        }
        return IfExpression(element, element.textRange)
    }

    fun getConditionalExpression(element: PsiConditionalExpression, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (settings.state.checkExpressionsCollapse && element.condition is PsiBinaryExpression) {
            val condition = element.condition as PsiBinaryExpression
            if (condition.operationSign.text == "!=" && condition.rOperand != null &&
                (BaseExtension.isNull(condition.lOperand.type) || BaseExtension.isNull(condition.rOperand!!.type)) &&
                element.thenExpression != null && element.elseExpression != null
            ) {
                val qualifier = if (BaseExtension.isNull(condition.lOperand.type)) {
                    condition.rOperand
                } else {
                    condition.lOperand
                }
                if (qualifier is PsiReferenceExpression || qualifier is PsiMethodCallExpression &&
                    Helper.startsWith(
                        (qualifier as PsiMethodCallExpression).methodExpression.referenceName,
                        "get"
                    ) && qualifier.argumentList.expressions.isEmpty()
                ) {
                    val r = if (qualifier is PsiReferenceExpression) qualifier else (qualifier as PsiMethodCallExpression).methodExpression
                    val references = SyntaxTraverser.psiTraverser(element.thenExpression)
                        .filter { e: PsiElement ->
                            e is PsiReferenceExpression && e.parent !is PsiMethodCallExpression && Helper.isReferenceToReference(e, r) ||
                                e is PsiMethodCallExpression && Helper.isReferenceToReference(e.methodExpression, r)
                        }.toList()
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
        var add = true
        var string = false
        var operands: Array<Expression?>? = null
        for (i in 0 until element.operands.size - 1) {
            val a = element.operands[i]
            val b = element.operands[i + 1]
            val token = element.getTokenBeforeOperand(b)
            if (token != null) {
                if ("&&" == token.text && a is PsiBinaryExpression && b is PsiBinaryExpression) {
                    val twoBinaryExpression = BinaryExpressionExt.getAndTwoBinaryExpressions(
                        element,
                        a,
                        b,
                        document
                    )
                    if (twoBinaryExpression != null) {
                        return twoBinaryExpression
                    }
                }
                if (add && "+" == token.text) {
                    if (operands == null) {
                        operands = arrayOfNulls(element.operands.size)
                    }
                    operands[i] = BuildExpressionExt.getAnyExpression(element.operands[i], document)
                    if (operands[i] is StringLiteral) {
                        string = true
                    }
                } else {
                    add = false
                }
            }
        }
        if (add && operands != null) {
            operands[element.operands.size - 1] = BuildExpressionExt.getAnyExpression(
                element.operands[element.operands.size - 1],
                document
            )
            if (operands[element.operands.size - 1] is StringLiteral) {
                string = true
            }
        }
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (add && operands != null && string && settings.state.concatenationExpressionsCollapse) {
            return InterpolatedString(element, element.textRange, operands.asList() as List<Expression>)
        } else if (add && operands != null) {
            return Add(
                element,
                element.textRange,
                operands.asList() as List<Expression>
            )
        }
        if (element is PsiBinaryExpression) {
            val binaryExpression = BinaryExpressionExt.getBinaryExpression(element, document)
            if (binaryExpression != null) {
                return binaryExpression
            }
        }
        val expression = IfNullSafeExt.createExpression(element, document)
        return expression
    }
}

