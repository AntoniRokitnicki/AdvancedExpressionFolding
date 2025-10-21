package com.intellij.advancedExpressionFolding.folding.base.folding

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface ControlFlowFoldingTest : FoldingTestSection {
    @Test
    fun controlFlowSingleStatementTestData() = testCase.runReadOnlyFoldingTest(
        foldingState()::controlFlowSingleStatementCodeBlockCollapse,
    )

    @Test
    fun controlFlowMultiStatementTestData() = testCase.runReadOnlyFoldingTest(
        foldingState()::controlFlowMultiStatementCodeBlockCollapse,
    )

    @Test
    fun compactControlFlowTestData() = testCase.runFoldingTest(foldingState()::compactControlFlowSyntaxCollapse)
}

@Disabled("Split from FoldingTest")
open class ControlFlowFoldingTestCase : FoldingFeatureTestCase(), ControlFlowFoldingTest
