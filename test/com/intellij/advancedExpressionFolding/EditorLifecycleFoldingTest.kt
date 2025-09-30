package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.runInEdt
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiJavaFile
import com.intellij.testFramework.PlatformTestUtil
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EditorLifecycleFoldingTest : BaseTest() {
    //language=Java
    private val sample = """
        package data;

        public class SerialTestData {
            private static final long serialVersionUID = 1234567L;
        }
    """.trimIndent()

    @BeforeEach
    fun setUp() {
        AdvancedExpressionFoldingSettings.getInstance().enableAll()
    }

    @Test
    fun `folding scheduled after editor creation`() {
        fixture.configureByText("SerialTestData.java", sample)
        val editor = fixture.editor

        ApplicationManager.getApplication().invokeAndWait {
            runWriteAction {
                editor.foldingModel.runBatchFoldingOperation {
                    editor.foldingModel.allFoldRegions.forEach { it.isExpanded = true }
                }
            }
        }

        Thread.sleep(1500)
        runInEdt {
            PlatformTestUtil.dispatchAllEventsInIdeEventQueue()
        }

        val pluginRegions = runReadAction {
            editor.foldingModel.allFoldRegions.filter {
                it.group?.toString()?.startsWith("com.intellij.advancedExpressionFolding") == true
            }
        }
        assertTrue(pluginRegions.isNotEmpty(), "No plugin fold regions found")
        assertTrue(pluginRegions.all { !it.isExpanded }, "Fold regions should be collapsed after delay")
    }

    @Test
    fun `clears cached keys when editor closed`() {
        val psiFile = fixture.configureByText("SerialTestData.java", sample) as PsiJavaFile
        val editor = fixture.editor
        val document = editor.document
        val builder = AdvancedExpressionFoldingBuilder()
        runReadAction { builder.buildFoldRegions(psiFile, document, false) }
        assertNotNull(psiFile.getUserData(Keys.FULL_CACHE))

        ApplicationManager.getApplication().invokeAndWait {
            FileEditorManager.getInstance(fixture.project).closeFile(psiFile.virtualFile)
        }
        runInEdt {
            PlatformTestUtil.dispatchAllEventsInIdeEventQueue()
        }
        assertNull(psiFile.getUserData(Keys.FULL_CACHE))
    }
}
