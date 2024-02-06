package com.intellij.advancedExpressionFolding

import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.event.EditorFactoryListener

class FoldingEditorCreatedListener : EditorFactoryListener {

    override fun editorCreated(event: EditorFactoryEvent) {
        FoldingService.get().fold(event.editor, true)
    }

}