package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections

class ArrayGet(
    element: PsiElement,
    textRange: TextRange,
    obj: Expression
) : Expression(element, textRange) {
    private var objectExpression: Expression = obj

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ArrayGet::class.java.getName())
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(
                    objectExpression.getTextRange().getEndOffset(),
                    getTextRange().getEndOffset()
                ),
                group,
                "." + "last()"
            )
        )
        if (objectExpression.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *objectExpression.buildFoldRegions(objectExpression.getElement(), document, this)
            )
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }
}
