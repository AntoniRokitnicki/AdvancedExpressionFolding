package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import com.intellij.openapi.application.runInEdt
import com.intellij.platform.testFramework.core.FileComparisonFailedError
import org.junit.jupiter.api.Test
import org.junitpioneer.jupiter.Stopwatch
import org.opentest4j.TestAbortedException
import kotlin.reflect.KMutableProperty0

@Stopwatch
//TODO: maybe use @RetryingTest(maxAttempts = 3, suspendForMs = 100, onExceptions = <FindName>.class) when rarely IDE can't be start
open class FoldingTest : BaseTest() {

    class TooComplexException : TestAbortedException("TOO COMPLEX FOLDING")
    class FoldingChangedException : AssertionError()

    protected val state: State by lazy {
        getInstance().state
    }

    open fun assignState(vararg turnOnProperties: KMutableProperty0<Boolean>,) {
        getInstance().disableAll()
        turnOnProperties.forEach {
            it.set(true)
        }
    }

    open fun doFoldingTest(
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider(),
    ) {
        assignState(*turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        try {
            super.doFoldingTest(null)
        } catch (_: FileComparisonFailedError) {
            throw FoldingChangedException()
        } catch (e: IllegalArgumentException) {
            if (e.message == "Comparison method violates its general contract!") {
                throw TooComplexException()
            }
        }
    }

    private fun doReadOnlyFoldingTest(
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider()
    ) {
        assignState(*turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        runInEdt {
            super.doReadOnlyFoldingTest()
        }
    }

    /**
     * [data.ElvisTestData]
     */
    @Test
    open fun elvisTestData() {
        doFoldingTest(state::checkExpressionsCollapse)
    }

    /**
     * [data.ForRangeTestData]
     */
    @Test
    open fun forRangeTestData() {
        doFoldingTest(state::rangeExpressionsCollapse)
    }

    /**
     * [data.StringBuilderTestData]
     */
    @Test
    open fun stringBuilderTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    /**
     * [data.InterpolatedStringTestData]
     */
    @Test
    open fun interpolatedStringTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    /**
     * [data.GetSetPutTestData]
     */
    @Test
    open fun getSetPutTestData() {
        doFoldingTest(state::getExpressionsCollapse)
    }

    /**
     * [data.SliceTestData]
     */
    @Test
    open fun sliceTestData() {
        doFoldingTest(state::slicingExpressionsCollapse)
    }

    /**
     * [data.AppendSetterInterpolatedStringTestData]
     */
    @Test
    open fun appendSetterInterpolatedStringTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::getSetExpressionsCollapse)
    }

    /**
     * [data.EqualsCompareTestData]
     */
    @Test
    open fun equalsCompareTestData() {
        doFoldingTest(state::comparingExpressionsCollapse)
    }

    /**
     * [data.TypeCastTestData]
     */
    @Test
    open fun typeCastTestData() {
        doFoldingTest(state::castExpressionsCollapse)
    }

    /**
     * [data.VarTestData]
     */
    @Test
    open fun varTestData() {
        doFoldingTest(state::varExpressionsCollapse)
    }

    /**
     * [data.GetterSetterTestData]
     */
    @Test
    open fun getterSetterTestData() {
        doFoldingTest(state::getSetExpressionsCollapse)
    }

    /**
     * [data.ControlFlowSingleStatementTestData]
     */
    @Test
    open fun controlFlowSingleStatementTestData() {
        // TODO: Test with different indentation settings
        doReadOnlyFoldingTest(state::controlFlowSingleStatementCodeBlockCollapse)
    }

    /**
     * [data.ControlFlowMultiStatementTestData]
     */
    @Test
    open fun controlFlowMultiStatementTestData() {
        // TODO: Test with different indentation settings
        doReadOnlyFoldingTest(state::controlFlowMultiStatementCodeBlockCollapse)
    }

    /**
     * [data.LocalDateTestData]
     */
    @Test
    open fun localDateTestData() {
        doReadOnlyFoldingTest(state::comparingLocalDatesCollapse)
    }

