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
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression
import com.intellij.advancedExpressionFolding.processor.expression.BinaryExpressionExt
import com.intellij.advancedExpressionFolding.processor.isNull
import com.intellij.advancedExpressionFolding.processor.language.kotlin.IfNullSafeExt
import com.intellij.advancedExpressionFolding.processor.language.kotlin.IfNullSafePrintlnExt
import com.intellij.advancedExpressionFolding.processor.language.kotlin.LetReturnExt
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IControlFlowState
import com.intellij.advancedExpressionFolding.settings.state.IExpressionCollapseState
import com.intellij.advancedExpressionFolding.settings.state.IKotlinLanguageState
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

object IfExt :
    IControlFlowState by State()(),
    IExpressionCollapseState by State()(),
    IKotlinLanguageState by State()() {

    fun getSwitchStatement(element: PsiSwitchStatement): Expression? {
        val lParenth = element.lParenth ?: return null
        val rParenth = element.rParenth ?: return null
        return if (element.expression != null && compactControlFlowSyntaxCollapse) {
            CompactControlFlowExpression(
                element,
                TextRange.create(lParenth.textRange.startOffset, rParenth.textRange.endOffset)
            )
        } else {
            null
        }
    }

    fun getIfExpression(element: PsiIfStatement, document: Document): Expression? {
        LetReturnExt.getIfExpression(element)?.let { return it }

        IfNullSafePrintlnExt.createExpression(element)?.let { return it }

        val condition = element.condition
        if (checkExpressionsCollapse) {
            fun isSupportedQualifier(qualifierElement: PsiElement?): Boolean {
                return when (qualifierElement) {
                    is PsiReferenceExpression -> true
                    is PsiMethodCallExpression ->
                        Helper.startsWith(qualifierElement.methodExpression.referenceName, "get") &&
                            qualifierElement.argumentExpressions.isEmpty()
                    else -> false
                }
            }

            fun unwrapThenBranch(statement: PsiStatement?): PsiStatement? {
                var current = statement ?: return null
                if (current.children.size == 1 && current.children[0] is PsiCodeBlock) {
                    val statements = (current.children[0] as PsiCodeBlock).statements
                    current = if (statements.size == 1) statements[0] else return null
                }
                return current
            }

            fun qualifierBase(qualifierElement: PsiElement): PsiElement? {
                return when (qualifierElement) {
                    is PsiMethodCallExpression -> qualifierElement.methodExpression.qualifierExpression
                    is PsiReferenceExpression -> qualifierElement.qualifierExpression
                    else -> null
                }
            }

            fun flattenGuardExpressions(condition: PsiExpression?): List<PsiBinaryExpression>? {
                return when (condition) {
                    is PsiBinaryExpression -> {
                        when (condition.operationSign.text) {
                            "&&" -> {
                                val left = flattenGuardExpressions(condition.lOperand) ?: return null
                                val right = flattenGuardExpressions(condition.rOperand) ?: return null
                                left + right
                            }
                            "!=" -> listOf(condition)
                            else -> null
                        }
                    }

                    is PsiPolyadicExpression -> {
                        if (condition.operands.isEmpty()) {
                            return null
                        }
                        for (index in 1 until condition.operands.size) {
                            val token = condition.getTokenBeforeOperand(condition.operands[index])?.text
                            if (token != "&&") {
                                return null
                            }
                        }
                        val result = mutableListOf<PsiBinaryExpression>()
                        for (operand in condition.operands) {
                            val guards = flattenGuardExpressions(operand) ?: return null
                            result += guards
                        }
                        result
                    }

                    else -> null
                }
            }

            fun collectGuardedQualifiers(condition: PsiExpression): List<PsiElement>? {
                val guardExpressions = flattenGuardExpressions(condition) ?: return null
                if (guardExpressions.size < 2) {
                    return null
                }
                val qualifiers = mutableListOf<PsiElement>()
                for ((index, guard) in guardExpressions.withIndex()) {
                    if (guard.operationSign.text != "!=") {
                        return null
                    }
                    val lNull = isNull(guard.lOperand.type)
                    val rNull = isNull(guard.rOperand?.type)
                    val qualifierElement = when {
                        lNull -> guard.rOperand
                        rNull -> guard.lOperand
                        else -> null
                    } ?: return null
                    if (!isSupportedQualifier(qualifierElement)) {
                        return null
                    }
                    val baseQualifier = qualifierBase(qualifierElement)
                    if (index == 0) {
                        if (baseQualifier != null) {
                            return null
                        }
                    } else {
                        val previousQualifier = qualifiers.last()
                        if (baseQualifier == null || !Helper.equal(baseQualifier, previousQualifier)) {
                            return null
                        }
                    }
                    qualifiers += qualifierElement
                }
                return qualifiers
            }

            when (condition) {
                is PsiBinaryExpression -> {
                    if (condition.operationSign.text == "!=" && element.elseBranch == null && element.thenBranch != null) {
                        val lNull = isNull(condition.lOperand.type)
                        val rNull = isNull(condition.rOperand?.type)
                        if ((lNull && condition.rOperand != null) || (condition.rOperand != null && rNull)) {
                            val targetStatement = unwrapThenBranch(element.thenBranch) ?: return null
                            val qualifierElement = (if (lNull) condition.rOperand else condition.lOperand) ?: return IfExpression(
                                element,
                                element.textRange
                            )
                            if (isSupportedQualifier(qualifierElement)) {
                                val sameQualifier = Helper.findSameQualifier(targetStatement, qualifierElement)
                                if (sameQualifier != null) {
                                    return ShortElvisExpression(
                                        element,
                                        element.textRange,
                                        getAnyExpression(targetStatement, document),
                                        listOf(sameQualifier.textRange)
                                    )
                                }
                            }
                        }
                    } else if (condition.operationSign.text == "&&" && element.elseBranch == null && element.thenBranch != null) {
                        val qualifiers = collectGuardedQualifiers(condition)
                        if (qualifiers != null) {
                            val targetStatement = unwrapThenBranch(element.thenBranch) ?: return null
                            val ranges = qualifiers.mapNotNull { qualifier ->
                                Helper.findSameQualifier(targetStatement, qualifier)?.textRange
                            }
                            if (ranges.size == qualifiers.size && ranges.isNotEmpty()) {
                                return ShortElvisExpression(
                                    element,
                                    element.textRange,
                                    getAnyExpression(targetStatement, document),
                                    ranges
                                )
                            }
                        }
                    }
                }

                is PsiPolyadicExpression -> {
                    if (element.elseBranch == null && element.thenBranch != null) {
                        val qualifiers = collectGuardedQualifiers(condition)
                        if (qualifiers != null) {
                            val targetStatement = unwrapThenBranch(element.thenBranch) ?: return null
                            val ranges = qualifiers.mapNotNull { qualifier ->
                                Helper.findSameQualifier(targetStatement, qualifier)?.textRange
                            }
                            if (ranges.size == qualifiers.size && ranges.isNotEmpty()) {
                                return ShortElvisExpression(
                                    element,
                                    element.textRange,
                                    getAnyExpression(targetStatement, document),
                                    ranges
                                )
                            }
                        }
                    }
                }
            }
        }
        return IfExpression(element, element.textRange)
    }

    fun getConditionalExpression(element: PsiConditionalExpression, document: Document): Expression? {
        val condition = element.condition
        if (checkExpressionsCollapse && condition is PsiBinaryExpression) {
            val operationSign = condition.operationSign.text
            val isInvertedElvis = operationSign == "=="
            val isStandardElvis = operationSign == "!="
            if ((isStandardElvis || isInvertedElvis) && condition.rOperand != null &&
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
                    val nonNullExpression = if (isInvertedElvis) {
                        element.elseExpression
                    } else {
                        element.thenExpression
                    } ?: return null
                    val fallbackExpression = if (isInvertedElvis) {
                        element.thenExpression
                    } else {
                        element.elseExpression
                    } ?: return null
                    val reference: PsiReference? = when (qualifierElement) {
                        is PsiReferenceExpression -> qualifierElement
                        is PsiMethodCallExpression -> qualifierElement.methodExpression
                        else -> return null
                    }
                    val references = SyntaxTraverser.psiTraverser(nonNullExpression)
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
                            getAnyExpression(nonNullExpression, document),
                            getAnyExpression(fallbackExpression, document),
                            references.map { it.textRange },
                            isInvertedElvis
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
                val expr = getAnyExpression(psiOperands[i], document)
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
            val lastExpr = getAnyExpression(psiOperands[lastIndex], document)
            operands[lastIndex] = lastExpr
            if (lastExpr is StringLiteral) {
                hasString = true
            }
            val operandList = operands.mapNotNull { it }
            if (hasString && concatenationExpressionsCollapse) {
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
