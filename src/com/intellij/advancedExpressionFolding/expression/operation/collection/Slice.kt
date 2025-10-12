package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Slice(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Function(element, textRange, "slice", operands) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Slice::class.java.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        val startOperand = operands[1]
        val startRangeEnd = if (startOperand is NumberLiteral && startOperand.number.toInt() == 0) {
            startOperand.textRange.endOffset
        } else {
            startOperand.textRange.startOffset
        }
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(operands[0].textRange.endOffset, startRangeEnd),
            group,
            "["
        )
        if (startOperand is NumberLiteral && startOperand.number.toInt() < 0) {
            val whitespaceRange = TextRange.create(startOperand.textRange.startOffset + 1, startOperand.textRange.startOffset + 2)
            if (document.getText(whitespaceRange) == " ") {
                descriptors += FoldingDescriptor(element.node, whitespaceRange, group, "")
            }
        }
        if (operands.size > 2) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(startOperand.textRange.endOffset, operands[2].textRange.startOffset),
                group,
                ":"
            )
            val endOperand = operands[2]
            if (endOperand is NumberLiteral && endOperand.number.toInt() < 0) {
                val whitespaceRange = TextRange.create(endOperand.textRange.startOffset + 1, endOperand.textRange.startOffset + 2)
                if (document.getText(whitespaceRange) == " ") {
                    descriptors += FoldingDescriptor(element.node, whitespaceRange, group, "")
                }
            }
        }
        val closingStart = if (operands.size > 2) {
            textRange.endOffset - 1
        } else {
            startOperand.textRange.endOffset
        }
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(closingStart, textRange.endOffset),
            group,
            if (operands.size == 2) ":]" else "]"
        )
        for (operand in operands) {
            if (operand.supportsFoldRegions(document, this)) {
                descriptors += operand.buildFoldRegions(operand.element, document, this).toList()
            }
        }
        return descriptors.toTypedArray()
    }
}
