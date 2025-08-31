package com.intellij.advancedExpressionFolding.expression.operation.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.property.Getter
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import java.util.ArrayList
import java.util.Collections

class OptionalNotNullAssertionGet(
    element: PsiElement,
    textRange: TextRange,
    private val `object`: Expression?
) : Expression(element, TextRange.create(textRange.startOffset - 1, textRange.endOffset + 2)) {

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.node,
                textRange,
                FoldingGroup.newGroup(Getter::class.java.name),
                "!!"
            )
        )
        if (`object` != null && `object`.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *`object`.buildFoldRegions(`object`.element, document, this)
            )
        }
        return descriptors.toTypedArray()
    }

    override fun isCollapsedByDefault() = true

    override fun supportsFoldRegions(document: Document, parent: Expression?) = true
}
