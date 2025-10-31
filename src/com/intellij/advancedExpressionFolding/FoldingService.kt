package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.openapi.Disposable
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiRecursiveElementVisitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

@Service
class FoldingService : CoroutineScope, Disposable {

    private val log = logger<FoldingService>()

    /**
     * Uses a [SupervisorJob] so one failing cleanup does not cancel the service scope.
     */
    private val job = SupervisorJob()

    /**
     * Keeps cleanup work off the EDT by delegating to [Dispatchers.Default].
     */
    @Suppress("InjectDispatcher")
    override val coroutineContext: CoroutineContext = job + Dispatchers.Default

    /**
     * Expands or collapses advanced folding regions according to the provided [state].
     */
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

    /**
     * Clears cached folding keys for every open project.
     */
    fun clearAllKeys() {
        ProjectManager.getInstance().openProjects.forEach(this::clearAllKeys)
    }

    /**
     * Clears cached folding keys for each editor in the given [project].
     */
    fun clearAllKeys(project: Project) {
        val editors = project.openTextEditors.toList()

        launch {
            editors.forEach(::clearAllKeys)
        }
    }

    /**
     * Clears cached folding keys for the provided [editor] when possible.
     */
    fun clearAllKeys(editor: Editor) {
        if (editor.isDisposed) {
            return
        }
        val project = editor.project ?: return
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document) ?: return
        psiFile.accept(KeyCleanerPsiElementVisitor())
    }

    /**
     * Removes folding keys from every visited PSI element.
     */
    class KeyCleanerPsiElementVisitor : PsiRecursiveElementVisitor() {
        override fun visitElement(element: PsiElement) {
            Keys.clearAll(element)
            super.visitElement(element)
        }
    }

    companion object {
        /**
         * Returns the project-level instance of [FoldingService].
         */
        fun get() = service<FoldingService>()
    }

    /**
     * Cancels the coroutine scope and waits for all cleanup work to finish.
     */
    override fun dispose() {
        try {
            job.cancel()
        } catch (t: Throwable) {
            log.warn("Failed to cancel folding service coroutine scope", t)
        }
        runBlocking {
            job.join()
        }
    }
}
