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
    private val qualifier: Expression,
    private val collectorTextRange: TextRange
) : Expression(element, textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        val offset = Helper.findDot(document, textRange.startOffset, -1, false)
        return textRange.startOffset + offset < collectorTextRange.startOffset &&
            collectorTextRange.endOffset < textRange.endOffset
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Collect::class.java.name)
        val descriptors = ArrayList<FoldingDescriptor>()
        val offset = Helper.findDot(document, textRange.startOffset, -1, false)
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset + offset, collectorTextRange.startOffset),
                group,
                "."
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(collectorTextRange.endOffset, textRange.endOffset),
                group,
                ""
            )
        )
        if (qualifier.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *qualifier.buildFoldRegions(qualifier.element, document, this))
        }
        return descriptors.toTypedArray()
    }
}
