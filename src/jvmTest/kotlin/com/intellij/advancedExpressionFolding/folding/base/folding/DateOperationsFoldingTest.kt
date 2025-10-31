package com.intellij.advancedExpressionFolding.folding.base.folding

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface DateOperationsFoldingTest : FoldingTestSection {
    @Test
    fun localDateTestData() = testCase.runReadOnlyFoldingTest(foldingState()::comparingLocalDatesCollapse)

    @Test
    fun localDateLiteralTestData() = testCase.runReadOnlyFoldingTest(foldingState()::localDateLiteralCollapse)

    @Test
    fun localDateLiteralPostfixTestData() = testCase.runReadOnlyFoldingTest(
        foldingState()::localDateLiteralCollapse,
        foldingState()::localDateLiteralPostfixCollapse,
    )
}

@Disabled("Split from FoldingTest")
open class DateOperationsFoldingTestCase : FoldingFeatureTestCase(), DateOperationsFoldingTest
