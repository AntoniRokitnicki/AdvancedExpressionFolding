package com.intellij.advancedExpressionFolding.expression.property

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Setter(
    element: PsiElement,
    textRange: TextRange,
    private val setterTextRange: TextRange,
    private val receiver: Expression?,
    private val name: String,
    private val value: Expression
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Setter::class.java.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(
                setterTextRange.startOffset,
                value.textRange.startOffset - if (value.isLeftOverflow()) 1 else 0
            ),
            group,
            "$name = "
        )
        if (!value.isRightOverflow()) {
            if (value.textRange.endOffset < textRange.endOffset) {
                descriptors += FoldingDescriptor(
                    element.node,
                    TextRange.create(value.textRange.endOffset, textRange.endOffset),
                    group,
                    ""
                )
            }
        }
        receiver?.takeIf { it.supportsFoldRegions(document, this) }?.let { obj ->
            descriptors += obj.buildFoldRegions(obj.element, document, this).toList()
        }
        if (value.supportsFoldRegions(document, this)) {
            if (value.isOverflow()) {
                descriptors += value.buildFoldRegions(value.element, document, this, group, "", "").toList()
            } else {
                descriptors += value.buildFoldRegions(value.element, document, this).toList()
            }
        }
        return descriptors.toTypedArray()
    }
}
