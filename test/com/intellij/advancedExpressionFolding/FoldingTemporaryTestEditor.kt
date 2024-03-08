package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper
import com.intellij.advancedExpressionFolding.diff.FoldingTemporaryEditor
import com.intellij.openapi.application.ApplicationManager
import com.intellij.testFramework.runInEdtAndGet

object FoldingTemporaryTestEditor {
    fun getFoldedText(
        text: String,
        wrapper: FoldingDescriptorExWrapper
    ): String {
        var changedText: String? = null
        //TODO: cant pass as reference, because its inline function
        runInEdtAndGet {
            ApplicationManager.getApplication().runWriteAction {
                changedText = FoldingTemporaryEditor.foldInEditor(text, wrapper.list)
            }
        }
        return changedText!!
    }
}