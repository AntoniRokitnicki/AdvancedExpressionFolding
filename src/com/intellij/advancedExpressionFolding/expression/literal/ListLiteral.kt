package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList


class ListLiteral(
    element: PsiElement,
    textRange: TextRange,
    items: List<Expression>
) : Expression(element, textRange) {
    private var items: List<Expression> = items

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ListLiteral::class.java.getName())
        return if (items.isEmpty()) {
            arrayOf(
                FoldingDescriptor(element.getNode(), textRange, group, "[]")
            )
        } else {
            val descriptors = ArrayList<FoldingDescriptor>()
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(textRange.getStartOffset(), items.get(0).getTextRange().getStartOffset()),
                    group,
                    "["
                )
            )
            if (items.get(items.size - 1).getTextRange().getEndOffset() < textRange.getEndOffset()) {
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(
                            items.get(items.size - 1).getTextRange().getEndOffset(),
                            textRange.getEndOffset()
                        ),
                        group,
                        "]"
                    )
                )
            }
            for (item in items) {
                for (d in item.buildFoldRegions(item.getElement(), document, this)) {
                    descriptors.add(d)
                }
            }
            descriptors.toArray(arrayOfNulls<FoldingDescriptor>(0))
        }
    }

    fun getItems(): List<Expression> {
        return items
    }
}

