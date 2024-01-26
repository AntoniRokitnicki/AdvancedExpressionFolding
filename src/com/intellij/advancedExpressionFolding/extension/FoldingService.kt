package com.intellij.advancedExpressionFolding.extension

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.editor.Editor

@Service
class FoldingService {

    fun fold(editor: Editor, state: Boolean) {
        val regions = editor.foldingModel.allFoldRegions.filter {
            it.group?.toString()?.startsWith("com.intellij.advancedExpressionFolding") ?: false
        }
        val others = editor.foldingModel.allFoldRegions.toList() - regions

        editor.foldingModel
            .runBatchFoldingOperation {
                regions.forEach {
                    it.isExpanded = !state
                }
                others.forEach {
                    it.isExpanded = true
                }
            }
    }

    companion object {
        fun get() = service<FoldingService>()
    }
}