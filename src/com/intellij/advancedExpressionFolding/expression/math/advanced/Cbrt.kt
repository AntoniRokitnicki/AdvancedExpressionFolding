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
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, operands[0].textRange.startOffset),
                group,
                if (operands[0] is NumberLiteral || operands[0] is Variable) "∛" else "∛("
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(operands[0].textRange.endOffset, textRange.endOffset),
                group,
                if (operands[0] is NumberLiteral || operands[0] is Variable) "" else ")"
            )
        )
        if (operands[0].supportsFoldRegions(document, this)) {
            descriptors.addAll(operands[0].buildFoldRegions(operands[0].element, document, this))
        }
        return descriptors.toTypedArray()
    }
}

