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

class ArrayStream(
    element: PsiElement,
    textRange: TextRange,
    private val argument: Expression
) : Expression(element, textRange) {
    override fun supportsFoldRegions(document: Document, parent: Expression?) = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val offset = Helper.findDot(document, textRange.endOffset, 1, false) + 1
        val noSpaces = offset == 1
        val group = FoldingGroup.newGroup(
            ArrayStream::class.java.name + if (noSpaces) "" else Expression.HIGHLIGHTED_GROUP_POSTFIX
        )
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, argument.textRange.startOffset),
                group,
                ""
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.node,
                TextRange.create(argument.textRange.endOffset, textRange.endOffset + if (noSpaces) 1 else 0),
                group,
                if (noSpaces) "." else ""
            )
        )
        if (argument.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *argument.buildFoldRegions(argument.element, document, this))
        }
        return descriptors.toTypedArray()
    }

    override fun isHighlighted() = true
}
