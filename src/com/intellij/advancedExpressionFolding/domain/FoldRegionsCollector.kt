package com.intellij.advancedExpressionFolding.domain

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

interface FoldRegionsCollector {
    fun collect(element: PsiElement, document: Document): Array<FoldingDescriptor>
    fun preview(element: PsiElement, document: Document): List<String>
    fun isFoldingFile(element: PsiElement): Boolean
    fun isCollapsedByDefault(astNode: ASTNode): Boolean
}
