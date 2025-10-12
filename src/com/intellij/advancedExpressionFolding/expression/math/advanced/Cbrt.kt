package com.intellij.advancedExpressionFolding.expression.math.advanced

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList

class Cbrt(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Function(element, textRange, "∛", operands), ArithmeticExpression {
    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(Cbrt::class.java.name)
        val operand = operands[0]
        val prefix = if (operand is NumberLiteral || operand is Variable) "∛" else "∛("
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, operand.textRange.startOffset),
                group,
                prefix
            )
        )
        val suffix = if (operand is NumberLiteral || operand is Variable) "" else ")"
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(operand.textRange.endOffset, textRange.endOffset),
                group,
                suffix
            )
        )
        if (operand.supportsFoldRegions(document, this)) {
            descriptors.addAll(operand.buildFoldRegions(operand.element, document, this).toList())
        }
        return descriptors.toTypedArray()
    }
}
