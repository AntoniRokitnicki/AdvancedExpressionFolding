package com.intellij.advancedExpressionFolding.application.service

import com.intellij.advancedExpressionFolding.application.port.out.FoldingBatchExecutor
import com.intellij.advancedExpressionFolding.application.port.out.FoldingGroupFactory
import com.intellij.advancedExpressionFolding.domain.model.FoldingToggleState
import com.intellij.advancedExpressionFolding.domain.service.FoldingDomainService
import com.intellij.openapi.editor.Editor

class FoldingToggleApplicationService(
    private val batchExecutor: FoldingBatchExecutor,
    private val groupFactory: FoldingGroupFactory,
    private val domainService: FoldingDomainService
) {
    fun toggle(editor: Editor, state: FoldingToggleState) {
        if (editor.isDisposed) {
            return
        }
        batchExecutor.execute(editor) {
            val group = groupFactory.create(editor)
            domainService.applyState(group, state)
        }
    }
}
