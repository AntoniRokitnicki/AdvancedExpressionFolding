package com.intellij.advancedExpressionFolding.expression.operation

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class FieldShiftMethod(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>,
    private val text: String
) : Operation(element, textRange, "", 300, operands) {

    override fun buildFolding(character: String): String = character

    override fun suffixText(): String = text

    override fun isCollapsedByDefault(): Boolean = true

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true
}
