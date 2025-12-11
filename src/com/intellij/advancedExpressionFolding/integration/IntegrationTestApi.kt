package com.intellij.advancedExpressionFolding.integration

import com.intellij.advancedExpressionFolding.isAdvancedExpressionFoldingGroup
import com.intellij.advancedExpressionFolding.openTextEditors
import com.intellij.codeInsight.folding.CodeFoldingManager
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.actionSystem.ex.ActionUtil
import com.intellij.openapi.actionSystem.impl.SimpleDataContext
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.application.runInEdt
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import org.jetbrains.annotations.TestOnly

@TestOnly
object IntegrationTestApi {
    @JvmStatic
    fun toggleGlobalFolding(state: Boolean) {
        runInEdt {
            val project = ProjectManager.getInstance().openProjects.firstOrNull { !it.isDisposed }
                ?: return@runInEdt
            val ctx = SimpleDataContext.getProjectContext(project)
            val actionId = if (state) "AdvancedFolding.EnableAll" else "AdvancedFolding.DisableAll"
            fireAction(actionId, project, ctx)
            refreshOpenEditors()
        }
    }

    private fun fireAction(actionId: String, project: Project, dataContext: DataContext) {
        if (project.isDisposed) {
            return
        }
        val action = ActionManager.getInstance().getAction(actionId) ?: return
        val event = AnActionEvent.createFromDataContext(
            ActionPlaces.UNKNOWN,
            Presentation(),
            dataContext
        )
        ActionUtil.performActionDumbAwareWithCallbacks(action, event)
    }

    @JvmStatic
    fun countAdvancedFoldRegions(): Int {
        val application = ApplicationManager.getApplication()
        val compute = {
            runReadAction {
                ProjectManager.getInstance().openProjects
                    .asSequence()
                    .mapNotNull { project ->
                        FileEditorManager.getInstance(project).selectedTextEditor
                    }
                    .firstOrNull { !it.isDisposed }
                    ?.foldingModel
                    ?.allFoldRegions
                    ?.count { fold -> fold.isAdvancedExpressionFoldingGroup }
                    ?: 0
            }
        }
        return if (application.isDispatchThread) {
            compute()
        } else {
            var result = 0
            application.invokeAndWait({
                result = compute()
            }, ModalityState.defaultModalityState())
            result
        }
    }

    private fun refreshOpenEditors() {
        ProjectManager.getInstance().openProjects.forEach { project ->
            val foldingManager = CodeFoldingManager.getInstance(project)
            project.openTextEditors.forEach { editor ->
                foldingManager.updateFoldRegions(editor)
            }
        }
    }
}
