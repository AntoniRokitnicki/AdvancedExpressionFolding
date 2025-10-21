package com.intellij.advancedExpressionFolding.folding.base.folding

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface CollectionsStreamsFoldingTest : FoldingTestSection {
    @Test
    fun concatenationTestData() = testCase.runFoldingTest(
        foldingState()::concatenationExpressionsCollapse,
        foldingState()::optional,
        foldingState()::streamSpread,
    )

    @Test
    fun optionalTestData() = testCase.runFoldingTest(
        foldingState()::concatenationExpressionsCollapse,
        foldingState()::optional,
        foldingState()::streamSpread,
    )

    @Test
    fun spreadTestData() = testCase.runFoldingTest(
        foldingState()::concatenationExpressionsCollapse,
        foldingState()::optional,
        foldingState()::streamSpread,
    )
}

@Disabled("Split from FoldingTest")
open class CollectionsStreamsFoldingTestCase : FoldingFeatureTestCase(), CollectionsStreamsFoldingTest
