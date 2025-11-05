package com.intellij.advancedExpressionFolding

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import org.jetbrains.annotations.TestOnly

@Service
class FoldingService @TestOnly internal constructor(
    private val keyClearer: EditorKeyClearer,
    private val scheduler: EditorClearScheduler<Editor>,
) {

    constructor() : this(
        keyClearer = PsiEditorKeyClearer(),
        scheduler = EditorClearScheduler { FoldingServiceCoroutineScope.get() },
    )

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
        scheduler.schedule(editors, keyClearer::clear)
    }

    fun clearAllKeys(editor: Editor) {
        keyClearer.clear(editor)
    }

    companion object {
        fun get() = service<FoldingService>()
    }
}
