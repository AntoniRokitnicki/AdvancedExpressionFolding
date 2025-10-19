package com.intellij.advancedExpressionFolding.expression.math.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList

class Abs(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Function(element, textRange, "abs", operands), ArithmeticExpression {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val operandRange = operands[0].textRange
        return textRange.startOffset < operandRange.startOffset &&
            operandRange.endOffset < textRange.endOffset
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(Abs::class.java.name)
        val operand = operands[0]
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, operand.textRange.startOffset),
                group,
                "|"
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(operand.textRange.endOffset, textRange.endOffset),
                group,
                "|"
            )
        )
        if (operand.supportsFoldRegions(document, this)) {
            descriptors.addAll(operand.buildFoldRegions(operand.element, document, this).toList())
        }
        return descriptors.toTypedArray()
    }
}
