package com.intellij.advancedExpressionFolding.application.port.`in`

import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

/**
 * Driving port that exposes debugging preview of folding descriptors.
 */
fun interface PreviewFoldRegionsUseCase {
    fun preview(element: PsiElement, document: Document): List<String>
}
