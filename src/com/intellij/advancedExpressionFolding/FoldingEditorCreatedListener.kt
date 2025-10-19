package com.intellij.advancedExpressionFolding

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.EDT
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.event.EditorFactoryListener
import com.intellij.openapi.editor.Editor
import com.intellij.util.messages.Topic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


class FoldingEditorCreatedListener : EditorFactoryListener {

    interface Listener {
        fun editorCreated(editor: Editor) = Unit
        fun editorReleased(editor: Editor) = Unit
    }

    companion object {
        val TOPIC: Topic<Listener> = Topic.create(
            "advanced.expression.folding.editor.lifecycle",
            Listener::class.java
        )
    }

    override fun editorCreated(event: EditorFactoryEvent) {
        CoroutineScope(Dispatchers.EDT).launch {
            delay(1.seconds)
            runWriteAction {
                FoldingService.get().fold(event.editor, true)
            }
            ApplicationManager.getApplication().messageBus.syncPublisher(TOPIC)
                .editorCreated(event.editor)
        }
    }

    override fun editorReleased(event: EditorFactoryEvent) {
        FoldingService.get().clearAllKeys(event.editor)
        ApplicationManager.getApplication().messageBus.syncPublisher(TOPIC)
            .editorReleased(event.editor)
    }

}