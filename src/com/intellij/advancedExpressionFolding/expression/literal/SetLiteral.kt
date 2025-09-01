package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.ArrayList
import java.util.Collections
import java.util.List

class SetLiteral(
    element: PsiElement,
    textRange: TextRange,
    firstBracesRange: TextRange,
    secondBracesRange: TextRange,
    items: List<Expression>
) : Function(element, textRange, "Set.of", items) {
    private var firstBracesRange: TextRange
    private var secondBracesRange: TextRange

    init {
        this.firstBracesRange = firstBracesRange
        this.secondBracesRange = secondBracesRange
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(this.javaClass.getName())
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        var offset = getTextRange().getStartOffset()
        descriptors.add(FoldingDescriptor(element.getNode(), TextRange.create(offset, firstBracesRange.getStartOffset()), group, "["))
        if (firstBracesRange.getStartOffset() < secondBracesRange.getStartOffset()) {
            descriptors.add(FoldingDescriptor(element.getNode(), TextRange.create(firstBracesRange.getStartOffset(), secondBracesRange.getStartOffset()), group, ""))
        }
        if (secondBracesRange.getStartOffset() < operands.get(0).getTextRange().getStartOffset()) {
            descriptors.add(FoldingDescriptor(element.getNode(), TextRange.create(secondBracesRange.getStartOffset(), operands.get(0).getTextRange().getStartOffset()), group, ""))
        }
        offset = operands.get(0).getTextRange().getEndOffset()
        val operandsSize = operands.stream().count().toInt()
        var i = 1
        while (i < operandsSize) {
            val r = TextRange.create(offset, operands.get(i).getTextRange().getStartOffset())
            val p = ", "
            if (!document.getText(r).equals(p)) {
                descriptors.add(FoldingDescriptor(element.getNode(), r, group, p))
            }
            offset = operands.get(i).getTextRange().getEndOffset()
            i++
        }
        descriptors.add(FoldingDescriptor(element.getNode(), TextRange.create(offset, secondBracesRange.getEndOffset()), group, ""))
        if (secondBracesRange.getEndOffset() < firstBracesRange.getEndOffset() - 1) {
            descriptors.add(FoldingDescriptor(element.getNode(), TextRange.create(secondBracesRange.getEndOffset(), firstBracesRange.getEndOffset() - 1), group, ""))
        }
        descriptors.add(FoldingDescriptor(element.getNode(), TextRange.create(firstBracesRange.getEndOffset() - 1, firstBracesRange.getEndOffset()), group, "]"))
        if (firstBracesRange.getEndOffset() < getTextRange().getEndOffset()) {
            descriptors.add(FoldingDescriptor(element.getNode(), TextRange.create(firstBracesRange.getEndOffset(), getTextRange().getEndOffset()), group, ""))
        }
        val iterator = operands.iterator()
        while (iterator.hasNext()) {
            val operand = iterator.next()
            if (operand.supportsFoldRegions(document, this)) {
                Collections.addAll(descriptors, *operand.buildFoldRegions(operand.getElement(), document, this))
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
