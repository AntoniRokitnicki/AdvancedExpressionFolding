package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.integration.mesh.FoldingMeshCommand
import com.intellij.advancedExpressionFolding.integration.mesh.FoldingServiceContract
import com.intellij.advancedExpressionFolding.integration.mesh.MeshContext
import com.intellij.advancedExpressionFolding.integration.mesh.MeshEndpoint
import com.intellij.advancedExpressionFolding.integration.mesh.ServiceMesh
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.application.runReadAction
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
import kotlinx.coroutines.runBlocking

@Service
class FoldingService {

    private val coroutineScope: FoldingServiceCoroutineScope
        get() = FoldingServiceCoroutineScope.get()

    private val meshEndpoint: MeshEndpoint<FoldingMeshCommand, Unit> by lazy {
        ServiceMesh.get().register(
            FoldingServiceContract.create { command ->
                handleCommand(command)
            }
        )
    }

    fun fold(editor: Editor, state: Boolean) {
        if (editor.isDisposed) {
            return
        }
        val application = ApplicationManager.getApplication()
        if (application.isDispatchThread) {
            handleFoldEditor(editor, state)
            return
        }
        runBlocking {
            meshEndpoint.dispatch(FoldingMeshCommand.FoldEditor(editor, state))
        }
    }

    fun clearAllKeys() {
        schedule(FoldingMeshCommand.ClearGlobal)
    }

    fun clearAllKeys(project: Project) {
        if (project.isDisposed) {
            return
        }
        schedule(FoldingMeshCommand.ClearProject(project))
    }

    fun clearAllKeys(editor: Editor) {
        if (editor.isDisposed) {
            return
        }
        schedule(FoldingMeshCommand.ClearEditor(editor))
    }

    private fun schedule(command: FoldingMeshCommand) {
        coroutineScope.launch {
            meshEndpoint.dispatch(command)
        }
    }

    private suspend fun MeshContext.handleCommand(command: FoldingMeshCommand) {
        when (command) {
            is FoldingMeshCommand.FoldEditor -> handleFoldEditor(command.editor, command.collapse)
            is FoldingMeshCommand.ClearEditor -> handleClearEditor(command.editor)
            is FoldingMeshCommand.ClearProject -> handleClearProject(command.project)
            FoldingMeshCommand.ClearGlobal -> handleClearGlobal()
        }
    }

    private fun handleFoldEditor(editor: Editor, state: Boolean) {
        runOnEdtAndWait {
            val regions = editor.foldingModel.allFoldRegions.filter(FoldRegion::isAdvancedExpressionFoldingGroup)

            editor.foldingModel
                .runBatchFoldingOperation {
                    regions.forEach {
                        it.isExpanded = !state
                    }
                }
        }
    }

    private fun handleClearGlobal() {
        ProjectManager.getInstance().openProjects.forEach(::handleClearProject)
    }

    private fun handleClearProject(project: Project) {
        if (project.isDisposed) {
            return
        }
        project.openTextEditors.forEach(::handleClearEditor)
    }

    private fun handleClearEditor(editor: Editor) {
        if (editor.isDisposed) {
            return
        }
        val project = editor.project ?: return
        runReadAction {
            val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document) ?: return@runReadAction
            psiFile.accept(KeyCleanerPsiElementVisitor())
        }
    }

    private fun runOnEdtAndWait(action: () -> Unit) {
        val application = ApplicationManager.getApplication()
        if (application.isDisposed) {
            return
        }
        if (application.isDispatchThread) {
            action()
        } else {
            application.invokeAndWait(action, ModalityState.defaultModalityState())
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
