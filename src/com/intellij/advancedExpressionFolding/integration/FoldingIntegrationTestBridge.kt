package com.intellij.advancedExpressionFolding.integration

import com.intellij.advancedExpressionFolding.FoldingService
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.codeInsight.folding.CodeFoldingManager
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.guessProjectDir
import com.intellij.psi.PsiDocumentManager
import org.jetbrains.annotations.TestOnly

@TestOnly
object FoldingIntegrationTestBridge {

    fun snapshot(filePaths: List<String>): List<FoldingSnapshot> = runReadAction {
        val project = findProject()
        filePaths.map { path ->
            snapshotForFile(project, path)
        }
    }

    fun updateFoldRegions(filePaths: List<String>) {
        val project = findProject()
        val application = ApplicationManager.getApplication()
        filePaths.forEach { path ->
            val editor = findTextEditor(project, path)?.editor ?: return@forEach
            if (editor.isDisposed) {
                return@forEach
            }
            application.invokeAndWait {
                application.runWriteAction {
                    if (editor.isDisposed) {
                        return@runWriteAction
                    }
                    PsiDocumentManager.getInstance(project).commitDocument(editor.document)
                    CodeFoldingManager.getInstance(project).updateFoldRegions(editor)
                }
            }
        }
    }

    fun fold(relativePath: String, collapse: Boolean) {
        val project = findProject()
        val application = ApplicationManager.getApplication()
        val editor = findTextEditor(project, relativePath)?.editor ?: return
        if (editor.isDisposed) {
            return
        }
        application.invokeAndWait {
            application.runWriteAction {
                if (!editor.isDisposed) {
                    FoldingService.Companion.get().fold(editor, collapse)
                }
            }
        }
    }

    private fun snapshotForFile(project: Project, filePath: String): FoldingSnapshot {
        val editor = findTextEditor(project, filePath)?.editor
        if (editor == null || editor.isDisposed) {
            return FoldingSnapshot(filePath, emptyList(), null, null)
        }
        val foldRegions = editor.foldingModel.allFoldRegions.map(FoldRegion::toSnapshot)
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document)
        val cache = psiFile?.getUserData(Keys.FULL_CACHE)
        val cachedDescriptors = cache?.map(FoldingDescriptor::toSnapshot)
        val cacheIdentityHash = cache?.let { System.identityHashCode(it) }
        return FoldingSnapshot(filePath, foldRegions, cachedDescriptors, cacheIdentityHash)
    }

    private fun findProject(): Project =
        ProjectManager.getInstance().openProjects.firstOrNull()
            ?: error("No open projects found for folding diagnostics")

    private fun findTextEditor(project: Project, relativePath: String): TextEditor? {
        val projectDir = project.guessProjectDir() ?: return null
        val virtualFile = projectDir.findFileByRelativePath(relativePath) ?: return null
        return FileEditorManagerEx.getInstanceEx(project)
            .getAllEditors(virtualFile)
            .filterIsInstance<TextEditor>()
            .firstOrNull()
    }
}

data class FoldingSnapshot(
    val filePath: String,
    val foldRegions: List<FoldRegionSnapshot>,
    val cachedDescriptors: List<FoldingDescriptorSnapshot>?,
    val cacheIdentityHash: Int?,
)

data class FoldRegionSnapshot(
    val signature: FoldingSignature,
    val expanded: Boolean,
)

data class FoldingDescriptorSnapshot(
    val signature: FoldingSignature,
)

data class FoldingSignature(
    val startOffset: Int,
    val endOffset: Int,
    val placeholderText: String?,
    val group: String?,
)

private fun FoldRegion.toSnapshot(): FoldRegionSnapshot = FoldRegionSnapshot(
    signature = FoldingSignature(startOffset, endOffset, placeholderText, group?.toString()),
    expanded = isExpanded,
)

private fun FoldingDescriptor.toSnapshot(): FoldingDescriptorSnapshot = FoldingDescriptorSnapshot(
    signature = FoldingSignature(range.startOffset, range.endOffset, placeholderText, group?.toString()),
)
