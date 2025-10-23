package com.intellij.advancedExpressionFolding.integration

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.advancedExpressionFolding.FoldingEditorCreatedListener
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.advancedExpressionFolding.processor.cache.Keys.isOn
import com.intellij.advancedExpressionFolding.processor.cache.Keys.turnOn
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.ex.FoldingModelEx
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.PlatformTestUtil
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FoldingEditorCreatedListenerTest : BaseTest() {

    @Test
    fun editorCreatedCollapsesPluginRegionsAfterDelay() {
        val (editor, region) = setUpEditorWithRegion(initiallyExpanded = true)
        val listener = FoldingEditorCreatedListener()
        val event = EditorFactoryEvent(EditorFactory.getInstance(), editor)

        listener.editorCreated(event)

        Thread.sleep(200)
        pumpEdtEvents()
        ApplicationManager.getApplication().invokeAndWait {
            assertTrue(region.isExpanded, "Folding should remain expanded before the delay elapses")
        }

        Thread.sleep(1_200)
        pumpEdtEvents()
        ApplicationManager.getApplication().invokeAndWait {
            assertFalse(region.isExpanded, "FoldingService should collapse plugin regions after the delay")
        }
    }

    @Test
    fun editorReleasedClearsCachedKeysFromPsi() {
        val fileText = """
            class KeysUsage {
                void method() {}
            }
        """.trimIndent()
        fixture.configureByText("KeysUsage.java", fileText)
        val psiFile = fixture.file
        val method = requireNotNull(runReadAction {
            PsiTreeUtil.findChildOfType(psiFile, PsiMethod::class.java)
        }) {
            "Failed to find PSI method to attach cached key"
        }
        Keys.IGNORED.turnOn(method)
        assertTrue(Keys.IGNORED.isOn(method), "Precondition: the key should be present before release")

        val listener = FoldingEditorCreatedListener()
        val event = EditorFactoryEvent(EditorFactory.getInstance(), fixture.editor)

        ApplicationManager.getApplication().invokeAndWait {
            listener.editorReleased(event)
        }

        val cleared = runReadAction { method.getUserData(Keys.IGNORED) }
        assertNull(cleared, "Editor release should clear cached keys from PSI")
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
            runWriteAction {
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

    private fun pumpEdtEvents() {
        ApplicationManager.getApplication().invokeAndWait {
            PlatformTestUtil.dispatchAllInvocationEventsInIdeEventQueue()
        }
    }
}
