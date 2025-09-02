package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression.Companion.EMPTY_ARRAY
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections
import java.util.List

class ElvisExpression(element: PsiElement, textRange: TextRange, thenExpression: Expression, elseExpression: Expression, elements: List<TextRange>) :
    Expression(element, textRange) {
    private var thenExpression: Expression = thenExpression
    private var elseExpression: Expression = elseExpression
    private var elements: List<TextRange> = elements

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(ElvisExpression::class.java.getName())
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(getTextRange().getStartOffset(), thenExpression.getTextRange().getStartOffset()),
                group,
                ""
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(thenExpression.getTextRange().getEndOffset(), elseExpression.getTextRange().getStartOffset()),
                group,
                " ?: "
            )
        )
        ShortElvisExpression.nullify(element, document, descriptors, group, elements, !(elements.size == 1 && elements.get(0).equals(thenExpression.getTextRange())))
        if (thenExpression.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *thenExpression.buildFoldRegions(thenExpression.getElement(), document, this))
        }
        if (elseExpression.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *elseExpression.buildFoldRegions(elseExpression.getElement(), document, this))
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }
}
