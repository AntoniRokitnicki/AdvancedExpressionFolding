package com.intellij.advancedExpressionFolding.expression.operation.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections

class Put(
    element: PsiElement,
    textRange: TextRange,
    private val `object`: Expression,
    private val key: Expression,
    private val value: Expression
) : Expression(element, textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return this.`object`.textRange.endOffset < this.key.textRange.startOffset
    }

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Put::class.java.name)
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(`object`.textRange.endOffset, key.textRange.startOffset),
                group,
                "["
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(key.textRange.endOffset, value.textRange.startOffset),
                group,
                "] = "
            )
        )
        if (value.textRange.endOffset < textRange.endOffset) {
            descriptors.add(
                FoldingDescriptor(
                    element.node,
                    TextRange.create(value.textRange.endOffset, textRange.endOffset),
                    group,
                    ""
                )
            )
        }
        if (`object`.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *`object`.buildFoldRegions(`object`.element, document, this))
        }
        if (key.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *key.buildFoldRegions(key.element, document, this))
        }
        if (value.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *value.buildFoldRegions(value.element, document, this))
        }
        return descriptors.toTypedArray()
    }
}
