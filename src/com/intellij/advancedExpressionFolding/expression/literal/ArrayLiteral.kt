package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections

class ArrayLiteral(
    element: PsiElement,
    textRange: TextRange,
    private val items: List<Expression>
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return (!textRange.isEmpty && items.isEmpty()) ||
            (!items.isEmpty() &&
                textRange.startOffset < items[0].textRange.startOffset &&
                items[items.size - 1].textRange.endOffset < textRange.endOffset)
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ArrayLiteral::class.java.name)
        return if (items.isEmpty()) {
            arrayOf(
                FoldingDescriptor(
                    element.node,
                    textRange,
                    group,
                    "[]"
                )
            )
        } else {
            val descriptors = ArrayList<FoldingDescriptor>()
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(textRange.startOffset, items[0].textRange.startOffset),
                    group,
                    "["
                )
            )
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(
                        items[items.size - 1].textRange.endOffset,
                        textRange.endOffset
                    ),
                    group,
                    "]"
                )
            )
            for (item in items) {
                if (item.supportsFoldRegions(document, this)) {
                    Collections.addAll(
                        descriptors,
                        *item.buildFoldRegions(item.element, document, this)
                    )
                }
            }
            descriptors.toTypedArray()
        }
    }
}

