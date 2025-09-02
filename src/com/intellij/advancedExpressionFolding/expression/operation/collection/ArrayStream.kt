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
    argument: Expression
) : Expression(element, textRange) {
    private var argument: Expression = argument

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val offset = Helper.findDot(document, getTextRange().getEndOffset(), 1, false) + 1
        val noSpaces = offset == 1
        val group = FoldingGroup.newGroup(ArrayStream::class.java.getName() + if (noSpaces) "" else Expression.HIGHLIGHTED_GROUP_POSTFIX)
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(getTextRange().getStartOffset(), argument.getTextRange().getStartOffset()),
                group,
                ""
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(
                    argument.getTextRange().getEndOffset(),
                    getTextRange().getEndOffset() + if (noSpaces) 1 else 0
                ),
                group,
                if (noSpaces) "." else ""
            )
        )
        if (argument.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *argument.buildFoldRegions(argument.getElement(), document, this)
            )
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }

    override fun isHighlighted(): Boolean {
        return true
    }
}
