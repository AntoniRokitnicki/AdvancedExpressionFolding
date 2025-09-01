package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.InterpolatedString
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList

class CompactControlFlowExpression(element: PsiElement, textRange: TextRange) : Expression(element, textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return supportsFoldRegions(document, textRange)
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        buildFoldRegions(
            element,
            FoldingGroup.newGroup(
                CompactControlFlowExpression::class.java.getName() + Expression.HIGHLIGHTED_GROUP_POSTFIX
            ),
            descriptors,
            textRange
        )
        return descriptors.toArray(EMPTY_ARRAY)
    }

    override fun isHighlighted(): Boolean {
        return true
    }

    companion object {
        @JvmStatic
        fun buildFoldRegions(
            element: PsiElement,
            group: FoldingGroup,
            descriptors: ArrayList<FoldingDescriptor>,
            textRange: TextRange
        ) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(textRange.getStartOffset(), textRange.getStartOffset() + 1),
                    group,
                    ""
                )
            )
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(textRange.getEndOffset() - 1, textRange.getEndOffset()),
                    group,
                    ""
                )
            )
        }

        @JvmStatic
        fun supportsFoldRegions(document: Document, textRange: TextRange): Boolean {
            return textRange.getStartOffset() > 0 && textRange.getEndOffset() < document.getTextLength() - 1 &&
                InterpolatedString.OVERFLOW_CHARACTERS.contains(
                    document.getText(
                        TextRange.create(textRange.getStartOffset() - 1, textRange.getStartOffset())
                    )
                ) &&
                InterpolatedString.OVERFLOW_CHARACTERS.contains(
                    document.getText(TextRange.create(textRange.getEndOffset(), textRange.getEndOffset() + 1))
                )
        }
    }
}

