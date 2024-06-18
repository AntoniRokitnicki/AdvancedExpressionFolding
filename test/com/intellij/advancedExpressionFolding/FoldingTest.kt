package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.State
import com.intellij.advancedExpressionFolding.extension.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.IDynamicDataProvider
import com.intellij.openapi.application.runInEdt
import org.junit.AssumptionViolatedException
import org.junit.jupiter.api.Test
import org.junitpioneer.jupiter.Stopwatch
import kotlin.reflect.KMutableProperty0

@Stopwatch
//TODO: maybe use @RetryingTest(maxAttempts = 3, suspendForMs = 100, onExceptions = <FindName>.class) when rarely IDE can't be start
open class FoldingTest : BaseTest() {

    class TooComplexException : AssumptionViolatedException("TOO COMPLEX FOLDING")
    class RandomException(t: Throwable) : AssumptionViolatedException("TOO COMPLEX FOLDING", t)

    private val state: State by lazy {
        getInstance().state
    }

    open fun assignState(turnOnProperties: Array<out KMutableProperty0<Boolean>>) {
        getInstance().disableAll()
        turnOnProperties.forEach {
            it.set(true)
        }
    }

    open fun doFoldingTest(vararg turnOnProperties: KMutableProperty0<Boolean>,dynamic: IDynamicDataProvider = TestDynamicDataProvider(), ) {
        assignState(turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        try {
            super.doFoldingTest(null)
        } catch (e: com.intellij.rt.execution.junit.FileComparisonFailure) {
            throw RuntimeException("FileComparisonFailure")
        } catch (e: IllegalArgumentException) {
            if (e.message == "Comparison method violates its general contract!") {
                throw TooComplexException()
            } else {
                throw RandomException(e)
            }
        } catch (t: Throwable) {
            throw RandomException(t)
        }
    }

    private fun doReadOnlyFoldingTest( vararg turnOnProperties: KMutableProperty0<Boolean>, dynamic: IDynamicDataProvider = TestDynamicDataProvider()) {
        assignState(turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        runInEdt {
            super.doReadOnlyFoldingTest()
        }
    }

    /**
     * [data.ElvisTestData]
     */
    @Test fun testElvisTestData() {
        doFoldingTest(state::checkExpressionsCollapse)
    }

    /**
     * [data.ForRangeTestData]
     */
    @Test fun testForRangeTestData() {
        doFoldingTest(state::rangeExpressionsCollapse)
    }

    /**
     * [data.StringBuilderTestData]
     */
    @Test fun testStringBuilderTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    /**
     * [data.InterpolatedStringTestData]
     */
    @Test fun testInterpolatedStringTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    /**
     * [data.GetSetPutTestData]
     */
    @Test fun testGetSetPutTestData() {
        doFoldingTest(state::getExpressionsCollapse)
    }

    /**
     * [data.SliceTestData]
     */
    @Test fun testSliceTestData() {
        doFoldingTest(state::slicingExpressionsCollapse)
    }

    /**
     * [data.AppendSetterInterpolatedStringTestData]
     */
    @Test fun testAppendSetterInterpolatedStringTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::getSetExpressionsCollapse)
    }

    /**
     * [data.EqualsCompareTestData]
     */
    @Test fun testEqualsCompareTestData() {
        doFoldingTest(state::comparingExpressionsCollapse)
    }

    /**
     * [data.TypeCastTestData]
     */
    @Test fun testTypeCastTestData() {
        doFoldingTest(state::castExpressionsCollapse)
    }

    /**
     * [data.VarTestData]
     */
    @Test fun testVarTestData() {
        doFoldingTest(state::varExpressionsCollapse)
    }

    /**
     * [data.GetterSetterTestData]
     */
    @Test fun testGetterSetterTestData() {
        doFoldingTest(state::getSetExpressionsCollapse)
    }

    /**
     * [data.ControlFlowSingleStatementTestData]
     */
    @Test fun testControlFlowSingleStatementTestData() {
        // TODO: Test with different indentation settings
        doReadOnlyFoldingTest(state::controlFlowSingleStatementCodeBlockCollapse)
    }

    /**
     * [data.ControlFlowMultiStatementTestData]
     */
    @Test fun testControlFlowMultiStatementTestData() {
        // TODO: Test with different indentation settings
        doReadOnlyFoldingTest(state::controlFlowMultiStatementCodeBlockCollapse)
    }

    /**
     * [data.LocalDateTestData]
     */
    @Test fun testLocalDateTestData() {
        doReadOnlyFoldingTest(state::comparingLocalDatesCollapse)
    }

