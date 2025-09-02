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
    setterTextRange: TextRange,
    obj: Expression?,
    name: String,
    value: Expression
) : Expression(element, textRange) {
    private val setterTextRange: TextRange = setterTextRange
    private val obj: Expression? = obj
    private val name: String = name
    private val value: Expression = value

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Setter::class.java.getName())
        val descriptors = java.util.ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(
                    setterTextRange.getStartOffset(),
                    value.getTextRange().getStartOffset() - (if (value.isLeftOverflow()) 1 else 0)
                ),
                group,
                name + " = "
            )
        )
        if (!value.isRightOverflow()) {
            if (value.getTextRange().getEndOffset() < getTextRange().getEndOffset()) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(
                            value.getTextRange().getEndOffset(),
                            getTextRange().getEndOffset()
                        ),
                        group,
                        ""
                    )
                )
            }
        }
        if (obj != null && obj.supportsFoldRegions(document, this)) {
            java.util.Collections.addAll(descriptors, *obj.buildFoldRegions(obj.getElement(), document, this))
        }
        if (value.supportsFoldRegions(document, this)) {
            if (value.isOverflow()) {
                java.util.Collections.addAll(
                    descriptors,
                    *value.buildFoldRegions(value.getElement(), document, this, group, "", "")
                )
            } else {
                java.util.Collections.addAll(descriptors, *value.buildFoldRegions(value.getElement(), document, this))
            }
        }
        return descriptors.toTypedArray()
    }
}

