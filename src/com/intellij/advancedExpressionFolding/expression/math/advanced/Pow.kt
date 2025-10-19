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
import java.util.ArrayList

class Pow(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Function(element, textRange, "pow", operands), ArithmeticExpression {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return operands[0].textRange.endOffset < textRange.endOffset &&
            Helper.superscript(operands[1].element.text) != null
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(Pow::class.java.name)
        val base = operands[0]
        if (textRange.startOffset < base.textRange.startOffset) {
            val placeholder = if (base is Operation) "(" else ""
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(textRange.startOffset, base.textRange.startOffset),
                    group,
                    placeholder
                )
            )
        }
        if (base.supportsFoldRegions(document, this)) {
            descriptors.addAll(base.buildFoldRegions(base.element, document, this).toList())
        }
        val superscript = Helper.superscript(operands[1].element.text)
        if (superscript != null) {
            val placeholder = if (base is Operation) ")" + superscript else superscript
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(base.textRange.endOffset, textRange.endOffset),
                    group,
                    placeholder
                )
            )
        }
        return descriptors.toTypedArray()
    }
}
