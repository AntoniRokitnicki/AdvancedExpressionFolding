package com.intellij.advancedExpressionFolding.expression.math.advanced

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.advancedExpressionFolding.processor.util.Helper
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

class Pow(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
    Function(element, textRange, "pow", operands), ArithmeticExpression {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return operands.get(0).getTextRange().getEndOffset() < getTextRange().getEndOffset() &&
            Helper.superscript(operands.get(1).getElement().getText()) != null
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(Pow::class.java.getName())
        if (getTextRange().getStartOffset() < operands.get(0).getTextRange().getStartOffset()) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(
                        getTextRange().getStartOffset(),
                        operands.get(0).getTextRange().getStartOffset()
                    ),
                    group,
                    if (operands.get(0) is Operation) "(" else ""
                )
            )
        }
        if (operands.get(0).supportsFoldRegions(document, this)) {
            for (d in operands.get(0).buildFoldRegions(operands.get(0).getElement(), document, this)) {
                descriptors.add(d)
            }
        }
        val b = operands.get(1).getElement().getText()
        val superscript = Helper.superscript(b)
        if (superscript != null) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(
                        operands.get(0).getTextRange().getEndOffset(),
                        getTextRange().getEndOffset()
                    ),
                    group,
                    if (operands.get(0) is Operation) ")" + superscript else superscript
                )
            )
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }
}
