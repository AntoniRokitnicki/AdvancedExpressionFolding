package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.State
import com.intellij.advancedExpressionFolding.extension.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.IDynamicDataProvider
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
            throw RuntimeException("FileComparisonFailedError")
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
    fun elvisTestData() {
        doFoldingTest(state::checkExpressionsCollapse)
    }

    /**
     * [data.ForRangeTestData]
     */
    @Test
    fun forRangeTestData() {
        doFoldingTest(state::rangeExpressionsCollapse)
    }

    /**
     * [data.StringBuilderTestData]
     */
    @Test
    fun stringBuilderTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    /**
     * [data.InterpolatedStringTestData]
     */
    @Test
    fun interpolatedStringTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse)
    }

    /**
     * [data.GetSetPutTestData]
     */
    @Test
    fun getSetPutTestData() {
        doFoldingTest(state::getExpressionsCollapse)
    }

    /**
     * [data.SliceTestData]
     */
    @Test
    fun sliceTestData() {
        doFoldingTest(state::slicingExpressionsCollapse)
    }

    /**
     * [data.AppendSetterInterpolatedStringTestData]
     */
    @Test
    fun appendSetterInterpolatedStringTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::getSetExpressionsCollapse)
    }

    /**
     * [data.EqualsCompareTestData]
     */
    @Test
    fun equalsCompareTestData() {
        doFoldingTest(state::comparingExpressionsCollapse)
    }

    /**
     * [data.TypeCastTestData]
     */
    @Test
    fun typeCastTestData() {
        doFoldingTest(state::castExpressionsCollapse)
    }

    /**
     * [data.VarTestData]
     */
    @Test
    fun varTestData() {
        doFoldingTest(state::varExpressionsCollapse)
    }

    /**
     * [data.GetterSetterTestData]
     */
    @Test
    fun getterSetterTestData() {
        doFoldingTest(state::getSetExpressionsCollapse)
    }

    /**
     * [data.ControlFlowSingleStatementTestData]
     */
    @Test
    fun controlFlowSingleStatementTestData() {
        // TODO: Test with different indentation settings
        doReadOnlyFoldingTest(state::controlFlowSingleStatementCodeBlockCollapse)
    }

    /**
     * [data.ControlFlowMultiStatementTestData]
     */
    @Test
    fun controlFlowMultiStatementTestData() {
        // TODO: Test with different indentation settings
        doReadOnlyFoldingTest(state::controlFlowMultiStatementCodeBlockCollapse)
    }

    /**
     * [data.LocalDateTestData]
     */
    @Test
    fun localDateTestData() {
        doReadOnlyFoldingTest(state::comparingLocalDatesCollapse)
    }

    /**
     * [data.LocalDateLiteralTestData]
     */
    @Test
    fun localDateLiteralTestData() {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse)
    }

    /**
     * [data.LocalDateLiteralPostfixTestData]
     */
    @Test
    fun localDateLiteralPostfixTestData() {
        doReadOnlyFoldingTest(state::localDateLiteralCollapse, state::localDateLiteralPostfixCollapse)
    }

    /**
     * [data.CompactControlFlowTestData]
     */
    @Test
    fun compactControlFlowTestData() {
        doFoldingTest(state::compactControlFlowSyntaxCollapse)
    }

    /**
     * [data.SemicolonTestData]
     */
    @Test
    fun semicolonTestData() {
        doReadOnlyFoldingTest(state::semicolonsCollapse)
    }

    /**
     * [data.AssertTestData]
     */
    @Test
    fun assertTestData() {
        doReadOnlyFoldingTest(state::assertsCollapse)
    }

    /**
     * [data.ConcatenationTestData]
     */
    @Test
    fun concatenationTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.OptionalTestData]
     */
    @Test
    fun optionalTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.SpreadTestData]
     */
    @Test
    fun spreadTestData() {
        doFoldingTest(state::concatenationExpressionsCollapse, state::optional, state::streamSpread)
    }

    /**
     * [data.LombokTestData]
     */
    @Test
    fun lombokTestData() {
        doFoldingTest(state::lombok)
    }

    @Test
    fun lombokUsageTestData() {
        doFoldingTest(state::lombok)
    }

    /**
     * [data.FieldShiftBuilder]
     */
    @Test
    fun fieldShiftBuilder() {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    /**
     * [data.FieldShiftSetters]
     */
    @Test
    fun fieldShiftSetters() {
        doFoldingTest(state::fieldShift, state::getSetExpressionsCollapse)
    }

    /**
     * [data.LetReturnIt]
     */
    @Test
    fun letReturnIt() {
        doFoldingTest(state::varExpressionsCollapse, state::kotlinQuickReturn)
    }

    /**
     * [data.IfNullSafeData]
     */
    @Test
    fun ifNullSafeData() {
        doFoldingTest(state::checkExpressionsCollapse, state::getSetExpressionsCollapse, state::ifNullSafe)
    }

    /**
     * [data.LogBrackets]
     */
    @Test
    fun logBrackets() {
        doFoldingTest(state::getSetExpressionsCollapse, state::logFolding)
    }

    /**
     * [data.FieldShiftFields]
     */
    @Test
    fun fieldShiftFields() {
        doFoldingTest(state::getSetExpressionsCollapse, state::fieldShift)
    }

    /**
     * [data.DestructuringAssignmentArrayTestData]
     */
    @Test
    fun destructuringAssignmentArrayTestData() {
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
    fun destructuringAssignmentListTestData() {
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
    fun printlnTestData() {
        doFoldingTest(state::println)
    }

    /**
     * [data.NullableAnnotationTestData]
     */
    @Test
    fun nullableAnnotationTestData() {
        doFoldingTest(state::nullable, state::lombok)
    }

    /**
     * [data.NullableAnnotationCheckNotNullTestData]
     */
    @Test
    fun nullableAnnotationCheckNotNullTestData() {
        doFoldingTest(state::nullable, state::lombok)
    }

    /**
     * [data.ConstTestData]
     */
    @Test
    fun constTestData() {
        doFoldingTest(state::const)
    }

    /**
     * [data.FinalRemovalTestData]
     */
    @Test
    fun finalRemovalTestData() {
        doFoldingTest(state::finalRemoval)
    }

    /**
     * [data.FinalEmojiTestData]
     */
    @Test
    fun finalEmojiTestData() {
        doFoldingTest(state::finalEmoji)
    }

    /**
     * [data.LombokDirtyOffTestData]
     */
    @Test
    fun lombokDirtyOffTestData() {
        doFoldingTest(state::lombok, state::lombokDirtyOff)
    }

    /**
     * [data.ExpressionFuncTestData]
     */
    @Test
    fun expressionFuncTestData() {
        doFoldingTest(state::expressionFunc)
    }

    /**
     * [data.DynamicTestData]
     */
    @Test
    fun dynamicTestData() {
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
    fun arithmeticExpressionsTestData() {
        doFoldingTest(state::arithmeticExpressions)
    }

    /**
     * [data.EmojifyTestData]
     */
    @Test
    fun emojifyTestData() {
        doFoldingTest(state::emojify)
    }

    /**
     * [data.InterfaceExtensionPropertiesTestData]
     */
    @Test
    fun interfaceExtensionPropertiesTestData() {
        doFoldingTest(state::interfaceExtensionProperties, state::lombok, state::nullable)
    }

    /**
     * [data.PatternMatchingInstanceofTestData]
     */
    @Test
    fun patternMatchingInstanceofTestData() {
        doFoldingTest(state::patternMatchingInstanceof)
    }

    /**
     * [data.SummaryParentOverrideTestData]
     */
    @Test
    fun summaryParentOverrideTestData() {
        doFoldingTest(state::summaryParentOverride)
    }

    /**
     * [data.ConstructorReferenceNotationTestData]
     */
    @Test
    fun constructorReferenceNotationTestData() {
        doFoldingTest(state::constructorReferenceNotation)
    }

    /**
     * [data.ConstructorReferenceNotationWithConstTestData]
     */
    @Test
    fun constructorReferenceNotationWithConstTestData() {
        doFoldingTest(state::constructorReferenceNotation, state::const)
    }


    /**
     * [data.MethodDefaultParametersTestData]
     */
    @Test
    fun methodDefaultParametersTestData() {
        doFoldingTest(state::methodDefaultParameters)
    }

    /**
     * [data.LombokPatternOffTestData]
     */
    @Test
    fun lombokPatternOffTestData() {
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
    fun lombokPatternOffNegativeTestData() {
        state.lombokPatternOff = "LombokPatternOffTestData"
        try {
            doFoldingTest(state::lombok)
        } finally {
            state.lombokPatternOff = null
        }
    }
    // NEW OPTION
    /**
     * [data.ExperimentalTestData]
     */
    @Test
    fun experimentalTestData() {
        doFoldingTest(state::experimental, state::nullable, state::const, state::lombok, state::getExpressionsCollapse)
    }

}