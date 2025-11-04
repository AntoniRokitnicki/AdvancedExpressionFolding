package com.intellij.advancedExpressionFolding.expression.operation.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.CollapsedOperation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class StreamFilterNotNull(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : CollapsedOperation(element, ".filterNotNull()", operands, textRange) {
    override fun changeOperandsEndOffset(startOffset: Int): Int {
        return startOffset + "Objects::nonNull".length
    }
}
