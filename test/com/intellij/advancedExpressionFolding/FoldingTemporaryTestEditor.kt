package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.diff.FoldedCode
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper
import com.intellij.advancedExpressionFolding.diff.FoldingTemporaryEditor
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.Computable
import com.intellij.testFramework.runInEdtAndGet

object FoldingTemporaryTestEditor {
    fun getEditorFoldedText(
        text: String,
        wrapper: FoldingDescriptorExWrapper
    ): FoldedCode {
        return runInEdtAndGet {
            ApplicationManager.getApplication().runWriteAction(Computable {
                FoldingTemporaryEditor.foldInEditor(text, wrapper.list)
            })
        }
    }

    fun getFoldedText(
        text: String,
        wrapper: FoldingDescriptorExWrapper
    ): FoldedCode {
        val normalized = normalizeLineEndings(stripFoldingMarkers(text))
        if (wrapper.list.isEmpty()) {
            return normalized
        }

        val folded = getEditorFoldedText(normalized, wrapper)
        return normalizeLineEndings(folded)
    }

    private fun normalizeLineEndings(text: String): String {
        return text
            .replace("\r\n", "\n")
            .replace("\r", "\n")
    }

    private fun stripFoldingMarkers(text: String): String {
        val regex = Regex("<${FOLD}\\stext='[^']*'(\\sexpand='[^']*')*>")
        return text
            .replace(regex, "")
            .replace("</$FOLD>", "")
    }

    private const val FOLD = "fold"
}