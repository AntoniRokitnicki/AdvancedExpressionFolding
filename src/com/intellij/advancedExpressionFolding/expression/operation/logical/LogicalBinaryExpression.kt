package com.intellij.advancedExpressionFolding.expression.operation.logical

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class LogicalBinaryExpression(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>,
    val operator: LogicalOperator,
    private val uppercase: Boolean,
    @Suppress("UNUSED_PARAMETER") private val visualParentheses: Boolean,
) : Operation(element, textRange, operator.display(uppercase), operator.priority, operands) {

    override fun buildFolding(character: String): String {
        return " ${operator.display(uppercase)} "
    }
}

enum class LogicalOperator(val priority: Int, private val text: String) {
    OR(200, "OR"),
    AND(300, "AND");

    fun display(uppercase: Boolean): String = if (uppercase) text else text.lowercase()
}
