package com.intellij.advancedExpressionFolding.folding.base.folding

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface HidingSuppressionFoldingTest : FoldingTestSection {
    @Test
    fun overrideHideTestData() = testCase.runFoldingTest(foldingState()::overrideHide)

    @Test
    fun suppressWarningsHideTestData() = testCase.runFoldingTest(foldingState()::suppressWarningsHide)

    @Test
    fun summaryParentOverrideTestData() = testCase.runFoldingTest(foldingState()::summaryParentOverride)
}

@Disabled("Split from FoldingTest")
open class HidingSuppressionFoldingTestCase : FoldingFeatureTestCase(), HidingSuppressionFoldingTest
