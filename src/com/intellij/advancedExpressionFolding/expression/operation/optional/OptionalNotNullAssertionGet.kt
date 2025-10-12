package com.intellij.advancedExpressionFolding.expression.operation.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.property.Getter
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class OptionalNotNullAssertionGet(
    element: PsiElement,
    textRange: TextRange,
    private val receiver: Expression?
) : Expression(element, TextRange.create(textRange.startOffset - 1, textRange.endOffset + 2)) {

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        descriptors += FoldingDescriptor(
            element.node,
            textRange,
            FoldingGroup.newGroup(Getter::class.java.name),
            "!!"
        )
        receiver?.takeIf { it.supportsFoldRegions(document, this) }?.let { obj ->
            descriptors += obj.buildFoldRegions(obj.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }

    override fun isCollapsedByDefault(): Boolean = true

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true
}
