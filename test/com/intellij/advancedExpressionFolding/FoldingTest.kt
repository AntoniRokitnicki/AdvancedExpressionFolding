package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.State
import kotlin.reflect.KMutableProperty0

open class FoldingTest : BaseTest() {

    private val state: State by lazy {
        AdvancedExpressionFoldingSettings.getInstance().state
    }

    private fun doFoldingTest(vararg turnOnProperties: KMutableProperty0<Boolean>) {
        turnOnProperties.forEach {
            it.set(true)
        }
        super.doFoldingTest()
    }

    private fun doReadOnlyFoldingTest(vararg turnOnProperties: KMutableProperty0<Boolean>) {
        turnOnProperties.forEach {
            it.set(true)
        }
        super.doReadOnlyFoldingTest()
    }

    /**
     * [data.ElvisTestData]
     */
    fun testElvisTestData() {
        doFoldingTest(state::checkExpressionsCollapse)
    }

    /**
     * [data.ForRangeTestData]
     */
    fun testForRangeTestData() {
        doFoldingTest(state::rangeExpressionsCollapse)
    }

    /**
     * [data.StringBuilderTestData]
     */
    fun testStringBuilderTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    /**
     * [data.InterpolatedStringTestData]
     */
    fun testInterpolatedStringTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    /**
     * [data.GetSetPutTestData]
     */
    fun testGetSetPutTestData() {
        doFoldingTest(state::getExpressionsCollapse)
    }

    /**
     * [data.SliceTestData]
     */
    fun testSliceTestData() {
        doFoldingTest(state::slicingExpressionsCollapse)
    }

    /**
     * [data.AppendSetterInterpolatedStringTestData]
     */
    fun testAppendSetterInterpolatedStringTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::getSetExpressionsCollapse)
    }

    /**
     * [data.EqualsCompareTestData]
     */
    fun testEqualsCompareTestData() {
        doFoldingTest(state::comparingExpressionsCollapse)
    }

    /**
     * [data.TypeCastTestData]
     */
    fun testTypeCastTestData() {
        doFoldingTest(state::castExpressionsCollapse)
    }

    /**
     * [data.VarTestData]
     */
    fun testVarTestData() {
        doFoldingTest(state::varExpressionsCollapse)
    }

    /**
     * [data.GetterSetterTestData]
     */
    fun testGetterSetterTestData() {
        doFoldingTest(state::getSetExpressionsCollapse)
    }

    /**
     * [data.ControlFlowSingleStatementTestData]
     */
    fun testControlFlowSingleStatementTestData() {
        // TODO: Test with different indentation settings
        doReadOnlyFoldingTest(state::controlFlowSingleStatementCodeBlockCollapse)
    }

    /**
     * [data.ControlFlowMultiStatementTestData]
     */
    fun testControlFlowMultiStatementTestData() {
        // TODO: Test with different indentation settings
        doReadOnlyFoldingTest(state::controlFlowMultiStatementCodeBlockCollapse)
    }

    /**
     * [data.LocalDateTestData]
     */
    fun testLocalDateTestData() {
        doReadOnlyFoldingTest(state::comparingLocalDatesCollapse)
    }

    /**
     * [data.LocalDateLiteralTestData]
     */
    fun testLocalDateLiteralTestData() {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse)
    }

    /**
     * [data.LocalDateLiteralPostfixTestData]
     */
    fun testLocalDateLiteralPostfixTestData() {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse, state::localDateLiteralPostfixCollapse)
    }

    /**
     * [data.CompactControlFlowTestData]
     */
    fun testCompactControlFlowTestData() {
        doFoldingTest(state::compactControlFlowSyntaxCollapse)
    }

    /**
     * [data.SemicolonTestData]
     */
    fun testSemicolonTestData() {
        doReadOnlyFoldingTest(state::semicolonsCollapse)
    }

    /**
     * [data.ConcatenationTestData]
     */
    fun testConcatenationTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.OptionalTestData]
     */
    fun testOptionalTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.SpreadTestData]
     */
    fun testSpreadTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.LombokTestData]
     */
    fun testLombokTestData() {
        doFoldingTest(state::lombok)
    }

    fun testLombokUsageTestData() {
        doFoldingTest(state::lombok)
    }

    /**
     * [data.FieldShiftBuilder]
     */
    fun testFieldShiftBuilder() {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    /**
     * [data.FieldShiftSetters]
     */
    fun testFieldShiftSetters() {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    /**
     * [data.LetReturnIt]
     */
    fun testLetReturnIt() {
        doFoldingTest(state::varExpressionsCollapse, state::kotlinQuickReturn)
    }

    /**
     * [data.IfNullSafeData]
     */
    fun testIfNullSafeData() {
        doFoldingTest(state::checkExpressionsCollapse, state::getSetExpressionsCollapse, state::ifNullSafe)
    }

    /**
     * [data.LogBrackets]
     */
    fun testLogBrackets() {
        doFoldingTest(state::getSetExpressionsCollapse, state::logFolding)
    }



    /**
     * [data.FieldShiftFields]
     */
    fun testFieldShiftFields() {
        doFoldingTest(state::getSetExpressionsCollapse, state::fieldShift)
    }


}