    /**
     * [data.LocalDateLiteralTestData]
     */
    @Test
    open fun localDateLiteralTestData() {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse)
    }

    /**
     * [data.LocalDateLiteralPostfixTestData]
     */
    @Test
    open fun localDateLiteralPostfixTestData() {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse, state::localDateLiteralPostfixCollapse)
    }

    /**
     * [data.CompactControlFlowTestData]
     */
    @Test
    open fun compactControlFlowTestData() {
        doFoldingTest(state::compactControlFlowSyntaxCollapse)
    }

    /**
     * [data.SemicolonTestData]
     */
    @Test
    open fun semicolonTestData() {
        doReadOnlyFoldingTest(state::semicolonsCollapse)
    }

    /**
     * [data.AssertTestData]
     */
    @Test
    open fun assertTestData() {
        doReadOnlyFoldingTest(state::assertsCollapse)
    }

    /**
     * [data.ConcatenationTestData]
     */
    @Test
    open fun concatenationTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.OptionalTestData]
     */
    @Test
    open fun optionalTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.SpreadTestData]
     */
    @Test
    open fun spreadTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.LombokTestData]
     */
    @Test
    open fun lombokTestData() {
        doFoldingTest(state::lombok)
    }

    @Test
    open fun lombokUsageTestData() {
        doFoldingTest(state::lombok)
    }

    /**
     * [data.FieldShiftBuilder]
     */
    @Test
    open fun fieldShiftBuilder() {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    /**
     * [data.FieldShiftSetters]
     */
    @Test
    open fun fieldShiftSetters() {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    /**
     * [data.LetReturnIt]
     */
    @Test
    open fun letReturnIt() {
        doFoldingTest(state::varExpressionsCollapse, state::kotlinQuickReturn)
    }

    /**
     * [data.IfNullSafeData]
     */
    @Test
    open fun ifNullSafeData() {
        doFoldingTest(state::checkExpressionsCollapse, state::getSetExpressionsCollapse, state::ifNullSafe)
    }

    /**
     * [data.LogBrackets]
     */
    @Test
    open fun logBrackets() {
        doFoldingTest(state::getSetExpressionsCollapse, state::logFolding)
    }

    /**
     * [data.FieldShiftFields]
     */
    @Test
    open fun fieldShiftFields() {
        doFoldingTest(state::getSetExpressionsCollapse, state::fieldShift)
    }

    /**
     * [data.DestructuringAssignmentArrayTestData]
     */
    @Test
    open fun destructuringAssignmentArrayTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse, state::varExpressionsCollapse)
    }

    /**
     * [data.DestructuringAssignmentArrayWithoutValTestData]
     */
    @Test
    open fun destructuringAssignmentArrayWithoutValTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse)
    }

    /**
     * [data.DestructuringAssignmentListTestData]
     */
    @Test
    open fun destructuringAssignmentListTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse, state::varExpressionsCollapse)
    }

    /**
     * [data.DestructuringAssignmentListWithoutValTestData]
     */
    @Test
    open fun destructuringAssignmentListWithoutValTestData() {
        doFoldingTest(state::destructuring, state::getSetExpressionsCollapse)
    }

    /**
     * [data.PrintlnTestData]
     */
    @Test
    open fun printlnTestData() {
        doFoldingTest(state::println)
    }

    /**
     * [data.NullableAnnotationTestData]
     */
    @Test
    open fun nullableAnnotationTestData() {
        doFoldingTest(state::nullable, state::lombok)
    }

    /**
     * [data.NullableAnnotationCheckNotNullTestData]
     */
    @Test
    open fun nullableAnnotationCheckNotNullTestData() {
        doFoldingTest(state::nullable, state::getSetExpressionsCollapse)
    }
    /**
     * [data.NullableAnnotationCheckNotNullFieldShiftTestData]
     */
    @Test
    open fun nullableAnnotationCheckNotNullFieldShiftTestData() {
        doFoldingTest(state::nullable, state::getSetExpressionsCollapse, state::fieldShift)
    }

    /**
     * [data.ConstTestData]
     */
    @Test
    open fun constTestData() {
        doFoldingTest(state::const)
    }

    /**
     * [data.FinalRemovalTestData]
     */
    @Test
    open fun finalRemovalTestData() {
        doFoldingTest(state::finalRemoval)
    }

    /**
     * [data.FinalEmojiTestData]
     */
    @Test
    open fun finalEmojiTestData() {
        @Suppress("DEPRECATION")
        doFoldingTest(state::finalEmoji)
    }

    /**
     * [data.LombokDirtyOffTestData]
     */
    @Test
    open fun lombokDirtyOffTestData() {
        doFoldingTest(state::lombok, state::lombokDirtyOff)
    }

    /**
     * [data.Expressionopen funcTestData]
     */
    @Test
    open fun expressionFuncTestData() {
        doFoldingTest(state::expressionFunc)
    }

    /**
     * [data.DynamicTestData]
     */
    @Test
    open fun dynamicTestData() {
        val dynamicProvider = object : IDynamicDataProvider {
            override fun parse(): List<DynamicMethodCall> {
                return parseToml(
                    """
normalMethod.method = 'normalMethod'
normalMethod.newName = 'changedNormalMethod'
staticMethod.method = 'staticMethod'
staticMethod.newName = 'changedStaticMethod'
                """.trimIndent()
                )
            }

        }
        doFoldingTest(state::dynamic, state::getSetExpressionsCollapse, dynamic = dynamicProvider)
    }

    /**
     * [data.ArithmeticExpressionsTestData]
     */
    @Test
    open fun arithmeticExpressionsTestData() {
        @Suppress("DEPRECATION")
        doFoldingTest(state::arithmeticExpressions)
    }

    /**
     * [data.EmojifyTestData]
     */
    @Test
    open fun emojifyTestData() {
        @Suppress("DEPRECATION")
        doFoldingTest(state::emojify)
    }

    /**
     * [data.InterfaceExtensionPropertiesTestData]
     */
    @Test
    open fun interfaceExtensionPropertiesTestData() {
        doFoldingTest(state::interfaceExtensionProperties, state::lombok, state::nullable)
    }

    /**
     * [data.PatternMatchingInstanceofTestData]
     */
    @Test
    open fun patternMatchingInstanceofTestData() {
        doFoldingTest(state::patternMatchingInstanceof)
    }

    /**
     * [data.SummaryParentOverrideTestData]
     */
    @Test
    open fun summaryParentOverrideTestData() {
        doFoldingTest(state::summaryParentOverride)
    }

    /**
     * [data.ConstructorReferenceNotationTestData]
     */
    @Test
    open fun constructorReferenceNotationTestData() {
        doFoldingTest(state::constructorReferenceNotation)
    }

    /**
     * [data.ConstructorReferenceNotationWithConstTestData]
     */
    @Test
    open fun constructorReferenceNotationWithConstTestData() {
        doFoldingTest(state::constructorReferenceNotation, state::const)
    }


    /**
     * [data.MethodDefaultParametersTestData]
     */
    @Test
    open fun methodDefaultParametersTestData() {
        doFoldingTest(state::methodDefaultParameters)
    }

    /**
     * [data.LombokPatternOffTestData]
     */
    @Test
    open fun lombokPatternOffTestData() {
        state.lombokPatternOff = "LombokPa[t]{2}ernOffTestData"
        try {
            doFoldingTest(state::lombok)
        } finally {
            state.lombokPatternOff = null
        }
    }
    /**
     * [data.LombokPatternOffNegativeTestData]
     */
    @Test
    open fun lombokPatternOffNegativeTestData() {
        state.lombokPatternOff = "LombokPatternOffTestData"
        try {
            doFoldingTest(state::lombok)
        } finally {
            state.lombokPatternOff = null
        }
    }

    /**
     * [data.OverrideHideTestData]
     */
    @Test
    fun overrideHideTestData() {
        doFoldingTest(state::overrideHide)
    }
    /**
     * [data.SuppressWarningsHideTestData]
     */
    @Test
    fun suppressWarningsHideTestData() {
        doFoldingTest(state::suppressWarningsHide)
    }
    // NEW OPTION
    /**
     * [data.ExperimentalTestData]
     */
    @Test
    open fun experimentalTestData() {
        doFoldingTest(state::experimental, state::nullable, state::const, state::lombok, state::getExpressionsCollapse)
    }

}
