package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList


class SetLiteral(
    element: PsiElement,
    textRange: TextRange,
    firstBracesRange: TextRange,
    secondBracesRange: TextRange,
    items: List<Expression>
) : Function(element, textRange, "Set.of", items) {
    private var firstBracesRange: TextRange = firstBracesRange
    private var secondBracesRange: TextRange = secondBracesRange

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(this.javaClass.getName())
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        var offset = getTextRange().getStartOffset()
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(offset, firstBracesRange.getStartOffset()),
                group,
                "["
            )
        )
        if (firstBracesRange.getStartOffset() < secondBracesRange.getStartOffset()) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(firstBracesRange.getStartOffset(), secondBracesRange.getStartOffset()),
                    group,
                    ""
                )
            )
        }
        if (secondBracesRange.getStartOffset() < operands[0].getTextRange().getStartOffset()) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(
                        secondBracesRange.getStartOffset(),
                        operands[0].getTextRange().getStartOffset()
                    ),
                    group,
                    ""
                )
            )
        }
        offset = operands[0].getTextRange().getEndOffset()
        for (i in 1 until operands.size) {
            val r = TextRange.create(offset, operands[i].getTextRange().getStartOffset())
            val p = ", "
            if (!document.getText(r).equals(p)) {
                descriptors.add(FoldingDescriptor(element.getNode(), r, group, p))
            }
            offset = operands[i].getTextRange().getEndOffset()
        }
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(offset, secondBracesRange.getEndOffset()),
                group,
                ""
            )
        )
        if (secondBracesRange.getEndOffset() < firstBracesRange.getEndOffset() - 1) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(secondBracesRange.getEndOffset(), firstBracesRange.getEndOffset() - 1),
                    group,
                    ""
                )
            )
        }
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(firstBracesRange.getEndOffset() - 1, firstBracesRange.getEndOffset()),
                group,
                "]"
            )
        )
        if (firstBracesRange.getEndOffset() < getTextRange().getEndOffset()) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(firstBracesRange.getEndOffset(), getTextRange().getEndOffset()),
                    group,
                    ""
                )
            )
        }
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                for (d in operand.buildFoldRegions(operand.getElement(), document, this)) {
                    descriptors.add(d)
                }
            }
        }
        return descriptors.toTypedArray()
    }

    fun getFirstBracesRange(): TextRange {
        return firstBracesRange
    }

    fun getSecondBracesRange(): TextRange {
        return secondBracesRange
    }
}

