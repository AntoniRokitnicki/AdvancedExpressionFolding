package com.intellij.advancedExpressionFolding.integration

import com.intellij.codeInsight.folding.CodeFoldingManager
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.ProjectManager
import java.util.concurrent.atomic.AtomicReference

object IntegrationTestApi {
    private const val GLOBAL_TOGGLE_ACTION_ID =
        "com.intellij.advancedExpressionFolding.action.GlobalToggleFoldingAction"

    @JvmStatic
    fun toggleGlobalFolding(state: Boolean) {
        runOnEdt {
            val action = ActionManager.getInstance().getAction(GLOBAL_TOGGLE_ACTION_ID) as? ToggleAction
                ?: error("Action $GLOBAL_TOGGLE_ACTION_ID not found")
            val event = AnActionEvent.createFromAnAction(
                action,
                null,
                ActionPlaces.UNKNOWN,
                DataContext.EMPTY_CONTEXT
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
                    ?.count { fold ->
                        fold.group?.toString()?.startsWith("com.intellij.advancedExpressionFolding") == true
                    }
                    ?: 0
            }
        }
    }

    private fun refreshOpenEditors() {
        ProjectManager.getInstance().openProjects.forEach { project ->
            val foldingManager = CodeFoldingManager.getInstance(project)
            FileEditorManager.getInstance(project).allEditors
                .mapNotNull { editor -> (editor as? TextEditor)?.editor }
                .filterNot { it.isDisposed }
                .forEach { editor ->
                    foldingManager.updateFoldRegions(editor)
                }
        }
    }

    private fun runOnEdt(action: () -> Unit) {
        val application = ApplicationManager.getApplication()
        if (application.isDispatchThread) {
            action()
        } else {
            application.invokeAndWait(action, ModalityState.defaultModalityState())
        }
    }

    private fun <T> computeOnEdt(action: () -> T): T {
        val reference = AtomicReference<T?>()
        runOnEdt {
            reference.set(action())
        }
        return reference.get()
            ?: error("Failed to compute value on EDT")
    }
}
