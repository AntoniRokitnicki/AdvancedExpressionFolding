package com.intellij.advancedExpressionFolding.expression.property

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

interface IGetter : INameable {
    fun getName(): String
    fun getGetterTextRange(): TextRange
    fun getObject(): Expression?
    fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor>
}

