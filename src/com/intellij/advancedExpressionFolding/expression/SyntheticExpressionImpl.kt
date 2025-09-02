package com.intellij.advancedExpressionFolding.expression

import com.intellij.advancedExpressionFolding.expression.Expression.Companion.EMPTY_ARRAY
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections

class SyntheticExpressionImpl : Expression {
    private var text: String
    private var children: ArrayList<Expression>

    constructor(element: PsiElement, textRange: TextRange, text: String, children: ArrayList<Expression>) : super(element, textRange) {
        this.text = text
        this.children = children
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as SyntheticExpressionImpl
        return text == that.text
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return children.size > 0 && children.stream().anyMatch { e -> e.supportsFoldRegions(document, this) }
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        for (child in children) {
            if (child.supportsFoldRegions(document, this)) {
                Collections.addAll(descriptors, *child.buildFoldRegions(child.getElement(), document, this))
            }
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }

    fun getText(): String {
        return text
    }
}
