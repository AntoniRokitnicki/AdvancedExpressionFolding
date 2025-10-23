package com.intellij.advancedExpressionFolding.application.port.out

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

interface FoldingCache {
    fun read(element: PsiElement, quick: Boolean, document: Document): Array<FoldingDescriptor>?
    fun write(element: PsiElement, foldingDescriptors: Array<FoldingDescriptor>)
}
