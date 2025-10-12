package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class ArrayLiteral(
    element: PsiElement,
    textRange: TextRange,
    val items: List<Expression>
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return (!textRange.isEmpty && items.isEmpty()) ||
            (items.isNotEmpty() &&
                textRange.startOffset < items.first().textRange.startOffset &&
                items.last().textRange.endOffset < textRange.endOffset)
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ArrayLiteral::class.java.name)
        if (items.isEmpty()) {
            return arrayOf(FoldingDescriptor(element.node, textRange, group, "[]"))
        }
        val descriptors = mutableListOf<FoldingDescriptor>()
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(textRange.startOffset, items.first().textRange.startOffset),
            group,
            "["
        )
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(items.last().textRange.endOffset, textRange.endOffset),
            group,
            "]"
        )
        for (item in items) {
            if (item.supportsFoldRegions(document, this)) {
                descriptors += item.buildFoldRegions(item.element, document, this).toList()
            }
        }
        return descriptors.toTypedArray()
    }
}
