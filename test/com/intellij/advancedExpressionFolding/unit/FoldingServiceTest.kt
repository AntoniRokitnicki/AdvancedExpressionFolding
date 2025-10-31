package com.intellij.advancedExpressionFolding.unit

import com.intellij.advancedExpressionFolding.FoldingService
import com.intellij.advancedExpressionFolding.folding.BaseTest
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.testFramework.LoggedErrorProcessor
import com.intellij.util.ThrowableRunnable
import com.intellij.util.ui.UIUtil
import com.intellij.openapi.application.WriteIntentReadAction
import kotlinx.coroutines.job
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class FoldingServiceTest : BaseTest() {

    @Test
    fun clearAllKeysAfterDisposingEditorsDoesNotLogWarnings() {
        val psiFile = fixture.configureByText("Foo.java", "class Foo {}")
        val virtualFile = psiFile.virtualFile
        val warnings = mutableListOf<String>()
        val project = fixture.project
        val foldingService = FoldingService.Companion.get()
        val scopeJob = foldingService.coroutineContext.job

        val processor = object : LoggedErrorProcessor() {
            override fun processWarn(
                category: String,
                message: String,
                t: Throwable?,
            ): Boolean {
                synchronized(warnings) {
                    warnings.add("$category: $message")
                }
                return true
            }
        }

        LoggedErrorProcessor.executeWith(processor, ThrowableRunnable<RuntimeException> {
            foldingService.clearAllKeys(project)
            UIUtil.invokeAndWaitIfNeeded {
                WriteIntentReadAction.run {
                    FileEditorManager.getInstance(project).closeFile(virtualFile)
                }
            }
            runBlocking {
                while (true) {
                    val children = scopeJob.children.toList()
                    if (children.isEmpty()) {
                        break
                    }
                    children.forEach { it.join() }
                }
            }
        })

        assertTrue(warnings.isEmpty()) {
            "Warnings were logged after disposing editors: ${warnings.joinToString()}"
        }
    }
}
