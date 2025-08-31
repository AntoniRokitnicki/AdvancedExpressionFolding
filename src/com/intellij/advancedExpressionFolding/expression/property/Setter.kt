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
    private val obj: Expression?,
    private val name: String,
    private val value: Expression
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Setter::class.java.name)
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(setterTextRange.startOffset, value.textRange.startOffset - if (value.isLeftOverflow()) 1 else 0),
                group,
                "$name = "
            )
        )
        if (!value.isRightOverflow()) {
            if (value.textRange.endOffset < textRange.endOffset) {
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(value.textRange.endOffset, textRange.endOffset),
                        group,
                        ""
                    )
                )
            }
        }
        if (obj != null && obj.supportsFoldRegions(document, this)) {
            descriptors.addAll(obj.buildFoldRegions(obj.element, document, this))
        }
        if (value.supportsFoldRegions(document, this)) {
            if (value.isOverflow()) {
                descriptors.addAll(value.buildFoldRegions(value.element, document, this, group, "", ""))
            } else {
                descriptors.addAll(value.buildFoldRegions(value.element, document, this))
            }
        }
        return descriptors.toTypedArray()
    }
}

