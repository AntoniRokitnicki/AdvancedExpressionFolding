package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper
import com.intellij.advancedExpressionFolding.diff.FoldingTemporaryEditor
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.Computable
import com.intellij.testFramework.runInEdtAndGet

object FoldingTemporaryTestEditor {
    fun getFoldedText(
        text: String,
        wrapper: FoldingDescriptorExWrapper
    ): String {
        return runInEdtAndGet {
            ApplicationManager.getApplication().runWriteAction(Computable {
                FoldingTemporaryEditor.foldInEditor(text, wrapper.list)
            })
        }
    }
}