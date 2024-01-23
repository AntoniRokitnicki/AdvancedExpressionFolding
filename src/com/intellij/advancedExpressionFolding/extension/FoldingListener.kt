package com.intellij.advancedExpressionFolding.extension

import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.fileEditor.impl.text.TextEditorCustomizer

class FoldingListener : TextEditorCustomizer {

    override fun customize(textEditor: TextEditor) {
        FoldingService.get().fold(textEditor.editor, true)
    }


}