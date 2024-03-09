package com.intellij.advancedExpressionFolding.diff

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.EditorKind
import com.intellij.openapi.editor.LogicalPosition
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.util.TextRange
import javax.swing.SwingUtilities

object FoldingTemporaryEditor {

    fun getFoldedText(
        text: String,
        wrapper: FoldingDescriptorExWrapper
    ): String {
        var changedText: String? = null
        SwingUtilities.invokeAndWait {
            ApplicationManager.getApplication().runWriteAction {
                changedText = foldInEditor(text, wrapper.list)
            }
        }
        return changedText!!
    }

    fun foldInEditor(text: String, list: List<FoldingDescriptorEx>) : String {
        val editorFactory = EditorFactory.getInstance()
        val document = editorFactory.createDocument(removeFoldingMarkers(text))

        val editor = editorFactory.createViewer(document, null, EditorKind.MAIN_EDITOR) as EditorEx
        val foldedText = try {
            editor.caretModel.moveToLogicalPosition(LogicalPosition(0, 0))
            editor.foldingModel.runBatchFoldingOperation {
                list.forEach {
                    editor.foldingModel.addFoldRegion(it.range.start, it.range.end, it.placeholder!!)
                }
            }
            getVisibleText(editor)
        } finally {
            editorFactory.releaseEditor(editor)
        }
        return foldedText
    }

    private fun getVisibleText(editor: EditorEx): String {
        val document = editor.document
        val foldedText = StringBuilder()
        var offset = 0
        for (region in editor.foldingModel.allFoldRegions) {
            if (region.isValid && region.isExpanded) {
                val foldStart = region.startOffset
                try {
                    foldedText.append(document.getText(TextRange(offset, foldStart)))
                    foldedText.append(region.placeholderText)
                    offset = region.endOffset
                } catch (e: IllegalArgumentException) {
                    //ignore text that has already been folded
                }
            }
        }
        foldedText.append(document.getText(TextRange(offset, document.textLength)))
        return foldedText.toString()
    }

    private const val FOLD = "fold"
    private fun removeFoldingMarkers(expectedContent: String): String {
        return expectedContent.replace(Regex("<${FOLD}\\stext='[^']*'(\\sexpand='[^']*')*>"), "")
            .replace("</${FOLD}>", "")
    }

}

