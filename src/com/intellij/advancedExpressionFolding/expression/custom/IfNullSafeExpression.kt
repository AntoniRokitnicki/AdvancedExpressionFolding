package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class IfNullSafeExpression(element: PsiElement, textRange: TextRange, val character: String) : Expression(element, textRange) {

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(IfNullSafeExpression::class.java.name)
        descriptors.add(FoldingDescriptor(element.node, textRange, group, character))
        return descriptors.toTypedArray()
    }


    fun buildFoldRegion(): MutableList<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        val group = FoldingGroup.newGroup(IfNullSafeExpression::class.java.name)
        descriptors.add(FoldingDescriptor(element.node, textRange, group, character))
        return descriptors
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
