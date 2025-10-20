package com.intellij.advancedExpressionFolding.expression.operation

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.CollapsedOperation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class FieldShiftMethod(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>,
    private val text: String
) : CollapsedOperation(element, "", operands, textRange) {

    override fun suffixText(): String = text
}
