package com.intellij.advancedExpressionFolding.integration

import com.intellij.advancedExpressionFolding.isAdvancedExpressionFoldingGroup
import com.intellij.advancedExpressionFolding.openTextEditors
import com.intellij.codeInsight.folding.CodeFoldingManager
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.ProjectManager
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.atomic.AtomicReference

@TestOnly
object IntegrationTestApi {
    private const val GLOBAL_TOGGLE_ACTION_ID =
        "com.intellij.advancedExpressionFolding.action.GlobalToggleFoldingAction"

    @JvmStatic
    fun toggleGlobalFolding(state: Boolean) {
        runOnEdt {
            val actionManager = ActionManager.getInstance()
            val action = actionManager.getAction(GLOBAL_TOGGLE_ACTION_ID) as? ToggleAction
                ?: error("Action $GLOBAL_TOGGLE_ACTION_ID not found")
            val presentation = action.templatePresentation.clone()
            val event = AnActionEvent(
                null,
                DataContext.EMPTY_CONTEXT,
                ActionPlaces.UNKNOWN,
                presentation,
                actionManager,
                0
            )
            action.setSelected(event, state)
            refreshOpenEditors()
        }
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
