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

class Pow(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
    Function(element, textRange, "pow", operands), ArithmeticExpression {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return operands[0].textRange.endOffset < textRange.endOffset &&
            Helper.superscript(operands[1].element.text) != null
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(Pow::class.java.name)
        if (textRange.startOffset < operands[0].textRange.startOffset) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(textRange.startOffset, operands[0].textRange.startOffset),
                    group,
                    if (operands[0] is Operation) "(" else ""
                )
            )
        }
        if (operands[0].supportsFoldRegions(document, this)) {
            descriptors.addAll(operands[0].buildFoldRegions(operands[0].element, document, this))
        }
        val b = operands[1].element.text
        val superscript = Helper.superscript(b)
        if (superscript != null) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(operands[0].textRange.endOffset, textRange.endOffset),
                    group,
                    if (operands[0] is Operation) ")" + superscript else superscript
                )
            )
        }
        return descriptors.toTypedArray()
    }
}

