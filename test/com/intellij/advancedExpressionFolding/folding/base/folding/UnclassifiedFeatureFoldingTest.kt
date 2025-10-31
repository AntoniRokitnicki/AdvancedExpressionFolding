package com.intellij.advancedExpressionFolding.folding.base.folding

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface UnclassifiedFeatureFoldingTest : FoldingTestSection {
    @Test
    fun semicolonTestData() = testCase.runReadOnlyFoldingTest(foldingState()::semicolonsCollapse)

    @Test
    fun logicalOperatorsWordsTestData() = testCase.runFoldingTest(
        foldingState()::experimental,
        foldingState()::logicalOperatorsWords,
        foldingState()::logicalOperatorsUppercase,
        foldingState()::logicalOperatorsParentheses,
    )

    @Test
    fun logicalOperatorsWordsLowercaseTestData() = testCase.runFoldingTest(
        foldingState()::experimental,
        foldingState()::logicalOperatorsWords,
        foldingState()::logicalOperatorsParentheses,
    )
}

@Disabled("Split from FoldingTest")
open class UnclassifiedFeatureFoldingTestCase : FoldingFeatureTestCase(), UnclassifiedFeatureFoldingTest
