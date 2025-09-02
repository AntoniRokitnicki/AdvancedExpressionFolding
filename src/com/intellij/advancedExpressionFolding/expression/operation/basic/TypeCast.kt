package com.intellij.advancedExpressionFolding.expression.operation.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections

class TypeCast(
    element: PsiElement,
    textRange: TextRange,
    obj: Expression
) : Expression(element, textRange) {
    private var obj: Expression = obj

    fun getObject(): Expression {
        return obj
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val dotAccess = document.getTextLength() > getTextRange().getEndOffset() &&
                document.getText(TextRange.create(getTextRange().getEndOffset(), getTextRange().getEndOffset() + 1)) == "."
        val group = FoldingGroup.newGroup(TypeCast::class.java.getName() + if (dotAccess) "" else HIGHLIGHTED_GROUP_POSTFIX)
        val descriptors = ArrayList<FoldingDescriptor>()
        if (obj.getTextRange().getEndOffset() < getTextRange().getEndOffset()) {
            if (dotAccess) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(getTextRange().getStartOffset(), obj.getTextRange().getStartOffset()),
                        group,
                        ""
                    )
                )
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(obj.getTextRange().getEndOffset(), getTextRange().getEndOffset() + 1),
                        group,
                        "."
                    )
                )
            } else {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(getTextRange().getStartOffset(), obj.getTextRange().getStartOffset()),
                        group,
                        ""
                    )
                )
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(obj.getTextRange().getEndOffset(), getTextRange().getEndOffset()),
                        group,
                        ""
                    )
                )
            }
        } else {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(getTextRange().getStartOffset(), obj.getTextRange().getStartOffset()),
                    group,
                    ""
                )
            )
        }
        if (obj.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *obj.buildFoldRegions(obj.getElement(), document, this))
        }
        return descriptors.toTypedArray()
    }

    override fun isHighlighted(): Boolean {
        return true
    }
}
