package com.intellij.advancedExpressionFolding

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.PsiJavaFile
import com.intellij.testFramework.PlatformTestUtil
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EditorLifecycleFoldingTest : BaseTest() {
    private val sample = """
        package data;

        public class AssertTestData {
            public static void main(String[] args) {
                if (args.length == 0) {
                    throw new IllegalArgumentException();
                }
                if (args.length == 1) {
                    throw new IllegalArgumentException("...");
                }
                if (args.length == 2)
                    throw new IllegalArgumentException("...");
            }
        }
    """.trimIndent()

    @Test
    fun `folding scheduled after editor creation`() {
        val psiFile = fixture.configureByText("AssertTestData.java", sample)
        val editor = fixture.editor

        ApplicationManager.getApplication().invokeAndWait {
            runWriteAction {
                editor.foldingModel.runBatchFoldingOperation {
                    editor.foldingModel.allFoldRegions.forEach { it.isExpanded = true }
                }
            }
        }

        Thread.sleep(1500)
        PlatformTestUtil.dispatchAllEventsInIdeEventQueue()

        val pluginRegions = editor.foldingModel.allFoldRegions.filter {
            it.group?.toString()?.startsWith("com.intellij.advancedExpressionFolding") == true
        }
        assertTrue(pluginRegions.isNotEmpty(), "No plugin fold regions found")
        assertTrue(pluginRegions.all { !it.isExpanded }, "Fold regions should be collapsed after delay")
    }

    @Test
    fun `clears cached keys when editor closed`() {
        val psiFile = fixture.configureByText("AssertTestData.java", sample) as PsiJavaFile
        val editor = fixture.editor
        val document = editor.document
        val builder = AdvancedExpressionFoldingBuilder()
        runReadAction { builder.buildFoldRegions(psiFile, document, false) }
        assertNotNull(psiFile.getUserData(Keys.FULL_CACHE))

        ApplicationManager.getApplication().invokeAndWait {
            FileEditorManager.getInstance(project).closeFile(psiFile.virtualFile)
        }
        PlatformTestUtil.dispatchAllEventsInIdeEventQueue()
        assertNull(psiFile.getUserData(Keys.FULL_CACHE))
    }
}
