package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.action.FoldingOffAction
import com.intellij.advancedExpressionFolding.action.FoldingOnAction
import com.intellij.advancedExpressionFolding.settings.IConfig
import com.intellij.ide.plugins.DynamicPluginListener
import com.intellij.ide.plugins.IdeaPluginDescriptor
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.event.EditorFactoryEvent
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PluginComponentsTest {

    @Test
    fun pluginUnloadingClearsCache() {
        mockkObject(FoldingService.Companion)
        val service = mockk<FoldingService>(relaxed = true)
        every { FoldingService.get() } returns service

        val clazz = Class.forName("com.intellij.advancedExpressionFolding.PluginUnloadingListener")
        val ctor = clazz.getDeclaredConstructor().apply { isAccessible = true }
        val listener = ctor.newInstance() as DynamicPluginListener
        val descriptor = mockk<IdeaPluginDescriptor>()

        listener.beforePluginUnload(descriptor, false)
        listener.pluginUnloaded(descriptor, false)

        verify(exactly = 2) { service.clearAllKeys() }
        unmockkAll()
    }

    @Test
    fun editorListenerClearsOnRelease() {
        mockkObject(FoldingService.Companion)
        val service = mockk<FoldingService>(relaxed = true)
        every { FoldingService.get() } returns service

        val editor = mockk<Editor>()
        val event = mockk<EditorFactoryEvent> { every { this@mockk.editor } returns editor }

        val listener = FoldingEditorCreatedListener()
        listener.editorReleased(event)
        verify { service.clearAllKeys(editor) }
        unmockkAll()
    }

    private class TestConfig(override var globalOn: Boolean = false) : IConfig {
        override val memoryImprovement: Boolean = false
    }

    private fun createEvent(editor: Editor?): AnActionEvent = mockk {
        every { getData(CommonDataKeys.EDITOR) } returns editor
    }

    @Test
    fun foldingOnActionFoldsAndEnablesGlobalFlag() {
        mockkObject(FoldingService.Companion)
        val service = mockk<FoldingService>(relaxed = true)
        every { FoldingService.get() } returns service

        val config = TestConfig(false)
        val action = FoldingOnAction(config)
        val editor = mockk<Editor>()
        val event = createEvent(editor)

        action.actionPerformed(event)

        assertTrue(config.globalOn)
        verify { service.fold(editor, true) }
        unmockkAll()
    }

    @Test
    fun foldingOffActionFoldsEditorOff() {
        mockkObject(FoldingService.Companion)
        val service = mockk<FoldingService>(relaxed = true)
        every { FoldingService.get() } returns service

        val action = FoldingOffAction()
        val editor = mockk<Editor>()
        val event = createEvent(editor)

        action.actionPerformed(event)

        verify { service.fold(editor, false) }
        unmockkAll()
    }
}
