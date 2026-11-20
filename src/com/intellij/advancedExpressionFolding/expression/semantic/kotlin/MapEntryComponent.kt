package com.intellij.advancedExpressionFolding.expression.semantic.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.property.INameable
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethodCallExpression

/// `entry.getKey()` / `entry.getValue()` inside a destructured map loop, shown as the bare `key` / `value`.
class MapEntryComponent(
    element: PsiMethodCallExpression,
    override val name: String,
) : Expression(element, element.textRange), INameable {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> = arrayOf(
        FoldingDescriptor(element.node, textRange, FoldingGroup.newGroup(MapEntryComponent::class.java.name), name)
    )
}
