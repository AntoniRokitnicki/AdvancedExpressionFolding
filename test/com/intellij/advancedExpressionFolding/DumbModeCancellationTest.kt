package com.intellij.advancedExpressionFolding

import com.google.common.collect.Sets
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.progress.EmptyProgressIndicator
import com.intellij.openapi.progress.ProcessCanceledException
import com.intellij.openapi.progress.ProgressManager
import com.intellij.testFramework.DumbModeTestUtils
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DumbModeCancellationTest : BaseTest() {

    @Test
    fun `get non synthetic expression does not index during dumb mode`() {
        val psiFile = fixture.configureByText("DumbMode.java", "class DumbMode { int value; }")
        val document = fixture.editor.document

        DumbModeTestUtils.runInDumbModeSynchronously(fixture.project) {
            assertDoesNotThrow {
                runReadAction {
                    BuildExpressionExt.getNonSyntheticExpression(psiFile, document)
                }
            }
        }
    }

    @Test
    fun `collect fold regions checks cancellation`() {
        val psiFile = fixture.configureByText("Cancellation.java", "class Cancellation { void test() {} }")
        val document = fixture.editor.document

        val indicator = EmptyProgressIndicator()

        val unique = Sets.newIdentityHashSet<Expression>()
        val descriptors = ArrayList<FoldingDescriptor>()
        var canceled = false

        ProgressManager.getInstance().runProcess({
            indicator.cancel()
            assertDoesNotThrow {
                try {
                    runReadAction {
                        BuildExpressionExt.collectFoldRegionsRecursively(psiFile, document, unique, descriptors)
                    }
                } catch (e: ProcessCanceledException) {
                    canceled = true
                }
            }
        }, indicator)

        assertTrue(canceled)
    }
}
