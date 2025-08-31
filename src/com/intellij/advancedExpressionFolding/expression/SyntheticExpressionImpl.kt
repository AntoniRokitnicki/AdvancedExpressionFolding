package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class SyntheticExpressionImpl(
    element: PsiElement,
    textRange: TextRange,
    private val text: String,
    private val children: ArrayList<Expression>
) : Expression(element, textRange) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as SyntheticExpressionImpl
        return text == other.text
    }

    override fun hashCode(): Int = text.hashCode()

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return children.isNotEmpty() && children.any { it.supportsFoldRegions(document, this) }
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        for (child in children) {
            if (child.supportsFoldRegions(document, this)) {
                descriptors.addAll(child.buildFoldRegions(child.element, document, this))
            }
        }
        return descriptors.toTypedArray()
    }

    fun getText(): String = text
}

