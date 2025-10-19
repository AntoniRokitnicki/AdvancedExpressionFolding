package com.intellij.advancedExpressionFolding.expression.operation.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

open class OptionalIfPresentLet(
    element: PsiElement,
    textRange: TextRange,
    qualifier: Expression,
) : Operation(element, textRange, "", 300, listOf(qualifier)) {

    override fun buildFolding(character: String): String = character

    override fun suffixText(): String = "?.let { … }"

    override fun isCollapsedByDefault(): Boolean = true

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true
}

