package com.intellij.advancedExpressionFolding.analytics

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

@Service(Service.Level.APP)
class FoldingFeatureExtractorService {

    data class FoldingFeatures(
        val elementType: String,
        val textLength: Int,
        val lineCount: Int,
    )

    fun extract(element: PsiElement, document: Document): FoldingFeatures {
        val textRange = element.textRange
        val safeEndOffset = textRange.endOffset.coerceAtMost(document.textLength)
        val startLine = document.getLineNumber(textRange.startOffset)
        val endLine = document.getLineNumber(safeEndOffset)

        return FoldingFeatures(
            element.node?.elementType?.toString().orEmpty(),
            textRange.length,
            (endLine - startLine + 1).coerceAtLeast(1),
        )
    }

    companion object {
        fun getInstance(): FoldingFeatureExtractorService = service()
    }
}
