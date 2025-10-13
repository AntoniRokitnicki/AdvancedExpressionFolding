package com.intellij.advancedExpressionFolding.expression.literal

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class SetLiteral(
    element: PsiElement,
    textRange: TextRange,
    val firstBracesRange: TextRange,
    val secondBracesRange: TextRange,
    items: List<Expression>
) : Function(element, textRange, "Set.of", items) {

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(javaClass.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        if (operands.isEmpty()) {
            descriptors += FoldingDescriptor(element.node, textRange, group, "[]")
            return descriptors.toTypedArray()
        }
        var offset = textRange.startOffset
        var start = offset
        var end = firstBracesRange.startOffset
        if (start < end) {
            descriptors += FoldingDescriptor(element.node, TextRange.create(start, end), group, "[")
        }
        start = firstBracesRange.startOffset
        end = secondBracesRange.startOffset
        if (start < end) {
            descriptors += FoldingDescriptor(element.node, TextRange.create(start, end), group, "")
        }
        start = secondBracesRange.startOffset
        end = operands.first().textRange.startOffset
        if (start < end) {
            descriptors += FoldingDescriptor(element.node, TextRange.create(start, end), group, "")
        }
        offset = operands.first().textRange.endOffset
        for (i in 1 until operands.size) {
            start = offset
            end = operands[i].textRange.startOffset
            if (start < end) {
                val range = TextRange.create(start, end)
                val placeholder = ", "
                if (document.getText(range) != placeholder) {
                    descriptors += FoldingDescriptor(element.node, range, group, placeholder)
                }
            }
            offset = operands[i].textRange.endOffset
        }
        start = offset
        end = secondBracesRange.endOffset
        if (start < end) {
            descriptors += FoldingDescriptor(element.node, TextRange.create(start, end), group, "")
        }
        start = secondBracesRange.endOffset
        end = firstBracesRange.endOffset - 1
        if (start < end) {
            descriptors += FoldingDescriptor(element.node, TextRange.create(start, end), group, "")
        }
        start = firstBracesRange.endOffset - 1
        end = firstBracesRange.endOffset
        if (start < end) {
            descriptors += FoldingDescriptor(element.node, TextRange.create(start, end), group, "]")
        }
        start = firstBracesRange.endOffset
        end = textRange.endOffset
        if (start < end) {
            descriptors += FoldingDescriptor(element.node, TextRange.create(start, end), group, "")
        }
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                descriptors += operand.buildFoldRegions(operand.element, document, this).toList()
            }
        }
        return descriptors.toTypedArray()
    }
}
