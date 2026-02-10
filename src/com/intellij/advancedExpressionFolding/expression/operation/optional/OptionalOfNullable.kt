package com.intellij.advancedExpressionFolding.expression.operation.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.CollapsedOperation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

open class OptionalOfNullable(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : CollapsedOperation(element, "", operands, textRange) {
    override fun changeOperandsStartOffset(offset: Int): Int {
        return offset - "Optional".length
    }
}