    /**
     * [data.LocalDateLiteralTestData]
     */
    @Test fun testLocalDateLiteralTestData() {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse)
    }

    /**
     * [data.LocalDateLiteralPostfixTestData]
     */
    @Test fun testLocalDateLiteralPostfixTestData() {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse, state::localDateLiteralPostfixCollapse)
    }

    /**
     * [data.CompactControlFlowTestData]
     */
    @Test fun testCompactControlFlowTestData() {
        doFoldingTest(state::compactControlFlowSyntaxCollapse)
    }

    /**
     * [data.SemicolonTestData]
     */
    @Test fun testSemicolonTestData() {
        doReadOnlyFoldingTest(state::semicolonsCollapse)
    }

    /**
     * [data.AssertTestData]
     */
    @Test fun testAssertTestData() {
        doReadOnlyFoldingTest(state::assertsCollapse)
    }

    /**
     * [data.ConcatenationTestData]
     */
    @Test fun testConcatenationTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.OptionalTestData]
     */
    @Test fun testOptionalTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.SpreadTestData]
     */
    @Test fun testSpreadTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.LombokTestData]
     */
    @Test fun testLombokTestData() {
        doFoldingTest(state::lombok)
    }

    @Test fun testLombokUsageTestData() {
        doFoldingTest(state::lombok)
    }

    /**
     * [data.FieldShiftBuilder]
     */
    @Test fun testFieldShiftBuilder() {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    /**
     * [data.FieldShiftSetters]
     */
    @Test fun testFieldShiftSetters() {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    /**
     * [data.LetReturnIt]
     */
    @Test fun testLetReturnIt() {
        doFoldingTest(state::varExpressionsCollapse, state::kotlinQuickReturn)
    }

    /**
     * [data.IfNullSafeData]
     */
    @Test fun testIfNullSafeData() {
        doFoldingTest(state::checkExpressionsCollapse, state::getSetExpressionsCollapse, state::ifNullSafe)
    }

    /**
     * [data.LogBrackets]
     */
    @Test fun testLogBrackets() {
        doFoldingTest(state::getSetExpressionsCollapse, state::logFolding)
    }

    /**
     * [data.FieldShiftFields]
     */
    @Test fun testFieldShiftFields() {
        doFoldingTest(state::getSetExpressionsCollapse, state::fieldShift)
    }

    /**
     * [data.DestructuringAssignmentArrayTestData]
     */
    @Test fun testDestructuringAssignmentArrayTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse, state::varExpressionsCollapse)
    }

    /**
     * [data.DestructuringAssignmentArrayWithoutValTestData]
     */
    open @Test fun testDestructuringAssignmentArrayWithoutValTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse)
    }

    /**
     * [data.DestructuringAssignmentListTestData]
     */
    @Test fun testDestructuringAssignmentListTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse, state::varExpressionsCollapse)
    }

    /**
     * [data.DestructuringAssignmentListWithoutValTestData]
     */
    open @Test fun testDestructuringAssignmentListWithoutValTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse)
    }

    /**
     * [data.PrintlnTestData]
     */
    @Test fun testPrintlnTestData() {
        doFoldingTest()
    }
    /**
     * [data.NullableAnnotationTestData]
     */
    @Test fun testNullableAnnotationTestData() {
        doFoldingTest(state::nullable, state::lombok)
    }

    /**
     * [data.NullableAnnotationCheckNotNullTestData]
     */
    @Test fun testNullableAnnotationCheckNotNullTestData() {
        doFoldingTest(state::nullable, state::lombok)
    }
    /**
     * [data.ConstTestData]
     */
    @Test fun testConstTestData() {
        doFoldingTest(state::const)
    }
    /**
     * [data.FinalRemovalTestData]
     */
    @Test fun testFinalRemovalTestData() {
        doFoldingTest(state::finalRemoval)
    }
    /**
     * [data.FinalEmojiTestData]
     */
    @Test fun testFinalEmojiTestData() {
        doFoldingTest(state::finalEmoji)
    }

    /**
     * [data.LombokDirtyOffTestData]
     */
    @Test fun testLombokDirtyOffTestData() {
        doFoldingTest(state::lombok, state::lombokDirtyOff)
    }

    /**
     * [data.ExpressionFuncTestData]
     */
    @Test fun testExpressionFuncTestData() {
        doFoldingTest(state::expressionFunc)
    }

    /**
     * [data.DynamicTestData]
     */
    @Test
    fun testDynamicTestData() {
        try {
            val dynamicProvider = object : IDynamicDataProvider {
                override fun parse(): List<DynamicMethodCall> {
                    return parseToml(
                        """
[main1desc]
method = "main"
newName = "mainek1"

[main2desc]
method = "main2"
newName = "mainek2"

[main3desc]
method = "main3"
newName = "mainek3"
                    """.trimIndent()
                    )
                }

            }
            doFoldingTest(state::dynamic, dynamic = dynamicProvider)
        } finally {
            MethodCallFactory.refreshMethodCallMappings(null)
        }
    }

    /**
     * [data.ArithmeticExpressionsTestData]
     */
    @Test
    fun testArithmeticExpressionsTestData() {
        doFoldingTest(state::arithmeticExpressions)
    }

    /**
     * [data.EmojifyTestData]
     */
    @Test
    fun testEmojifyTestData() {
        doFoldingTest(state::emojify)
    }
    // NEW OPTION
    /**
     * [data.ExperimentalTestData]
     */
    @Test fun testExperimentalTestData() {
        doFoldingTest(state::experimental, state::nullable, state::const, state::lombok, state::getExpressionsCollapse)
    }

}