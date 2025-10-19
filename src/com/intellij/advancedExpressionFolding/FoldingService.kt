package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiRecursiveElementVisitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections

@Service
class FoldingService {

    private val throttledRules: MutableSet<String> = Collections.synchronizedSet(mutableSetOf())

    fun fold(editor: Editor, state: Boolean) {
        if (editor.isDisposed) {
            return
        }
        val regions = editor.foldingModel.allFoldRegions.filter {
            it.group?.toString()?.startsWith("com.intellij.advancedExpressionFolding") ?: false
        }

        editor.foldingModel
            .runBatchFoldingOperation {
                regions.forEach {
                    it.isExpanded = !state
                }
            }
    }

    fun clearAllKeys() {
        ProjectManager.getInstance().openProjects.forEach(this::clearAllKeys)
    }

    fun clearAllKeys(project: Project) {
        val editors = FileEditorManager.getInstance(project).allEditors.mapNotNull {
            (it as? TextEditor)?.editor
        }.filter {
            !it.isDisposed
        }

        CoroutineScope(Dispatchers.Default).launch {
            editors.forEach { editor ->
                clearAllKeys(editor)
            }
        }
    }

    fun clearAllKeys(editor: Editor) {
        if (editor.isDisposed) {
            return
        }
        val project = editor.project ?: return
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document) ?: return
        psiFile.accept(KeyCleanerPsiElementVisitor())
    }

    class KeyCleanerPsiElementVisitor : PsiRecursiveElementVisitor() {
        override fun visitElement(element: PsiElement) {
            Keys.clearAll(element)
            super.visitElement(element)
        }
    }

    fun notifyRuleThrottled(project: Project?, flag: String) {
        if (!throttledRules.add(flag)) {
            return
        }
        val group = NotificationGroupManager.getInstance().getNotificationGroup(NOTIFICATION_GROUP_ID)
        val notification = group.createNotification(
            "Advanced Expression Folding",
            "Rule '$flag' paused due to slow folding performance.",
            NotificationType.WARNING
        )
        notification.addAction(object : AnAction("Resume now") {
            override fun actionPerformed(e: AnActionEvent) {
                val settings = AdvancedExpressionFoldingSettings.getInstance().state
                if (settings.setAutoDisabled(flag, false)) {
                    throttledRules.remove(flag)
                }
                FoldingRuleExecutionGuard.resetStats(flag)
                notification.expire()
            }
        })
        notification.notify(project)
    }

    fun clearNotification(flag: String) {
        throttledRules.remove(flag)
    }

    companion object {
        private const val NOTIFICATION_GROUP_ID = "AdvancedExpressionFolding.Throttling"
        fun get() = service<FoldingService>()
    }
}
