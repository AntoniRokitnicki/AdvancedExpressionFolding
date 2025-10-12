package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class ArrayGet(
    element: PsiElement,
    textRange: TextRange,
    val `object`: Expression
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(ArrayGet::class.java.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(`object`.textRange.endOffset, textRange.endOffset),
            group,
            ".last()"
        )
        if (`object`.supportsFoldRegions(document, this)) {
            descriptors += `object`.buildFoldRegions(`object`.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }
}
