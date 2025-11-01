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
        val predictor = FoldingPredictor.get()
        predictor.registerEditor(event.editor)
        CoroutineScope(Dispatchers.EDT).launch {
            delay(1.seconds)
            runWriteAction {
                predictor.applyPredictions(event.editor)
            }
        }
    }

    override fun editorReleased(event: EditorFactoryEvent) {
        FoldingPredictor.get().unregisterEditor(event.editor)
        FoldingService.get().clearAllKeys(event.editor)
    }

}
