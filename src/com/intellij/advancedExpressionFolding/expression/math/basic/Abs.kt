package com.intellij.advancedExpressionFolding.expression.math.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Abs(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Function(element, textRange, "abs", operands), ArithmeticExpression {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return getTextRange().getStartOffset() <
            getOperands().get(0).getTextRange().getStartOffset() &&
            getOperands().get(0).getTextRange().getEndOffset() <
            getTextRange().getEndOffset()
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = java.util.ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(Abs::class.java.getName())
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(
                    getTextRange().getStartOffset(),
                    getOperands().get(0).getTextRange().getStartOffset()
                ),
                group,
                "|"
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(
                    getOperands().get(0).getTextRange().getEndOffset(),
                    getTextRange().getEndOffset()
                ),
                group,
                "|"
            )
        )
        if (getOperands().get(0).supportsFoldRegions(document, this)) {
            java.util.Collections.addAll(
                descriptors,
                *getOperands().get(0).buildFoldRegions(
                    getOperands().get(0).getElement(),
                    document,
                    this
                )
            )
        }
        return descriptors.toTypedArray()
    }
}

