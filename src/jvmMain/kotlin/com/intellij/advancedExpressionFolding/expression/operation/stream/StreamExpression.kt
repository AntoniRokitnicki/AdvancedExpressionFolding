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

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val startOffset = Helper.findDot(document, textRange.startOffset, -1, true)
        val endOffset = Helper.findDot(document, textRange.endOffset - 1, 1, false)
        return when {
            startOffset < -1 &&
                document.getText(TextRange.create(textRange.startOffset + startOffset, textRange.startOffset + startOffset + 1)) == "\n" &&
                endOffset > 1 -> {
                val startOffsetNoWhitespace = Helper.findDot(document, textRange.startOffset, -1, false)
                val startOffsetFinal = textRange.startOffset + startOffsetNoWhitespace
                val endOffsetFinal = textRange.endOffset + endOffset
                if (endOffsetFinal < 0) {
                    EMPTY_ARRAY
                } else {
                    arrayOf(
                        FoldingDescriptor(
                            element.node,
                            TextRange.create(startOffsetFinal, endOffsetFinal),
                            FoldingGroup.newGroup(StreamExpression::class.java.name),
                            "."
                        )
                    )
                }
            }
            startOffset < -1 &&
                document.getText(TextRange.create(textRange.startOffset + startOffset, textRange.startOffset + startOffset + 1)) == "." -> {
                val endOffsetWithWhitespace = Helper.findDot(document, textRange.endOffset - 1, 1, true)
                arrayOf(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(textRange.startOffset, textRange.endOffset + endOffsetWithWhitespace),
                        FoldingGroup.newGroup(StreamExpression::class.java.name + HIGHLIGHTED_GROUP_POSTFIX),
                        ""
                    )
                )
            }
            startOffset == -1 && endOffset > 1 -> {
                arrayOf(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(textRange.startOffset + startOffset, textRange.endOffset),
                        FoldingGroup.newGroup(StreamExpression::class.java.name + HIGHLIGHTED_GROUP_POSTFIX),
                        ""
                    )
                )
            }
            startOffset < -1 &&
                document.getText(TextRange.create(textRange.startOffset - 1, textRange.startOffset)) == "." &&
                endOffset == 1 -> {
                val endOffsetWithWhitespace = Helper.findDot(document, textRange.endOffset - 1, 1, true)
                arrayOf(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(textRange.startOffset - 1, textRange.endOffset + endOffsetWithWhitespace),
                        FoldingGroup.newGroup(StreamExpression::class.java.name),
                        "."
                    )
                )
            }
            startOffset == -1 && endOffset == 1 -> {
                arrayOf(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(textRange.startOffset - 1, textRange.endOffset + 1),
                        FoldingGroup.newGroup(StreamExpression::class.java.name),
                        "."
                    )
                )
            }
            else -> EMPTY_ARRAY
        }
    }

    override fun isHighlighted(): Boolean = true
}
