package com.intellij.advancedExpressionFolding.expression.operation.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

open class OptionalOfNullable(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Operation(element, textRange, "", 300, operands) {
    override fun buildFolding(character: String): String = character

    override fun changeOperandsStartOffset(offset: Int): Int {
        return offset - "Optional".length
    }

    override fun isCollapsedByDefault(): Boolean = true

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true
}
