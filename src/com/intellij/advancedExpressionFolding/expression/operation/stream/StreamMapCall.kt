package com.intellij.advancedExpressionFolding.expression.operation.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class StreamMapCall(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>,
    flatMap: Boolean
) : Operation(element, textRange, if (flatMap) "**." else "*.", 300, operands) {
    override fun buildFolding(character: String) = character

    override fun isCollapsedByDefault() = true

    override fun supportsFoldRegions(document: Document, parent: Expression?) = true
}
