package com.intellij.advancedExpressionFolding.application.port.`in`

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

/**
 * Driving port that exposes the main folding workflow to adapters.
 */
fun interface BuildFoldRegionsUseCase {
    fun buildFoldRegions(element: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor>
}
