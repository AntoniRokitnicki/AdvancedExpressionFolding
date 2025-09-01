package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.ArrayList
import java.util.Collections

class SyntheticExpressionImpl(element: PsiElement, textRange: TextRange, text: String, children: ArrayList<Expression>) : Expression(element, textRange) {
    private var text: String
    private var children: ArrayList<Expression>

    init {
        this.text = text
        this.children = children
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || this.javaClass != o.javaClass) return false
        val that = o as SyntheticExpressionImpl
        return text.equals(that.getText())
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val childrenSize = children.stream().count().toInt()
        if (childrenSize <= 0) {
            return false
        }
        var iterator = children.iterator()
        while (iterator.hasNext()) {
            val e = iterator.next()
            if (e.supportsFoldRegions(document, this)) {
                return true
            }
        }
        return false
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors: ArrayList<FoldingDescriptor> = ArrayList()
        val iterator = children.iterator()
        while (iterator.hasNext()) {
            val child = iterator.next()
            if (child.supportsFoldRegions(document, this)) {
                Collections.addAll(descriptors, *child.buildFoldRegions(child.getElement(), document, this))
            }
        }
        return descriptors.toTypedArray()
    }

    fun getText(): String {
        return text
    }
}
