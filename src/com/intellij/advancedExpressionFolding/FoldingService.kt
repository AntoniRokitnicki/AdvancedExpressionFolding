package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.extension.Keys
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

@Service
class FoldingService {

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

    companion object {
        fun get() = service<FoldingService>()
    }
}
