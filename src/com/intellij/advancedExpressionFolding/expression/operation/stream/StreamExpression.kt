package com.intellij.advancedExpressionFolding.expression.operation.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class StreamExpression(
    element: PsiElement,
    textRange: TextRange
) : Expression(element, textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val startOffset = Helper.findDot(document, getTextRange().getStartOffset(), -1, true)
        val endOffset = Helper.findDot(document, getTextRange().getEndOffset() - 1, 1, false)
        if (startOffset < -1 && document.getText(TextRange.create(getTextRange().getStartOffset() + startOffset, getTextRange().getStartOffset() + startOffset + 1)) == "\n" && endOffset > 1) {
            val startOffsetNoWhitespace = Helper.findDot(document, getTextRange().getStartOffset(), -1, false)
            val startOffsetFinal = getTextRange().getStartOffset() + startOffsetNoWhitespace
            val endOffsetFinal = getTextRange().getEndOffset() + endOffset
            if (endOffsetFinal < 0) {
                return EMPTY_ARRAY
            }
            return arrayOf(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(startOffsetFinal, endOffsetFinal),
                    FoldingGroup.newGroup(StreamExpression::class.java.name),
                    "."
                )
            )
        } else if (startOffset < -1 && document.getText(TextRange.create(getTextRange().getStartOffset() + startOffset, getTextRange().getStartOffset() + startOffset + 1)) == ".") {
            val endOffsetWithWhitespace = Helper.findDot(document, getTextRange().getEndOffset() - 1, 1, true)
            return arrayOf(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(getTextRange().getStartOffset(), getTextRange().getEndOffset() + endOffsetWithWhitespace),
                    FoldingGroup.newGroup(StreamExpression::class.java.name + HIGHLIGHTED_GROUP_POSTFIX),
                    ""
                )
            )
        } else if (startOffset == -1 && endOffset > 1) {
            return arrayOf(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(getTextRange().getStartOffset() + startOffset, getTextRange().getEndOffset()),
                    FoldingGroup.newGroup(StreamExpression::class.java.name + Expression.HIGHLIGHTED_GROUP_POSTFIX),
                    ""
                )
            )
        } else if (startOffset < -1 && document.getText(TextRange.create(getTextRange().getStartOffset() - 1, getTextRange().getStartOffset())) == "." && endOffset == 1) {
            val endOffsetWithWhitespace = Helper.findDot(document, getTextRange().getEndOffset() - 1, 1, true)
            return arrayOf(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(getTextRange().getStartOffset() - 1, getTextRange().getEndOffset() + endOffsetWithWhitespace),
                    FoldingGroup.newGroup(StreamExpression::class.java.name),
                    "."
                )
            )
        } else if (startOffset == -1 && endOffset == 1) {
            return arrayOf(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(getTextRange().getStartOffset() - 1, getTextRange().getEndOffset() + 1),
                    FoldingGroup.newGroup(StreamExpression::class.java.name),
                    "."
                )
            )
        }
        return EMPTY_ARRAY
    }

    override fun isHighlighted(): Boolean {
        return true
    }
}
