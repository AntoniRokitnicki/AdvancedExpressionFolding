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
    text: String
) : Operation(element, textRange, "", 300, operands) {
    private var text: String = text

    override fun buildFolding(character: String): String {
        return character
    }

    override fun suffixText(): String {
        return text
    }

    override fun isCollapsedByDefault(): Boolean {
        return true
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }
}
