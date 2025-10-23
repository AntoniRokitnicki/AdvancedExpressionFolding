package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Add
import com.intellij.advancedExpressionFolding.expression.math.basic.Divide
import com.intellij.advancedExpressionFolding.expression.math.basic.Multiply
import com.intellij.advancedExpressionFolding.expression.math.basic.NotEqual
import com.intellij.advancedExpressionFolding.expression.math.basic.Subtract
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append
import com.intellij.advancedExpressionFolding.expression.operation.basic.Equal
import com.intellij.advancedExpressionFolding.expression.operation.basic.Greater
import com.intellij.advancedExpressionFolding.expression.operation.basic.GreaterEqual
import com.intellij.advancedExpressionFolding.expression.operation.collection.Range
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.advancedExpressionFolding.processor.argumentExpressions
import com.intellij.advancedExpressionFolding.processor.argumentCount
import com.intellij.advancedExpressionFolding.processor.util.Helper.eraseGenerics
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.IExpressionCollapseState
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiPrefixExpression

object BinaryExpressionExt : IExpressionCollapseState by AdvancedExpressionFoldingSettings.State()() {

    fun getBinaryExpression(element: PsiBinaryExpression, document: Document): Expression? {
        tryBuildCompareToBasedExpression(element, document)?.let { return it }
        tryBuildBasicBinaryOperation(element, document)?.let { return it }
        return if (isAndOfBinaryExpressions(element)) {
            getAndTwoBinaryExpressions(element, element.lOperand as PsiBinaryExpression, element.rOperand as PsiBinaryExpression, document)
        } else {
            null
        }
    }

    private fun isAndOfBinaryExpressions(element: PsiBinaryExpression): Boolean {
        return element.operationSign.text == "&&" &&
            element.lOperand is PsiBinaryExpression &&
            element.rOperand is PsiBinaryExpression
    }

    private fun tryBuildBasicBinaryOperation(element: PsiBinaryExpression, document: Document): Expression? {
        if (!Consts.SUPPORTED_BINARY_OPERATORS.contains(element.operationSign.text) || element.rOperand == null) {
            return null
        }
        val leftExpression = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(element.lOperand, document)
        val rightExpression = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(element.rOperand!!, document)
        return when (element.operationSign.text) {
            "+" -> Add(element, element.textRange, listOf(leftExpression, rightExpression))
            "-" -> Subtract(element, element.textRange, listOf(leftExpression, rightExpression))
            "*" -> Multiply(element, element.textRange, listOf(leftExpression, rightExpression))
            "/" -> Divide(element, element.textRange, listOf(leftExpression, rightExpression))
            else -> null
        }
    }

    private fun tryBuildCompareToBasedExpression(element: PsiBinaryExpression, document: Document): Expression? {
        if (!comparingExpressionsCollapse) {
            return null
        }
        val methodCall = getMethodCallOperand(element)
        val literal = getLiteralOperand(element)
        if (!isSupportedCompareToPattern(methodCall, literal)) {
            return null
        }
        val method = methodCall!!.methodExpression.resolve() as? PsiMethod ?: return null
        if (!isSupportedCompareToMethod(method)) {
            return null
        }
        val qualifier = getQualifierExpression(methodCall, document) ?: return null
        val argument = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(methodCall.argumentExpressions[0], document)
        val operationSign = element.operationSign.text
        val expressionValue = literal!!.text.toInt()
        var lessOperation = "<"
        var greaterOperation = ">"
        if (literal == element.lOperand) {
            lessOperation = ">"
            greaterOperation = "<"
        }
        return buildCompareToExpression(element, qualifier, argument, operationSign, expressionValue, lessOperation, greaterOperation)
    }

    private fun getMethodCallOperand(element: PsiBinaryExpression): PsiMethodCallExpression? {
        return when {
            element.lOperand is PsiMethodCallExpression -> element.lOperand as PsiMethodCallExpression
            element.rOperand is PsiMethodCallExpression -> element.rOperand as PsiMethodCallExpression
            else -> null
        }
    }

    private fun getLiteralOperand(element: PsiBinaryExpression): PsiElement? {
        return when {
            isLiteralOrNegatedLiteral(element.lOperand) -> element.lOperand
            isLiteralOrNegatedLiteral(element.rOperand) -> element.rOperand
            else -> null
        }
    }

    private fun isSupportedCompareToPattern(methodCall: PsiMethodCallExpression?, literal: PsiElement?): Boolean {
        if (methodCall == null || literal == null) {
            return false
        }
        if (literal.text != "0" && literal.text != "1" && literal.text != "-1") {
            return false
        }
        val identifier = methodCall.methodExpression.children.firstOrNull { it is PsiIdentifier } as? PsiIdentifier
        return identifier != null && identifier.text == "compareTo" && methodCall.argumentCount == 1
    }

    private fun isSupportedCompareToMethod(method: PsiMethod?): Boolean {
        if (method == null) {
            return false
        }
        val psiClass = method.containingClass ?: return false
        val qualifiedName = psiClass.qualifiedName
        val supportedClass = qualifiedName?.let { Consts.SUPPORTED_CLASSES.contains(eraseGenerics(it)) } == true
        return supportedClass || Consts.UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS.contains(method.name)
    }

    private fun getQualifierExpression(methodCall: PsiMethodCallExpression, document: Document): Expression? {
        val qualifier = methodCall.methodExpression.qualifierExpression ?: return null
        return com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(qualifier, document)
    }

