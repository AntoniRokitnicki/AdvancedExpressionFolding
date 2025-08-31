package com.intellij.advancedExpressionFolding.expression

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class VariableDeclarationImpl(
    element: PsiElement,
    textRange: TextRange,
    private val isFinal: Boolean
) : Expression(element, textRange) {

    fun isFinal(): Boolean = isFinal

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(VariableDeclarationImpl::class.java.name)
        return arrayOf(FoldingDescriptor(element.node, textRange, group, if (isFinal) "val" else "var"))
    }
}

