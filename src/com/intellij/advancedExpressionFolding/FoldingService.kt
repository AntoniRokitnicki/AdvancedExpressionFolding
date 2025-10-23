package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.application.service.FoldingToggleApplicationService
import com.intellij.advancedExpressionFolding.application.service.KeyCleanupApplicationService
import com.intellij.advancedExpressionFolding.domain.model.FoldingToggleState
import com.intellij.advancedExpressionFolding.domain.service.FoldingDomainService
import com.intellij.advancedExpressionFolding.domain.service.KeyClearanceDomainService
import com.intellij.advancedExpressionFolding.infrastructure.editor.EditorFoldingBatchExecutor
import com.intellij.advancedExpressionFolding.infrastructure.editor.EditorFoldingGroupFactory
import com.intellij.advancedExpressionFolding.infrastructure.editor.ProjectOpenEditorsProvider
import com.intellij.advancedExpressionFolding.infrastructure.psi.PsiKeyAwareElementProvider
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Service
class FoldingService {

    private val foldingToggleService = FoldingToggleApplicationService(
        EditorFoldingBatchExecutor(),
        EditorFoldingGroupFactory(),
        FoldingDomainService()
    )

    private val keyCleanupService = KeyCleanupApplicationService(
        ProjectOpenEditorsProvider(),
        PsiKeyAwareElementProvider(),
        KeyClearanceDomainService()
    )

    fun fold(editor: Editor, state: Boolean) {
        foldingToggleService.toggle(editor, FoldingToggleState.from(state))
    }

    fun clearAllKeys() {
        ProjectManager.getInstance().openProjects.forEach(this::clearAllKeys)
    }

    fun clearAllKeys(project: Project) {
        CoroutineScope(Dispatchers.Default).launch {
            keyCleanupService.clear(project)
        }
    }

    fun clearAllKeys(editor: Editor) {
        keyCleanupService.clear(editor)
    }

    companion object {
        fun get() = service<FoldingService>()
    }
}
