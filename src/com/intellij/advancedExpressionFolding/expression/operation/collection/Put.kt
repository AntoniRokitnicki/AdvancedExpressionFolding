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
    obj: Expression,
    key: Expression,
    value: Expression
) : Expression(element, textRange) {
    private var objectExpression: Expression = obj
    private var key: Expression = key
    private var value: Expression = value

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return objectExpression.getTextRange().getEndOffset() < this.key.getTextRange().getStartOffset()
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Put::class.java.getName())
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(objectExpression.getTextRange().getEndOffset(), key.getTextRange().getStartOffset()),
                group,
                "["
            )
        )
        descriptors.add(
            FoldingDescriptor(
                element.getNode(),
                TextRange.create(key.getTextRange().getEndOffset(), value.getTextRange().getStartOffset()),
                group,
                "] = "
            )
        )
        if (value.getTextRange().getEndOffset() < getTextRange().getEndOffset()) {
            descriptors.add(
                FoldingDescriptor(
                    element.getNode(),
                    TextRange.create(value.getTextRange().getEndOffset(), getTextRange().getEndOffset()),
                    group,
                    ""
                )
            )
        }
        if (objectExpression.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *objectExpression.buildFoldRegions(objectExpression.getElement(), document, this)
            )
        }
        if (key.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *key.buildFoldRegions(key.getElement(), document, this)
            )
        }
        if (value.supportsFoldRegions(document, this)) {
            Collections.addAll(
                descriptors,
                *value.buildFoldRegions(value.getElement(), document, this)
            )
        }
        return descriptors.toArray(EMPTY_ARRAY)
    }
}
