package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.event.EditorFactoryEvent
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FoldingListenerTest : BaseTest() {

    @Test
    fun `editorReleased clears keys`() {
        fixture.configureByText("T.java", "class T {}")
        val element = fixture.file.firstChild
        with(Keys) {
            IGNORED.turnOn(element)
            assertTrue(IGNORED.isOn(element))
        }
        val event = EditorFactoryEvent(EditorFactory.getInstance(), fixture.editor)
        FoldingEditorCreatedListener().editorReleased(event)
        with(Keys) {
            assertFalse(IGNORED.isOn(element))
        }
    }
}

