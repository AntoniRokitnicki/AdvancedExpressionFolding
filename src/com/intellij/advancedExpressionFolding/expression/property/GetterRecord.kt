package com.intellij.advancedExpressionFolding.expression.property

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class GetterRecord(
    element: PsiElement,
    textRange: TextRange,
    override val getterTextRange: TextRange,
    override val receiver: Expression?,
    override val name: String
) : Expression(element, textRange), IGetter {
    override fun supportsFoldRegions(
        document: Document,
        parent: Expression?
    ): Boolean {
        return true
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val descriptors = arrayListOf<FoldingDescriptor>()
        descriptors += FoldingDescriptor(
            element.node,
            getterTextRange,
            FoldingGroup.newGroup(GetterRecord::class.java.name),
            name
        )
        receiver?.takeIf { it.supportsFoldRegions(document, this) }?.let { obj ->
            descriptors += obj.buildFoldRegions(obj.element, document, this).toList()
        }
        return descriptors.toTypedArray()
    }
}
