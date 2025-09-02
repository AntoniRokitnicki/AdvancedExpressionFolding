package com.intellij.advancedExpressionFolding.expression.operation.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class OptionalOrElseElvis(element: PsiElement, textRange: TextRange, operands: List<Expression>) : Operation(element, textRange, " ?: ", 300, operands) {
    override fun buildFolding(character: String): String {
        return character
    }

    override fun isCollapsedByDefault(): Boolean {
        return true
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }
}
