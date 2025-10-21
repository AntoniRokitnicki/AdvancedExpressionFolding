package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.folding.BaseTest
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.actionSystem.impl.SimpleDataContext
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.editor.ex.FoldingModelEx
import com.intellij.testFramework.assertInstanceOf
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@Suppress("OverrideOnly")
class FoldingActionsTest : BaseTest() {

    private val settings: AdvancedExpressionFoldingSettings
        get() = AdvancedExpressionFoldingSettings.getInstance()
    private var originalGlobalOn: Boolean = true

    @BeforeEach
    fun rememberOriginalState() {
        originalGlobalOn = settings.state.globalOn
        settings.state.globalOn = true
    }

    @AfterEach
    fun restoreOriginalState() {
        settings.state.globalOn = originalGlobalOn
    }

    @Test
    fun globalToggleActionFlipsStateThroughActionManager() {
        val action = ActionManager.getInstance().getAction("com.intellij.advancedExpressionFolding.action.GlobalToggleFoldingAction")
        val toggle = assertInstanceOf<ToggleAction>(action)

        val initialEvent = createEvent(toggle)
        assertTrue(settings.state.globalOn, "globalOn should start enabled for toggle test")

        ApplicationManager.getApplication().invokeAndWait {
            toggle.actionPerformed(initialEvent)
        }
        assertFalse(settings.state.globalOn, "Toggle action should disable global folding on first invocation")

        ApplicationManager.getApplication().invokeAndWait {
            toggle.actionPerformed(createEvent(toggle))
        }
        assertTrue(settings.state.globalOn, "Toggle action should re-enable global folding on second invocation")

        ApplicationManager.getApplication().invokeAndWait {
            toggle.actionPerformed(createEvent(toggle))
        }
        assertFalse(settings.state.globalOn, "Third toggle should disable global folding again")

        // Sanity check EDT expectation and dumb-awareness contract
        assertEquals(ActionUpdateThread.EDT, toggle.actionUpdateThread)
        assertTrue(toggle.isDumbAware)
    }

    @Test
    fun foldingOnActionEnablesStateAndCollapsesRegionsViaService() {
        val (editor, region) = setUpEditorWithRegion(initiallyExpanded = true)
        settings.state.globalOn = false

        val action = ActionManager.getInstance().getAction("com.intellij.advancedExpressionFolding.action.FoldingOnAction")
        val event = createEvent(action, editor)

        ApplicationManager.getApplication().invokeAndWait {
            action.actionPerformed(event)
        }

        assertTrue(settings.state.globalOn, "FoldingOnAction should enable global folding state")
        assertFalse(region.isExpanded, "FoldingService should collapse plugin regions when enabling folding")
        assertFalse(action.isDumbAware, "FoldingOnAction is expected to be disabled in dumb mode")

        // Repeating the action should keep the state idempotent
        ApplicationManager.getApplication().invokeAndWait {
            action.actionPerformed(createEvent(action, editor))
        }
        assertTrue(settings.state.globalOn, "Repeated enabling should leave global flag enabled")
        assertFalse(region.isExpanded, "Repeated enabling should keep regions collapsed")
    }

    @Test
    fun foldingOffActionExpandsRegionsWithoutTouchingState() {
        val (editor, region) = setUpEditorWithRegion(initiallyExpanded = false)
        settings.state.globalOn = true

        val action = ActionManager.getInstance().getAction("com.intellij.advancedExpressionFolding.action.FoldingOffAction")
        val event = createEvent(action, editor)

        ApplicationManager.getApplication().invokeAndWait {
            action.actionPerformed(event)
        }

        assertTrue(settings.state.globalOn, "FoldingOffAction must not change the global folding flag")
        assertTrue(region.isExpanded, "FoldingService should expand plugin regions when disabling folding")
        assertFalse(action.isDumbAware, "FoldingOffAction is expected to be disabled in dumb mode")

        // Repeat the action to ensure idempotent expansion
        ApplicationManager.getApplication().invokeAndWait {
            action.actionPerformed(createEvent(action, editor))
        }
        assertTrue(region.isExpanded, "Repeated disabling should keep regions expanded")
        assertTrue(settings.state.globalOn, "Global flag should stay intact after repeated disabling")
    }

    private fun createEvent(action: AnAction, editor: Editor? = null): AnActionEvent {
        val builder = SimpleDataContext.builder()
            .add(CommonDataKeys.PROJECT, fixture.project)
        if (editor != null) {
            builder.add(CommonDataKeys.EDITOR, editor)
        }
        val dataContext = builder.build()
        return AnActionEvent.createEvent(action, dataContext, null, ActionPlaces.UNKNOWN, ActionUiKind.NONE, null)
    }

    private fun setUpEditorWithRegion(initiallyExpanded: Boolean): Pair<Editor, FoldRegion> {
        val fileText = """
            class Toggle {
                void method() {
                    int value = 1 + 2;
                }
            }
        """.trimIndent()
        fixture.configureByText("Toggle.java", fileText)
        val editor = fixture.editor
        val document = editor.document
        val expressionOffset = document.text.indexOf("1 + 2")
        check(expressionOffset >= 0) { "Failed to locate folding expression in the document" }
        val endOffset = document.text.indexOf(';', expressionOffset).takeIf { it >= 0 }?.plus(1)
            ?: error("Failed to locate expression terminator for folding region")
        val group = FoldingGroup.newGroup("com.intellij.advancedExpressionFolding.test")
        var region: FoldRegion? = null
        ApplicationManager.getApplication().invokeAndWait {
            ApplicationManager.getApplication().runWriteAction {
                val foldingModel = editor.foldingModel as FoldingModelEx
                foldingModel.runBatchFoldingOperation {
                    region = foldingModel.createFoldRegion(expressionOffset, endOffset, "...", group, false)
                    requireNotNull(region) { "Failed to create test folding region" }
                    region!!.isExpanded = initiallyExpanded
                }
            }
        }
        return editor to requireNotNull(region)
    }
}
