package com.intellij.advancedExpressionFolding.expression.semantic.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class IfNullSafePrintlnExpression(
    element: PsiElement,
    textRange: TextRange,
    private val placeholder: String,
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?,
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(IfNullSafePrintlnExpression::class.java.name)
        return arrayOf(FoldingDescriptor(element.node, textRange, group, placeholder))
    }
}
