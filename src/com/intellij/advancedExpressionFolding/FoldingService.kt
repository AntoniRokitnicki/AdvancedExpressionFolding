package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.openapi.application.ApplicationManager
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
import java.util.concurrent.CopyOnWriteArrayList

@Service
class FoldingService {

    fun interface FoldUpdateListener {
        fun foldUpdated(editor: Editor)
    }

    private val foldListeners = CopyOnWriteArrayList<FoldUpdateListener>()

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

        notifyFoldUpdated(editor)
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
        notifyFoldUpdated(editor)
    }

    class KeyCleanerPsiElementVisitor : PsiRecursiveElementVisitor() {
        override fun visitElement(element: PsiElement) {
            Keys.clearAll(element)
            super.visitElement(element)
        }
    }

    fun addFoldUpdateListener(listener: FoldUpdateListener) {
        foldListeners.add(listener)
    }

    fun removeFoldUpdateListener(listener: FoldUpdateListener) {
        foldListeners.remove(listener)
    }

    private fun notifyFoldUpdated(editor: Editor) {
        val application = ApplicationManager.getApplication()
        val runnable = Runnable {
            foldListeners.forEach { listener ->
                listener.foldUpdated(editor)
            }
        }
        if (application.isDispatchThread) {
            runnable.run()
        } else {
            application.invokeLater(runnable)
        }
    }

    companion object {
        fun get() = service<FoldingService>()
    }
}
