package com.intellij.advancedExpressionFolding.application.usecase

import com.intellij.advancedExpressionFolding.application.port.output.EditorPsiProvider
import com.intellij.advancedExpressionFolding.domain.service.KeyCleanupService
import com.intellij.openapi.editor.Editor

class ClearEditorKeysUseCase(
    private val editorPsiProvider: EditorPsiProvider,
    private val keyCleanupService: KeyCleanupService
) {
    fun execute(editor: Editor) {
        if (editor.isDisposed) return

        val psiFile = editorPsiProvider.getPsiFile(editor) ?: return
        keyCleanupService.clear(psiFile)
    }
}
