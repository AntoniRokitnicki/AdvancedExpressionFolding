package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections

class Collect(
    element: PsiElement,
    textRange: TextRange,
    qualifier: Expression,
    collectorTextRange: TextRange
) : Expression(element, textRange) {
    private var qualifier: Expression = qualifier
    private var collectorTextRange: TextRange = collectorTextRange

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val offset = Helper.findDot(document, getTextRange().getStartOffset(), -1, false)
        return getTextRange().getStartOffset() + offset < collectorTextRange.getStartOffset() &&
            collectorTextRange.getEndOffset() < getTextRange().getEndOffset()
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Collect::class.java.getName())
        val descriptors = ArrayList<FoldingDescriptor>()
        val offset = Helper.findDot(document, getTextRange().getStartOffset(), -1, false)
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(getTextRange().getStartOffset() + offset, collectorTextRange.getStartOffset()),
                group,
                "."
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(collectorTextRange.getEndOffset(), getTextRange().getEndOffset()),
                group,
                ""
            )
        )
        if (qualifier.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *qualifier.buildFoldRegions(qualifier.getElement(), document, this)
            )
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }
}