    private fun buildCompareToExpression(
        element: PsiBinaryExpression,
        qualifier: Expression,
        argument: Expression,
        operationSign: String,
        expressionValue: Int,
        lessOperation: String,
        greaterOperation: String
    ): Expression? {
        return when (operationSign) {
            "==" -> buildEqualComparison(element, qualifier, argument, expressionValue)
            "!=" -> buildNotEqualComparison(element, qualifier, argument, expressionValue)
            lessOperation -> buildLessComparison(element, qualifier, argument, expressionValue)
            greaterOperation -> buildGreaterComparison(element, qualifier, argument, expressionValue)
            "$lessOperation=" -> buildLessEqualComparison(element, qualifier, argument, expressionValue)
            "$greaterOperation=" -> buildGreaterEqualComparison(element, qualifier, argument, expressionValue)
            else -> null
        }
    }

    private fun buildEqualComparison(
        element: PsiBinaryExpression,
        qualifier: Expression,
        argument: Expression,
        expressionValue: Int
    ): Expression? {
        return when (expressionValue) {
            -1 -> Append.Less(element, element.textRange, listOf(qualifier, argument))
            0 -> Equal(element, element.textRange, listOf(qualifier, argument))
            1 -> Greater(element, element.textRange, listOf(qualifier, argument))
            else -> null
        }
    }

    private fun buildNotEqualComparison(
        element: PsiBinaryExpression,
        qualifier: Expression,
        argument: Expression,
        expressionValue: Int
    ): Expression? {
        return when (expressionValue) {
            1 -> Append.LessEqual(element, element.textRange, listOf(qualifier, argument))
            0 -> NotEqual(element, element.textRange, listOf(qualifier, argument))
            -1 -> GreaterEqual(element, element.textRange, listOf(qualifier, argument))
            else -> null
        }
    }

    private fun buildLessComparison(
        element: PsiBinaryExpression,
        qualifier: Expression,
        argument: Expression,
        expressionValue: Int
    ): Expression? {
        return when (expressionValue) {
            1 -> Append.LessEqual(element, element.textRange, listOf(qualifier, argument))
            0 -> Append.Less(element, element.textRange, listOf(qualifier, argument))
            else -> null
        }
    }

    private fun buildGreaterComparison(
        element: PsiBinaryExpression,
        qualifier: Expression,
        argument: Expression,
        expressionValue: Int
    ): Expression? {
        return when (expressionValue) {
            -1 -> GreaterEqual(element, element.textRange, listOf(qualifier, argument))
            0 -> Greater(element, element.textRange, listOf(qualifier, argument))
            else -> null
        }
    }

    private fun buildLessEqualComparison(
        element: PsiBinaryExpression,
        qualifier: Expression,
        argument: Expression,
        expressionValue: Int
    ): Expression? {
        return when (expressionValue) {
            -1 -> Append.Less(element, element.textRange, listOf(qualifier, argument))
            0 -> Append.LessEqual(element, element.textRange, listOf(qualifier, argument))
            else -> null
        }
    }

    private fun buildGreaterEqualComparison(
        element: PsiBinaryExpression,
        qualifier: Expression,
        argument: Expression,
        expressionValue: Int
    ): Expression? {
        return when (expressionValue) {
            1 -> Greater(element, element.textRange, listOf(qualifier, argument))
            0 -> GreaterEqual(element, element.textRange, listOf(qualifier, argument))
            else -> null
        }
    }

    fun isLiteralOrNegatedLiteral(element: PsiElement?): Boolean {
        return element is PsiLiteralExpression ||
            element is PsiPrefixExpression && element.operand is PsiLiteralExpression && element.operationSign.text == "-"
    }

    fun getAndTwoBinaryExpressions(
        parent: PsiElement,
        a: PsiBinaryExpression,
        b: PsiBinaryExpression,
        document: Document
    ): Expression? {
        if (!rangeExpressionsCollapse) {
            return null
        }
        if ((a.operationSign.text == "<" || a.operationSign.text == "<=") &&
            (b.operationSign.text == ">" || b.operationSign.text == ">=") &&
            a.rOperand != null && b.rOperand != null
        ) {
            val e1 = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(a.lOperand, document)
            val e2 = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(a.rOperand!!, document)
            val e3 = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(b.lOperand, document)
            val e4 = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(b.rOperand!!, document)
            if (e1 == e3) {
                return Range(
                    parent,
                    TextRange.create(a.textRange.startOffset, b.textRange.endOffset),
                    e1,
                    e4,
                    b.operationSign.text == ">=",
                    e2,
                    a.operationSign.text == "<="
                )
            }
        }
        if ((a.operationSign.text == ">" || a.operationSign.text == ">=") &&
            (b.operationSign.text == "<" || b.operationSign.text == "<=") &&
            a.rOperand != null && b.rOperand != null
        ) {
            val e1 = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(a.lOperand, document)
            val e2 = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(a.rOperand!!, document)
            val e3 = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(b.lOperand, document)
            val e4 = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(b.rOperand!!, document)
            if (e1 == e3) {
                return Range(
                    parent,
                    TextRange.create(a.textRange.startOffset, b.textRange.endOffset),
                    e1,
                    e2,
                    a.operationSign.text == ">=",
                    e4,
                    b.operationSign.text == "<="
                )
            }
        }
        return null
    }
}
