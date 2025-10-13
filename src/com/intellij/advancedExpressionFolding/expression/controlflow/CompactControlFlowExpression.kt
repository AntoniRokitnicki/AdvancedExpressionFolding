package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.InterpolatedString
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class CompactControlFlowExpression(
    element: PsiElement,
    textRange: TextRange
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return supportsFoldRegions(document, textRange)
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        buildFoldRegions(
            element,
            FoldingGroup.newGroup(
                CompactControlFlowExpression::class.java.name + Expression.HIGHLIGHTED_GROUP_POSTFIX
            ),
            descriptors,
            textRange
        )
        return descriptors.toTypedArray()
    }

    override fun isHighlighted(): Boolean = true

    companion object {
        @JvmStatic
        fun buildFoldRegions(
            element: PsiElement,
            group: FoldingGroup,
            descriptors: MutableList<FoldingDescriptor>,
            textRange: TextRange
        ) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, textRange.startOffset + 1),
                group,
                ""
            )
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(textRange.endOffset - 1, textRange.endOffset),
                group,
                ""
            )
        }

        @JvmStatic
        fun supportsFoldRegions(document: Document, textRange: TextRange): Boolean {
            if (textRange.startOffset <= 0 || textRange.endOffset >= document.textLength - 1) {
                return false
            }
            val before = document.getText(TextRange.create(textRange.startOffset - 1, textRange.startOffset))
            val after = document.getText(TextRange.create(textRange.endOffset, textRange.endOffset + 1))
            return InterpolatedString.OVERFLOW_CHARACTERS.contains(before) &&
                InterpolatedString.OVERFLOW_CHARACTERS.contains(after)
        }
    }
}
