package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections

class SetLiteral(
    element: PsiElement,
    textRange: TextRange,
    val firstBracesRange: TextRange,
    val secondBracesRange: TextRange,
    items: MutableList<Expression>
) : Function(element, textRange, "Set.of", items) {

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(javaClass.name)
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        var offset = textRange.startOffset
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(offset, firstBracesRange.startOffset),
                group,
                "["
            )
        )
        if (firstBracesRange.startOffset < secondBracesRange.startOffset) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(firstBracesRange.startOffset, secondBracesRange.startOffset),
                    group,
                    ""
                )
            )
        }
        if (secondBracesRange.startOffset < operands[0].textRange.startOffset) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(
                        secondBracesRange.startOffset,
                        operands[0].textRange.startOffset
                    ),
                    group,
                    ""
                )
            )
        }
        offset = operands[0].textRange.endOffset
        for (i in 1 until operands.size) {
            val r = TextRange.create(offset, operands[i].textRange.startOffset)
            val p = ", "
            if (document.getText(r) != p) {
                descriptors.add(FoldingDescriptor(element.node, r, group, p))
            }
            offset = operands[i].textRange.endOffset
        }
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(offset, secondBracesRange.endOffset),
                group,
                ""
            )
        )
        if (secondBracesRange.endOffset < firstBracesRange.endOffset - 1) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(
                        secondBracesRange.endOffset,
                        firstBracesRange.endOffset - 1
                    ),
                    group,
                    ""
                )
            )
        }
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(firstBracesRange.endOffset - 1, firstBracesRange.endOffset),
                group,
                "]"
            )
        )
        if (firstBracesRange.endOffset < textRange.endOffset) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(firstBracesRange.endOffset, textRange.endOffset),
                    group,
                    ""
                )
            )
        }
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                Collections.addAll(
                    descriptors,
                    *operand.buildFoldRegions(operand.element, document, this)
                )
            }
        }
        return descriptors.toTypedArray()
    }
}

