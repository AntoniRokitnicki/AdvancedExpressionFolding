package com.intellij.advancedExpressionFolding

import arrow.core.getOrElse
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import arrow.fx.coroutines.ExitCase
import arrow.fx.coroutines.resourceScope
import arrow.fx.coroutines.parZip
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiRecursiveElementVisitor
import kotlinx.coroutines.launch

@Service
class FoldingService {

    fun fold(editor: Editor, state: Boolean) {
        if (editor.isDisposed) {
            return
        }
        val regions = editor.foldingModel.allFoldRegions.filter(FoldRegion::isAdvancedExpressionFoldingGroup)

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
        val editors = project.openTextEditors

        val coroutineScope = FoldingServiceCoroutineScope.get()
        clearAllKeysStart(coroutineScope, editors)
    }

    private fun FoldingService.clearAllKeysStart(
        coroutineScope: FoldingServiceCoroutineScope,
        editors: List<Editor>
    ) {
        coroutineScope.launch {
            clearAllKeysForEditors(editors)
        }
    }

    private suspend fun FoldingService.clearAllKeysForEditors(editors: List<Editor>) {
        editors.chunked(2).forEach { chunk ->
            when (chunk.size) {
                0 -> Unit
                1 -> clearEditorKeys(chunk[0]).getOrElse { }
                else -> parZip(
                    { clearEditorKeys(chunk[0]).getOrElse { } },
                    { clearEditorKeys(chunk[1]).getOrElse { } }
                ) { _, _ -> }
            }
        }
    }

    fun clearAllKeys(editor: Editor) {
        FoldingServiceCoroutineScope.get().launch {
            clearEditorKeys(editor).getOrElse { }
        }
    }

    private suspend fun clearEditorKeys(editor: Editor) = either<EditorCleanupIssue, Unit> {
        ensure(!editor.isDisposed) { EditorCleanupIssue.EditorDisposed }
        val project = ensureNotNull(editor.project) { EditorCleanupIssue.MissingProject }
        val manager = PsiDocumentManager.getInstance(project)
        val psiFile = ensureNotNull(manager.getPsiFile(editor.document)) { EditorCleanupIssue.NoPsiFile }

        resourceScope {
            val file = install(
                acquire = { psiFile },
                release = { _, _: ExitCase ->
                    manager.commitDocument(editor.document)
                }
            )
            file.accept(KeyCleanerPsiElementVisitor())
        }
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

private sealed interface EditorCleanupIssue {
    data object EditorDisposed : EditorCleanupIssue
    data object MissingProject : EditorCleanupIssue
    data object NoPsiFile : EditorCleanupIssue
}
