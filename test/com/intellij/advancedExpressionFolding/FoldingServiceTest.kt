package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.testFramework.runInEdtAndWait
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase5
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FoldingServiceTest : BaseTest() {

    @Test
    fun `fold toggles only plugin regions`() {
        val text = "class T {\n  void m(){}\n}\n"
        fixture.configureByText("T.java", text)
        val editor = fixture.editor
        val model = editor.foldingModel as com.intellij.openapi.editor.ex.FoldingModelEx
        lateinit var pluginRegion: FoldRegion
        lateinit var otherRegion: FoldRegion
        val mid = text.indexOf('{') + 1
        runInEdtAndWait {
            runWriteAction {
                model.runBatchFoldingOperation {
                    pluginRegion = model.createFoldRegion(
                        0,
                        mid,
                        "...",
                        FoldingGroup.newGroup("com.intellij.advancedExpressionFolding.test"),
                        false
                    )!!
                    otherRegion = model.createFoldRegion(
                        mid,
                        text.length,
                        "...",
                        FoldingGroup.newGroup("other"),
                        false
                    )!!
                    pluginRegion.isExpanded = true
                    otherRegion.isExpanded = true
                }
            }
        }
        val service = FoldingService()
        runInEdtAndWait { runReadAction { service.fold(editor, true) } }
        assertFalse(pluginRegion.isExpanded)
        assertTrue(otherRegion.isExpanded)
        runInEdtAndWait { runReadAction { service.fold(editor, false) } }
        assertTrue(pluginRegion.isExpanded)
        assertTrue(otherRegion.isExpanded)
    }

    @Test
    fun `clearAllKeys removes user data`() {
        fixture.configureByText("T.java", "class T {}")
        val element = fixture.file.firstChild
        with(Keys) {
            IGNORED.turnOn(element)
            assertTrue(IGNORED.isOn(element))
        }
        FoldingService().clearAllKeys(fixture.editor)
        with(Keys) {
            assertFalse(IGNORED.isOn(element))
        }
    }
}

