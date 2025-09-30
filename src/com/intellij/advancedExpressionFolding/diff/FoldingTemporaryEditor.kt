package com.intellij.advancedExpressionFolding.diff

import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.EditorKind
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.editor.LogicalPosition
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.util.TextRange

object FoldingTemporaryEditor {

    fun foldInEditor(text: String, list: List<FoldingDescriptorEx>) : FoldedCode {
        val editorFactory = EditorFactory.getInstance()
        val document = editorFactory.createDocument(removeFoldingMarkers(text))

        val editor = editorFactory.createViewer(document, null, EditorKind.MAIN_EDITOR) as EditorEx
        return try {
            editor.caretModel.moveToLogicalPosition(LogicalPosition(0, 0))
            editor.foldingModel.runBatchFoldingOperation {
                list.forEach {
                    editor.foldingModel.createFoldRegion(it.range.start, it.range.end, it.placeholder!!,
                        FoldingGroup.newGroup("${it.groupReference}"), false)
                }
            }
            getVisibleCode(editor)
        } finally {
            editorFactory.releaseEditor(editor)
        }
    }

    private fun getVisibleCode(editor: EditorEx): FoldedCode {
        val document = editor.document
        var offset = 0
        return buildString(document.textLength) {
            for (region in editor.foldingModel.allFoldRegions) {
                if (region.isValid && region.isExpanded) {
                    val foldStart = region.startOffset
                    try {
                        append(document.getText(TextRange(offset, foldStart)))
                        append(region.placeholderText)
                        offset = region.endOffset
                    } catch (_: IllegalArgumentException) {
                        //ignore text that has already been folded
                    }
                }
            }
            append(document.getText(TextRange(offset, document.textLength)))
        }
    }

    private const val FOLD = "fold"
    private fun removeFoldingMarkers(expectedContent: String): String {
        return expectedContent.replace(Regex("<${FOLD}\\stext='[^']*'(\\sexpand='[^']*')*>"), "")
            .replace("</${FOLD}>", "")
    }

}

typealias FoldedCode = String
