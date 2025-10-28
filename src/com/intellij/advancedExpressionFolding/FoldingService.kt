package com.intellij.advancedExpressionFolding

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
        //its cut, because verification throws for it seems to be no reason:
        // Invocation of unresolved method ServicesKt.serviceNotFoundError(...) (1)
        // Method FoldingService.clearAllKeys(Project) contains an invokestatic instruction referencing an unresolved method ServicesKt.serviceNotFoundError(...). This can lead to NoSuchMethodError exception at runtime.
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

    private fun FoldingService.clearAllKeysForEditors(editors: List<Editor>) {
        editors.forEach(::clearAllKeys)
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
