package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.ArrayList
import java.util.Collections
import java.util.List

class ListLiteral(element: PsiElement, textRange: TextRange, items: List<Expression>) : Expression(element, textRange) {
    private var items: List<Expression>

    init {
        this.items = items
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ListLiteral::class.java.getName())
        if (items.isEmpty()) {
            return arrayOf(FoldingDescriptor(element.getNode(), getTextRange(), group, "[]"))
        } else {
            val descriptors: ArrayList<FoldingDescriptor> = ArrayList()
            descriptors.add(FoldingDescriptor(element.getNode(), TextRange.create(getTextRange().getStartOffset(),
                items.get(0).getTextRange().getStartOffset()), group, "["))
            val lastIndex = items.stream().count().toInt() - 1
            if (items.get(lastIndex).getTextRange().getEndOffset() < getTextRange().getEndOffset()) {
                descriptors.add(FoldingDescriptor(element.getNode(), TextRange.create(
                    items.get(lastIndex).getTextRange().getEndOffset(),
                    getTextRange().getEndOffset()), group, "]"))
            }
            val iterator = items.iterator()
            while (iterator.hasNext()) {
                val item = iterator.next()
                Collections.addAll(descriptors, *item.buildFoldRegions(item.getElement(), document, this))
            }
            return descriptors.toTypedArray()
        }
    }

    fun getItems(): List<Expression> {
        return items
    }
}
