package com.intellij.advancedExpressionFolding.expression.semantic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.group
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class FastExpression(
    element: PsiElement,
    textRange: TextRange = element.textRange,
    private val text: String? = null,
    var group: FoldingGroup? = null,
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?) = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val parentGroup = group ?: createGroup()

        return arrayOf(
            FoldingDescriptor(
                element.node,
                textRange,
                parentGroup,
                text ?: "",
            )
        )
    }
    open fun createGroup(): FoldingGroup = this::class.group()

}

