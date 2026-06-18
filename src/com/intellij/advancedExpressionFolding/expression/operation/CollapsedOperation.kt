package com.intellij.advancedExpressionFolding.expression.operation

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

open class CollapsedOperation(
    element: PsiElement,
    text: String,
    operands: List<Expression> = emptyList(),
    textRange: TextRange = element.textRange,
    priority: Int = 300,
) : Operation(element, textRange, text, priority, operands) {

    override fun buildFolding(character: String): String = character

    override fun isCollapsedByDefault(): Boolean = true

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true
}
