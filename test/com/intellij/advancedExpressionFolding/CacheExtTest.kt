package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.cache.CacheExt.invalidateExpired
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiJavaFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CacheExtTest : BaseTest() {

    @Test
    fun cacheInvalidatesOnDocumentMutationWithoutTextAccess() {
        val psiFile = fixture.configureByText("Foo.java", "class Foo { int value = 1; }") as PsiJavaFile
        val baseDocument = fixture.getDocument(psiFile)
        val trackingDocument = TrackingDocument(baseDocument)

        psiFile.invalidateExpired(trackingDocument, synthetic = false)
        trackingDocument.resetAccessCount()

        WriteCommandAction.runWriteCommandAction(fixture.project) {
            baseDocument.insertString(baseDocument.textLength, " ")
            PsiDocumentManager.getInstance(fixture.project).commitDocument(baseDocument)
        }

        val expired = psiFile.invalidateExpired(trackingDocument, synthetic = false)

        assertTrue(expired, "Document changes must invalidate the cache")
        assertEquals(0, trackingDocument.textAccessCount, "Cache invalidation should not read the entire document text")
    }

    private class TrackingDocument(private val delegate: Document) : Document by delegate {
        var textAccessCount: Int = 0
            private set

        fun resetAccessCount() {
            textAccessCount = 0
        }

        override fun getText(): String {
            textAccessCount++
            return delegate.getText()
        }

        override fun getText(range: TextRange): String {
            textAccessCount++
            return delegate.getText(range)
        }

        override fun getCharsSequence(): CharSequence {
            textAccessCount++
            return delegate.getCharsSequence()
        }

        override fun getImmutableCharSequence(): CharSequence {
            textAccessCount++
            return delegate.getImmutableCharSequence()
        }
    }
}
