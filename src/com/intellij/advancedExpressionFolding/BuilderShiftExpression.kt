package com.intellij.advancedExpressionFolding

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class BuilderShiftExpression(element: PsiElement, textRange: TextRange, val character: String) : Expression(element, textRange) {
    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(BuilderShiftExpression::class.java.name)
        descriptors.add(FoldingDescriptor(element.node, textRange, group, character))
        return descriptors.toTypedArray()
    }

    override fun isCollapsedByDefault(): Boolean {
        return true
    }

    override fun supportsFoldRegions(
        document: Document,
        parent: Expression?
    ): Boolean {
        return true
    }
}
