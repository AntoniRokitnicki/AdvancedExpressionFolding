package com.intellij.advancedExpressionFolding.expression.operation.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class StreamFilterNotNull(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Operation(element, textRange, ".filterNotNull()", 300, operands) {
    override fun buildFolding(character: String) = character

    override fun changeOperandsEndOffset(startOffset: Int): Int {
        return startOffset + "Objects::nonNull".length
    }

    override fun isCollapsedByDefault() = true

    override fun supportsFoldRegions(document: Document, parent: Expression?) = true
}
