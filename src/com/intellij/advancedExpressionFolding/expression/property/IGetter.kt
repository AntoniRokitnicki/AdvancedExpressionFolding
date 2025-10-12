package com.intellij.advancedExpressionFolding.expression.property

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

interface IGetter : INameable {
    override val name: String
    val getterTextRange: TextRange
    val `object`: Expression?

    fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor>
}
