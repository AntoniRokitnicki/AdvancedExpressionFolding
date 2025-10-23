package com.intellij.advancedExpressionFolding.application.port.input

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

data class BuildFoldRegionsRequest(
    val element: PsiElement,
    val document: Document,
    val quick: Boolean
)

fun interface BuildFoldRegionsUseCase {
    fun build(request: BuildFoldRegionsRequest): Array<FoldingDescriptor>
}
