package com.intellij.advancedExpressionFolding

import com.intellij.openapi.application.EDT
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.editor.coroutineScope
import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.event.EditorFactoryListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


class FoldingEditorCreatedListener : EditorFactoryListener {

    override fun editorCreated(event: EditorFactoryEvent) {
        event.editor.coroutineScope.launch(Dispatchers.EDT) {
            delay(1.seconds)
            runWriteAction {
                FoldingService.get().fold(event.editor, true)
            }
        }
    }

    override fun editorReleased(event: EditorFactoryEvent) = FoldingService.get().clearAllKeys(event.editor)

}