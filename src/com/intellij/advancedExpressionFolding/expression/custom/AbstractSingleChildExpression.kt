package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class AbstractSingleChildExpression(
    element: PsiElement, textRange: TextRange, private val text: String, private val child: Expression?
) : Expression(element, textRange) {

    override fun supportsFoldRegions(
        document: Document,
        parent: Expression?
    ): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val folding = FoldingDescriptor(
            element.node,
            textRange,
            FoldingGroup.newGroup(groupName()),
            text
        )
        val array = arrayOf(folding)
        return if (child != null && child.supportsFoldRegions(document, this)) {
            array + child.buildFoldRegions(child.element, document, this)
        } else {
            array
        }
    }

    abstract fun groupName(): String

}
