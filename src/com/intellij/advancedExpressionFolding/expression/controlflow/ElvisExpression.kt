package com.intellij.advancedExpressionFolding.expression.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList

class ElvisExpression(
    element: PsiElement,
    textRange: TextRange,
    private val thenExpression: Expression,
    private val elseExpression: Expression,
    private val elements: List<TextRange>,
    private val inverted: Boolean = false
) : Expression(element, textRange) {

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(ElvisExpression::class.java.name)
        val thenRange = thenExpression.textRange
        val elseRange = elseExpression.textRange
        if (!inverted) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, thenRange.startOffset),
                group,
                ""
            )
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(thenRange.endOffset, elseRange.startOffset),
                group,
                " ?: "
            )
        } else {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, thenRange.endOffset),
                group,
                "${buildInvertedNonNullText(document, thenRange)} ?: ${document.getText(elseRange)}"
            )
        }
        if (!inverted) {
            ShortElvisExpression.nullify(
                element,
                document,
                descriptors,
                group,
                elements,
                !(elements.size == 1 && elements[0] == thenExpression.textRange)
            )
        }
        if (thenExpression.supportsFoldRegions(document, this)) {
            descriptors += thenExpression.buildFoldRegions(thenExpression.element, document, this).toList()
        }
        if (elseExpression.supportsFoldRegions(document, this)) {
            descriptors += elseExpression.buildFoldRegions(elseExpression.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    private fun buildInvertedNonNullText(document: Document, range: TextRange): String {
        return ShortElvisExpression.buildNullifiedText(
            document,
            range,
            elements,
            !(elements.size == 1 && elements[0] == thenExpression.textRange)
        )
    }
}
