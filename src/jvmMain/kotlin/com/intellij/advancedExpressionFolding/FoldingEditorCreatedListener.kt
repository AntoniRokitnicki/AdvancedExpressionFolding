package com.intellij.advancedExpressionFolding

import com.intellij.openapi.application.EDT
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.event.EditorFactoryListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


class FoldingEditorCreatedListener : EditorFactoryListener {

    override fun editorCreated(event: EditorFactoryEvent) {
        CoroutineScope(Dispatchers.EDT).launch {
            delay(1.seconds)
            runWriteAction {
                FoldingService.get().fold(event.editor, true)
            }
        }
    }

    override fun editorReleased(event: EditorFactoryEvent) = FoldingService.get().clearAllKeys(event.editor)

}