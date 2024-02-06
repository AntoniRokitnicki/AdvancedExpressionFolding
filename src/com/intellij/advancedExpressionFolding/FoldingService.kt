package com.intellij.advancedExpressionFolding

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor

@Service
class FoldingService {

    fun fold(editor: Editor, state: Boolean) {
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

    companion object {
        fun get() = service<FoldingService>()
    }
}