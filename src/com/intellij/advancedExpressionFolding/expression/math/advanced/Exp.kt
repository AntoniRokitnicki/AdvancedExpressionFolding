package com.intellij.advancedExpressionFolding.expression.math.advanced

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList

class Exp(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Function(element, textRange, "exp", operands), ArithmeticExpression {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return Helper.superscript(operands[0].element.text) != null
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(Exp::class.java.name)
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(textRange),
                group,
                "\uD835\uDC52" + Helper.superscript(operands[0].element.text)
            )
        )
        return descriptors.toTypedArray()
    }
}
