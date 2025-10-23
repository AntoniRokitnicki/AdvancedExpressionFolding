package com.intellij.advancedExpressionFolding.application.port.input

import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

data class PreviewFoldRegionsRequest(
    val element: PsiElement,
    val document: Document
)

fun interface PreviewFoldRegionsUseCase {
    fun preview(request: PreviewFoldRegionsRequest): List<String>
}
