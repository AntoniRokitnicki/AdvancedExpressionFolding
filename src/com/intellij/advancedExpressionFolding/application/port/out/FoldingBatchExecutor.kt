package com.intellij.advancedExpressionFolding.application.port.out

import com.intellij.openapi.editor.Editor

fun interface FoldingBatchExecutor {
    fun execute(editor: Editor, operation: () -> Unit)
}
