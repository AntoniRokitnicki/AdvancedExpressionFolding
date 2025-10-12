package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Put(
    element: PsiElement,
    textRange: TextRange,
    val `object`: Expression,
    val key: Expression,
    val value: Expression
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return `object`.textRange.endOffset < key.textRange.startOffset
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Put::class.java.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(`object`.textRange.endOffset, key.textRange.startOffset),
            group,
            "["
        )
        descriptors += FoldingDescriptor(
            element.node,
            TextRange.create(key.textRange.endOffset, value.textRange.startOffset),
            group,
            "] = "
        )
        if (value.textRange.endOffset < textRange.endOffset) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(value.textRange.endOffset, textRange.endOffset),
                group,
                ""
            )
        }
        if (`object`.supportsFoldRegions(document, this)) {
            descriptors += `object`.buildFoldRegions(`object`.element, document, this).toList()
        }
        if (key.supportsFoldRegions(document, this)) {
            descriptors += key.buildFoldRegions(key.element, document, this).toList()
        }
        if (value.supportsFoldRegions(document, this)) {
            descriptors += value.buildFoldRegions(value.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }
}
