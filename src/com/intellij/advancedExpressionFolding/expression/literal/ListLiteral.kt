package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Negate
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class ListLiteral(
    element: PsiElement,
    textRange: TextRange,
    val items: List<Expression>
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ListLiteral::class.java.name)
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
        if (items.last().textRange.endOffset < textRange.endOffset) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(items.last().textRange.endOffset, textRange.endOffset),
                group,
                "]"
            )
        }
        for (item in items) {
            if (item is Negate) {
                continue
            }
            descriptors += item.buildFoldRegions(item.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }
}
