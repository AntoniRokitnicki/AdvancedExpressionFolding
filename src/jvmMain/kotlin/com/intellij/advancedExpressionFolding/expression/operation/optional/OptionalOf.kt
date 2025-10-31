package com.intellij.advancedExpressionFolding.expression.operation.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class OptionalOf(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : OptionalOfNullable(element, textRange, operands) {
    protected override fun suffixText(): String = "!!"
}
