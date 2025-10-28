package com.intellij.advancedExpressionFolding.integration

import com.intellij.advancedExpressionFolding.isAdvancedExpressionFoldingGroup
import com.intellij.advancedExpressionFolding.openTextEditors
import com.intellij.codeInsight.folding.CodeFoldingManager
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.actionSystem.impl.SimpleDataContext
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.application.runInEdt
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.atomic.AtomicReference

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
        action.actionPerformed(event)
    }

    @JvmStatic
    fun countAdvancedFoldRegions(): Int {
        return computeOnEdt {
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
    }

    private fun refreshOpenEditors() {
        ProjectManager.getInstance().openProjects.forEach { project ->
            val foldingManager = CodeFoldingManager.getInstance(project)
            project.openTextEditors.forEach { editor ->
                foldingManager.updateFoldRegions(editor)
            }
        }
    }

    private inline fun runOnEdt(crossinline action: () -> Unit) {
        val application = ApplicationManager.getApplication()
        if (application.isDispatchThread) {
            action()
        } else {
            application.invokeAndWait({ action() }, ModalityState.defaultModalityState())
        }
    }

    private inline fun <T> computeOnEdt(noinline action: () -> T): T {
        val reference = AtomicReference<T?>()
        runOnEdt {
            reference.set(action())
        }
        return reference.get()
            ?: error("Failed to compute value on EDT")
    }
}
