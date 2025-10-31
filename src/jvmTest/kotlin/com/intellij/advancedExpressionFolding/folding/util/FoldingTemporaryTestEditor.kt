package com.intellij.advancedExpressionFolding.folding.util

import com.intellij.advancedExpressionFolding.diff.DiffFoldingTemporaryEditor
import com.intellij.advancedExpressionFolding.diff.FoldedCode
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.Computable
import com.intellij.testFramework.runInEdtAndGet

object FoldingTemporaryTestEditor {
    fun getFoldedText(
        text: String,
        wrapper: FoldingDescriptorExWrapper
    ): FoldedCode {
        return runInEdtAndGet {
            ApplicationManager.getApplication().runWriteAction(Computable {
                DiffFoldingTemporaryEditor.foldInEditor(text, wrapper.list)
            })
        }
    }
}
