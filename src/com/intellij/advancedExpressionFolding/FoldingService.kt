package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.application.usecase.ClearEditorKeysUseCase
import com.intellij.advancedExpressionFolding.application.usecase.ToggleEditorFoldingUseCase
import com.intellij.advancedExpressionFolding.domain.model.FoldingState
import com.intellij.advancedExpressionFolding.domain.service.FoldingGroupToggler
import com.intellij.advancedExpressionFolding.domain.service.KeyCleanupService
import com.intellij.advancedExpressionFolding.infrastructure.editor.IntellijAdvancedExpressionKeyRepository
import com.intellij.advancedExpressionFolding.infrastructure.editor.IntellijEditorPsiProvider
import com.intellij.advancedExpressionFolding.infrastructure.editor.IntellijFoldingGroupRepository
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Service
class FoldingService(
    private val toggleEditorFoldingUseCase: ToggleEditorFoldingUseCase,
    private val clearEditorKeysUseCase: ClearEditorKeysUseCase
) {

    constructor() : this(
        ToggleEditorFoldingUseCase(
            IntellijFoldingGroupRepository(),
            FoldingGroupToggler()
        ),
        ClearEditorKeysUseCase(
            IntellijEditorPsiProvider(),
            KeyCleanupService(IntellijAdvancedExpressionKeyRepository())
        )
    )

    fun fold(editor: Editor, state: Boolean) {
        toggleEditorFoldingUseCase.execute(editor, FoldingState.fromCollapseFlag(state))
    }

    fun clearAllKeys() {
        ProjectManager.getInstance().openProjects.forEach(this::clearAllKeys)
    }

    fun clearAllKeys(project: Project) {
        val editors = project.openTextEditors

        CoroutineScope(Dispatchers.Default).launch {
            editors.forEach { editor ->
                clearAllKeys(editor)
            }
        }
    }

    fun clearAllKeys(editor: Editor) {
        clearEditorKeysUseCase.execute(editor)
    }

    companion object {
        fun get() = service<FoldingService>()
    }
}
