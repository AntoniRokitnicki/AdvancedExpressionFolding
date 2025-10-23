package com.intellij.advancedExpressionFolding.infrastructure.editor

import com.intellij.advancedExpressionFolding.application.port.out.FoldingBatchExecutor
import com.intellij.openapi.editor.Editor

class EditorFoldingBatchExecutor : FoldingBatchExecutor {
    override fun execute(editor: Editor, operation: () -> Unit) {
        editor.foldingModel.runBatchFoldingOperation(operation)
    }
}
