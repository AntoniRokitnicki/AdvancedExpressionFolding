package com.intellij.advancedExpressionFolding.application.usecase

import com.intellij.advancedExpressionFolding.application.port.output.FoldingGroupRepository
import com.intellij.advancedExpressionFolding.domain.model.FoldingState
import com.intellij.advancedExpressionFolding.domain.service.FoldingGroupToggler
import com.intellij.openapi.editor.Editor

class ToggleEditorFoldingUseCase(
    private val foldingGroupRepository: FoldingGroupRepository,
    private val foldingGroupToggler: FoldingGroupToggler
) {
    fun execute(editor: Editor, state: FoldingState) {
        if (editor.isDisposed) return

        val groups = foldingGroupRepository.load(editor)
        if (groups.isEmpty()) return

        editor.foldingModel.runBatchFoldingOperation {
            foldingGroupToggler.toggle(groups, state)
        }
    }
}
