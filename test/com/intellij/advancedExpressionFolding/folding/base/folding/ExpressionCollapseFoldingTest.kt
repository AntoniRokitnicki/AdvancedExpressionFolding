package com.intellij.advancedExpressionFolding.folding.base.folding

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface ExpressionCollapseFoldingTest : FoldingTestSection {
    @Test
    fun elvisTestData() = testCase.runFoldingTest(foldingState()::checkExpressionsCollapse)

    @Test
    fun forRangeTestData() = testCase.runFoldingTest(foldingState()::rangeExpressionsCollapse)

    @Test
    fun stringBuilderTestData() = testCase.runFoldingTest(foldingState()::concatenationExpressionsCollapse)

    @Test
    fun interpolatedStringTestData() = testCase.runFoldingTest(foldingState()::concatenationExpressionsCollapse)

    @Test
    fun getSetPutTestData() = testCase.runFoldingTest(foldingState()::getExpressionsCollapse)

    @Test
    fun arraysAsListNegativeNumbersTestData() =
        testCase.runFoldingTest(foldingState()::getExpressionsCollapse)

    @Test
    fun sliceTestData() = testCase.runFoldingTest(foldingState()::slicingExpressionsCollapse)

    @Test
    fun appendSetterInterpolatedStringTestData() = testCase.runFoldingTest(
        foldingState()::concatenationExpressionsCollapse,
        foldingState()::getSetExpressionsCollapse,
    )

    @Test
    fun equalsCompareTestData() = testCase.runFoldingTest(foldingState()::comparingExpressionsCollapse)

    @Test
    fun typeCastTestData() = testCase.runFoldingTest(foldingState()::castExpressionsCollapse)

    @Test
    fun varTestData() = testCase.runFoldingTest(foldingState()::varExpressionsCollapse)

    @Test
    fun getterSetterTestData() = testCase.runFoldingTest(foldingState()::getSetExpressionsCollapse)

    @Test
    fun fieldShiftBuilder() = testCase.runFoldingTest(
        foldingState()::fieldShift,
        foldingState()::getSetExpressionsCollapse,
    )

    @Test
    fun fieldShiftSetters() = testCase.runFoldingTest(
        foldingState()::fieldShift,
        foldingState()::getSetExpressionsCollapse,
    )

    @Test
    fun fieldShiftFields() = testCase.runFoldingTest(
        foldingState()::getSetExpressionsCollapse,
        foldingState()::fieldShift,
    )
}

@Disabled("Split from FoldingTest")
open class ExpressionCollapseFoldingTestCase : FoldingFeatureTestCase(), ExpressionCollapseFoldingTest
