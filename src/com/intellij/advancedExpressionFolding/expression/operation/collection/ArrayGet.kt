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
    private val `object`: Expression
) : Expression(element, textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?) = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ArrayGet::class.java.name)
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(`object`.textRange.endOffset, textRange.endOffset),
                group,
                ".last()"
            )
        )
        if (`object`.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *`object`.buildFoldRegions(`object`.element, document, this))
        }
        return descriptors.toTypedArray()
    }
}
