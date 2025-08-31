package com.intellij.advancedExpressionFolding.expression.math.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Abs(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
    Function(element, textRange, "abs", operands), ArithmeticExpression {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return textRange.startOffset < operands[0].textRange.startOffset &&
            operands[0].textRange.endOffset < textRange.endOffset
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(Abs::class.java.name)
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, operands[0].textRange.startOffset),
                group,
                "|"
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(operands[0].textRange.endOffset, textRange.endOffset),
                group,
                "|"
            )
        )
        if (operands[0].supportsFoldRegions(document, this)) {
            descriptors.addAll(operands[0].buildFoldRegions(operands[0].element, document, this))
        }
        return descriptors.toTypedArray()
    }
}

