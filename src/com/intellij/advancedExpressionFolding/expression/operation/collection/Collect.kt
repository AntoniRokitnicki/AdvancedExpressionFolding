package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Collect(
    element: PsiElement,
    textRange: TextRange,
    val qualifier: Expression,
    val collectorTextRange: TextRange
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
        val descriptors = mutableListOf<FoldingDescriptor>()
        val offset = Helper.findDot(document, textRange.startOffset, -1, false)
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(textRange.startOffset + offset, collectorTextRange.startOffset),
            group,
            "."
        )
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(collectorTextRange.endOffset, textRange.endOffset),
            group,
            ""
        )
        if (qualifier.supportsFoldRegions(document, this)) {
            descriptors += qualifier.buildFoldRegions(qualifier.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }
}
