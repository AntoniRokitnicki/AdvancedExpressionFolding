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

class OptionalNotNullAssertionGet(element: PsiElement, textRange: TextRange, objectExpression: Expression?) : Expression(element, TextRange.create(textRange.startOffset - 1, textRange.endOffset + 2)) {
    private var objectExpression: Expression? = objectExpression

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        descriptors.add(
            FoldingDescriptor(
                element.node,
                getTextRange(),
                FoldingGroup.newGroup(Getter::class.java.name),
                "!!"
            )
        )
        if (objectExpression != null && objectExpression!!.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, *objectExpression!!.buildFoldRegions(objectExpression!!.getElement(), document, this))
        }
        return descriptors.toTypedArray()
    }

    override fun isCollapsedByDefault(): Boolean {
        return true
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return true
    }
}
