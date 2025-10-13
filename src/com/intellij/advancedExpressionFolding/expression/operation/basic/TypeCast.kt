package com.intellij.advancedExpressionFolding.expression.operation.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class TypeCast(
    element: PsiElement,
    textRange: TextRange,
    private val objectExpression: Expression
) : Expression(element, textRange) {

    fun getObject(): Expression = objectExpression

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val dotAccess = document.textLength > textRange.endOffset &&
            document.getText(TextRange.create(textRange.endOffset, textRange.endOffset + 1)) == "."
        val group = FoldingGroup.newGroup(TypeCast::class.java.name + if (dotAccess) "" else HIGHLIGHTED_GROUP_POSTFIX)
        val descriptors = mutableListOf<FoldingDescriptor>()
        if (objectExpression.textRange.endOffset < textRange.endOffset) {
            if (dotAccess) {
                descriptors += FoldingDescriptor(
                    element.node,
                    TextRange.create(textRange.startOffset, objectExpression.textRange.startOffset),
                    group,
                    ""
                )
                descriptors += FoldingDescriptor(
                    element.node,
                    TextRange.create(objectExpression.textRange.endOffset, textRange.endOffset + 1),
                    group,
                    "."
                )
            } else {
                descriptors += FoldingDescriptor(
                    element.node,
                    TextRange.create(textRange.startOffset, objectExpression.textRange.startOffset),
                    group,
                    ""
                )
                descriptors += FoldingDescriptor(
                    element.node,
                    TextRange.create(objectExpression.textRange.endOffset, textRange.endOffset),
                    group,
                    ""
                )
            }
        } else {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(textRange.startOffset, objectExpression.textRange.startOffset),
                group,
                ""
            )
        }
        if (objectExpression.supportsFoldRegions(document, this)) {
            descriptors += objectExpression.buildFoldRegions(objectExpression.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }

    override fun isHighlighted(): Boolean = true
}
