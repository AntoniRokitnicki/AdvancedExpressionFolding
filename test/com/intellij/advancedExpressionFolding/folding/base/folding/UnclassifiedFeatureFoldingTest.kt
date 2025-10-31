package com.intellij.advancedExpressionFolding.folding.base.folding

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface UnclassifiedFeatureFoldingTest : FoldingTestSection {
    @Test
    fun stringEscapesTestData() = testCase.runFoldingTest(foldingState()::stringEscapesVisualizeNewlines)

    @Test
    fun stringEscapesDisabledTestData() = testCase.runFoldingTest()

    @Test
    fun semicolonTestData() = testCase.runReadOnlyFoldingTest(foldingState()::semicolonsCollapse)
}

@Disabled("Split from FoldingTest")
open class UnclassifiedFeatureFoldingTestCase : FoldingFeatureTestCase(), UnclassifiedFeatureFoldingTest
