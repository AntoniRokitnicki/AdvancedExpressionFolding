package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import com.intellij.openapi.application.ApplicationManager
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
        doFoldingTestInternal(null, *turnOnProperties, dynamic = dynamic)
    }

    open fun doFoldingTest(
        testName: String,
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider = TestDynamicDataProvider(),
    ) {
        doFoldingTestInternal(testName, *turnOnProperties, dynamic = dynamic)
    }

    private fun doFoldingTestInternal(
        testName: String?,
        vararg turnOnProperties: KMutableProperty0<Boolean>,
        dynamic: IDynamicDataProvider,
    ) {
        assignState(*turnOnProperties)
        MethodCallFactory.initialize(dynamic)
        try {
            super.doFoldingTest(testName)
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
    ) {
        doReadOnlyFoldingTestInternal(null, *turnOnProperties)
    }

    private fun doReadOnlyFoldingTest(
        testName: String,
        vararg turnOnProperties: KMutableProperty0<Boolean>,
    ) {
        doReadOnlyFoldingTestInternal(testName, *turnOnProperties)
    }

    private fun doReadOnlyFoldingTestInternal(
        testName: String?,
        vararg turnOnProperties: KMutableProperty0<Boolean>,
    ) {
        assignState(*turnOnProperties)
        MethodCallFactory.initialize(TestDynamicDataProvider())
        ApplicationManager.getApplication().invokeAndWait {
            if (testName != null) {
                super.doReadOnlyFoldingTest(testName)
            } else {
                super.doReadOnlyFoldingTest()
            }
        }
    }

    protected open fun registerGeneratedTests(builder: FoldingTestBuilder) {
        with(builder) {
            folding("elvisTestData", state::checkExpressionsCollapse)
            folding("forRangeTestData", state::rangeExpressionsCollapse)
            folding("stringBuilderTestData", state::concatenationExpressionsCollapse)
            folding("interpolatedStringTestData", state::concatenationExpressionsCollapse)
            folding("getSetPutTestData", state::getExpressionsCollapse)
            folding("sliceTestData", state::slicingExpressionsCollapse)
            folding(
                "appendSetterInterpolatedStringTestData",
                state::concatenationExpressionsCollapse,
                state::getSetExpressionsCollapse,
            )
            folding("equalsCompareTestData", state::comparingExpressionsCollapse)
            folding("typeCastTestData", state::castExpressionsCollapse)
            folding("varTestData", state::varExpressionsCollapse)
            folding("getterSetterTestData", state::getSetExpressionsCollapse)
            readOnly("controlFlowSingleStatementTestData", state::controlFlowSingleStatementCodeBlockCollapse)
            readOnly("controlFlowMultiStatementTestData", state::controlFlowMultiStatementCodeBlockCollapse)
            readOnly("localDateTestData", state::comparingLocalDatesCollapse)
            readOnly("localDateLiteralTestData", state::localDateLiteralCollapse)
            readOnly(
                "localDateLiteralPostfixTestData",
                state::localDateLiteralCollapse,
                state::localDateLiteralPostfixCollapse,
            )
            folding("compactControlFlowTestData", state::compactControlFlowSyntaxCollapse)
            readOnly("semicolonTestData", state::semicolonsCollapse)
            readOnly("assertTestData", state::assertsCollapse)
            folding(
                "concatenationTestData",
                state::concatenationExpressionsCollapse,
                state::optional,
                state::streamSpread,
            )
            folding(
                "optionalTestData",
                state::concatenationExpressionsCollapse,
                state::optional,
                state::streamSpread,
            )
            folding(
                "spreadTestData",
                state::concatenationExpressionsCollapse,
                state::optional,
                state::streamSpread,
            )
            folding("lombokTestData", state::lombok)
            folding("lombokUsageTestData", state::lombok)
            folding("fieldShiftBuilder", state::fieldShift, state::getSetExpressionsCollapse)
            folding("fieldShiftSetters", state::fieldShift, state::getSetExpressionsCollapse)
            folding("letReturnIt", state::varExpressionsCollapse, state::kotlinQuickReturn)
            folding(
                "ifNullSafeData",
                state::checkExpressionsCollapse,
                state::getSetExpressionsCollapse,
                state::ifNullSafe,
            )
            folding("logBrackets", state::getSetExpressionsCollapse, state::logFolding)
            folding(
                "logFoldingTextBlocksTestData",
                state::getSetExpressionsCollapse,
                state::logFolding,
                state::logFoldingTextBlocks,
            )
            folding("fieldShiftFields", state::getSetExpressionsCollapse, state::fieldShift)
            folding(
                "destructuringAssignmentArrayTestData",
                state::destructuring,
                state::getSetExpressionsCollapse,
                state::varExpressionsCollapse,
            )
            folding(
                "destructuringAssignmentArrayWithoutValTestData",
                state::destructuring,
                state::getSetExpressionsCollapse,
            )
            folding(
                "destructuringAssignmentListTestData",
                state::destructuring,
                state::getSetExpressionsCollapse,
                state::varExpressionsCollapse,
            )
            folding(
                "destructuringAssignmentListWithoutValTestData",
                state::destructuring,
                state::getSetExpressionsCollapse,
            )
            folding("printlnTestData", state::println)
            folding("nullableAnnotationTestData", state::nullable, state::lombok)
            folding(
                "nullableAnnotationCheckNotNullTestData",
                state::nullable,
                state::getSetExpressionsCollapse,
            )
            folding(
                "nullableAnnotationCheckNotNullFieldShiftTestData",
                state::nullable,
                state::getSetExpressionsCollapse,
                state::fieldShift,
            )
            folding("constTestData", state::const)
            folding("finalRemovalTestData", state::finalRemoval)
            folding("lombokDirtyOffTestData", state::lombok, state::lombokDirtyOff)
            folding("expressionFuncTestData", state::expressionFunc)
            folding(
                "interfaceExtensionPropertiesTestData",
                state::interfaceExtensionProperties,
                state::lombok,
                state::nullable,
            )
            folding("patternMatchingInstanceofTestData", state::patternMatchingInstanceof)
            folding("summaryParentOverrideTestData", state::summaryParentOverride)
            folding("constructorReferenceNotationTestData", state::constructorReferenceNotation)
            folding(
                "constructorReferenceNotationWithConstTestData",
                state::constructorReferenceNotation,
                state::const,
            )
            folding("methodDefaultParametersTestData", state::methodDefaultParameters)
            folding("overrideHideTestData", state::overrideHide)
            folding("suppressWarningsHideTestData", state::suppressWarningsHide)
            folding(
                "experimentalTestData",
                state::experimental,
                state::nullable,
                state::const,
                state::lombok,
                state::getExpressionsCollapse,
            )
        }
    }

    private val generatedCases by lazy { buildFoldingTestCases { registerGeneratedTests(this) } }
    private val casesByName by lazy { generatedCases.associateBy { it.testName } }

    protected open val manualTestNames: Set<String> = setOf(
        "finalEmojiTestData",
        "dynamicTestData",
        "arithmeticExpressionsTestData",
        "emojifyTestData",
        "lombokPatternOffTestData",
        "lombokPatternOffNegativeTestData",
    )

    protected open val skippedGeneratedTests: Set<String> = emptySet()

    protected fun runGeneratedTest(testName: String) {
        val case = casesByName[testName]
            ?: if (testName in skippedGeneratedTests) {
                return
            } else {
                error("Unknown folding test: $testName")
            }
        val toggles = case.toggles.toTypedArray()
        if (case.readOnly) {
            doReadOnlyFoldingTest(case.testName, *toggles)
        } else {
            doFoldingTest(case.testName, *toggles, dynamic = case.dynamicProviderFactory())
        }
    }

    protected fun hasGeneratedTest(testName: String): Boolean =
        casesByName.containsKey(testName)

    protected fun ensureGeneratedTest(testName: String) {
        require(hasGeneratedTest(testName) || testName in manualTestNames) { "Unknown folding test: $testName" }
    }

    /**
     * [data.ElvisTestData]
     */
    @Test
    open fun elvisTestData() = runGeneratedTest("elvisTestData")

    /**
     * [data.ForRangeTestData]
     */
    @Test
    open fun forRangeTestData() = runGeneratedTest("forRangeTestData")

    /**
     * [data.StringBuilderTestData]
     */
    @Test
    open fun stringBuilderTestData() = runGeneratedTest("stringBuilderTestData")

    /**
     * [data.InterpolatedStringTestData]
     */
    @Test
    open fun interpolatedStringTestData() = runGeneratedTest("interpolatedStringTestData")

    /**
     * [data.GetSetPutTestData]
     */
    @Test
    open fun getSetPutTestData() = runGeneratedTest("getSetPutTestData")

    /**
     * [data.SliceTestData]
     */
    @Test
    open fun sliceTestData() = runGeneratedTest("sliceTestData")

    /**
     * [data.AppendSetterInterpolatedStringTestData]
     */
    @Test
    open fun appendSetterInterpolatedStringTestData() = runGeneratedTest("appendSetterInterpolatedStringTestData")

    /**
     * [data.EqualsCompareTestData]
     */
    @Test
    open fun equalsCompareTestData() = runGeneratedTest("equalsCompareTestData")

    /**
     * [data.TypeCastTestData]
     */
    @Test
    open fun typeCastTestData() = runGeneratedTest("typeCastTestData")

    /**
     * [data.VarTestData]
     */
    @Test
    open fun varTestData() = runGeneratedTest("varTestData")

    /**
     * [data.GetterSetterTestData]
     */
    @Test
    open fun getterSetterTestData() = runGeneratedTest("getterSetterTestData")

    /**
     * [data.ControlFlowSingleStatementTestData]
     */
    // TODO: Test with different indentation settings
    @Test
    open fun controlFlowSingleStatementTestData() = runGeneratedTest("controlFlowSingleStatementTestData")

    /**
     * [data.ControlFlowMultiStatementTestData]
     */
    // TODO: Test with different indentation settings
    @Test
    open fun controlFlowMultiStatementTestData() = runGeneratedTest("controlFlowMultiStatementTestData")

    /**
     * [data.LocalDateTestData]
     */
    @Test
    open fun localDateTestData() = runGeneratedTest("localDateTestData")

    /**
     * [data.LocalDateLiteralTestData]
     */
    @Test
    open fun localDateLiteralTestData() = runGeneratedTest("localDateLiteralTestData")

    /**
     * [data.LocalDateLiteralPostfixTestData]
     */
    @Test
    open fun localDateLiteralPostfixTestData() = runGeneratedTest("localDateLiteralPostfixTestData")

    /**
     * [data.CompactControlFlowTestData]
     */
    @Test
    open fun compactControlFlowTestData() = runGeneratedTest("compactControlFlowTestData")

    /**
     * [data.SemicolonTestData]
     */
    @Test
    open fun semicolonTestData() = runGeneratedTest("semicolonTestData")

    /**
     * [data.AssertTestData]
     */
    @Test
    open fun assertTestData() = runGeneratedTest("assertTestData")

    /**
     * [data.ConcatenationTestData]
     */
    @Test
    open fun concatenationTestData() = runGeneratedTest("concatenationTestData")

    /**
     * [data.OptionalTestData]
     */
    @Test
    open fun optionalTestData() = runGeneratedTest("optionalTestData")

    /**
     * [data.SpreadTestData]
     */
    @Test
    open fun spreadTestData() = runGeneratedTest("spreadTestData")

    /**
     * [data.LombokTestData]
     */
    @Test
    open fun lombokTestData() = runGeneratedTest("lombokTestData")

    /**
     * [data.LombokUsageTestData]
     */
    @Test
    open fun lombokUsageTestData() = runGeneratedTest("lombokUsageTestData")

    /**
     * [data.FieldShiftBuilder]
     */
    @Test
    open fun fieldShiftBuilder() = runGeneratedTest("fieldShiftBuilder")

    /**
     * [data.FieldShiftSetters]
     */
    @Test
    open fun fieldShiftSetters() = runGeneratedTest("fieldShiftSetters")

    /**
     * [data.LetReturnIt]
     */
    @Test
    open fun letReturnIt() = runGeneratedTest("letReturnIt")

    /**
     * [data.IfNullSafeData]
     */
    @Test
    open fun ifNullSafeData() = runGeneratedTest("ifNullSafeData")

    /**
     * [data.LogBrackets]
     */
    @Test
    open fun logBrackets() = runGeneratedTest("logBrackets")

    /**
     * [data.LogFoldingTextBlocksTestData]
     */
    @Test
    open fun logFoldingTextBlocksTestData() = runGeneratedTest("logFoldingTextBlocksTestData")

    /**
     * [data.FieldShiftFields]
     */
    @Test
    open fun fieldShiftFields() = runGeneratedTest("fieldShiftFields")

    /**
     * [data.DestructuringAssignmentArrayTestData]
     */
    @Test
    open fun destructuringAssignmentArrayTestData() = runGeneratedTest("destructuringAssignmentArrayTestData")

    /**
     * [data.DestructuringAssignmentArrayWithoutValTestData]
     */
    @Test
    open fun destructuringAssignmentArrayWithoutValTestData() = runGeneratedTest("destructuringAssignmentArrayWithoutValTestData")

    /**
     * [data.DestructuringAssignmentListTestData]
     */
    @Test
    open fun destructuringAssignmentListTestData() = runGeneratedTest("destructuringAssignmentListTestData")

    /**
     * [data.DestructuringAssignmentListWithoutValTestData]
     */
    @Test
    open fun destructuringAssignmentListWithoutValTestData() = runGeneratedTest("destructuringAssignmentListWithoutValTestData")

    /**
     * [data.PrintlnTestData]
     */
    @Test
    open fun printlnTestData() = runGeneratedTest("printlnTestData")

    /**
     * [data.NullableAnnotationTestData]
     */
    @Test
    open fun nullableAnnotationTestData() = runGeneratedTest("nullableAnnotationTestData")

    /**
     * [data.NullableAnnotationCheckNotNullTestData]
     */
    @Test
    open fun nullableAnnotationCheckNotNullTestData() = runGeneratedTest("nullableAnnotationCheckNotNullTestData")

    /**
     * [data.NullableAnnotationCheckNotNullFieldShiftTestData]
     */
    @Test
    open fun nullableAnnotationCheckNotNullFieldShiftTestData() = runGeneratedTest("nullableAnnotationCheckNotNullFieldShiftTestData")

    /**
     * [data.ConstTestData]
     */
    @Test
    open fun constTestData() = runGeneratedTest("constTestData")

    /**
     * [data.FinalRemovalTestData]
     */
    @Test
    open fun finalRemovalTestData() = runGeneratedTest("finalRemovalTestData")

    /**
     * [data.LombokDirtyOffTestData]
     */
    @Test
    open fun lombokDirtyOffTestData() = runGeneratedTest("lombokDirtyOffTestData")

    /**
     * [data.ExpressionFuncTestData]
     */
    @Test
    open fun expressionFuncTestData() = runGeneratedTest("expressionFuncTestData")

    /**
     * [data.InterfaceExtensionPropertiesTestData]
     */
    @Test
    open fun interfaceExtensionPropertiesTestData() = runGeneratedTest("interfaceExtensionPropertiesTestData")

    /**
     * [data.PatternMatchingInstanceofTestData]
     */
    @Test
    open fun patternMatchingInstanceofTestData() = runGeneratedTest("patternMatchingInstanceofTestData")

    /**
     * [data.SummaryParentOverrideTestData]
     */
    @Test
    open fun summaryParentOverrideTestData() = runGeneratedTest("summaryParentOverrideTestData")

    /**
     * [data.ConstructorReferenceNotationTestData]
     */
    @Test
    open fun constructorReferenceNotationTestData() = runGeneratedTest("constructorReferenceNotationTestData")

    /**
     * [data.ConstructorReferenceNotationWithConstTestData]
     */
    @Test
    open fun constructorReferenceNotationWithConstTestData() = runGeneratedTest("constructorReferenceNotationWithConstTestData")

    /**
     * [data.MethodDefaultParametersTestData]
     */
    @Test
    open fun methodDefaultParametersTestData() = runGeneratedTest("methodDefaultParametersTestData")

    /**
     * [data.OverrideHideTestData]
     */
    @Test
    open fun overrideHideTestData() = runGeneratedTest("overrideHideTestData")

    /**
     * [data.SuppressWarningsHideTestData]
     */
    @Test
    open fun suppressWarningsHideTestData() = runGeneratedTest("suppressWarningsHideTestData")

    /**
     * [data.ExperimentalTestData]
     */
    @Test
    open fun experimentalTestData() = runGeneratedTest("experimentalTestData")

    /**
     * [data.FinalEmojiTestData]
     */
    @Test
    open fun finalEmojiTestData() {
        @Suppress("DEPRECATION")
        doFoldingTest("finalEmojiTestData", state::finalEmoji)
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
                    """.trimIndent(),
                )
            }
        }
        doFoldingTest(
            "dynamicTestData",
            state::dynamic,
            state::getSetExpressionsCollapse,
            dynamic = dynamicProvider,
        )
    }

    /**
     * [data.ArithmeticExpressionsTestData]
     */
    @Test
    open fun arithmeticExpressionsTestData() {
        @Suppress("DEPRECATION")
        doFoldingTest("arithmeticExpressionsTestData", state::arithmeticExpressions)
    }

    /**
     * [data.EmojifyTestData]
     */
    @Test
    open fun emojifyTestData() {
        @Suppress("DEPRECATION")
        doFoldingTest("emojifyTestData", state::emojify)
    }

    /**
     * [data.LombokPatternOffTestData]
     */
    @Test
    open fun lombokPatternOffTestData() {
        state.lombokPatternOff = "LombokPa[t]{2}ernOffTestData"
        try {
            doFoldingTest("lombokPatternOffTestData", state::lombok)
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
            doFoldingTest("lombokPatternOffNegativeTestData", state::lombok)
        } finally {
            state.lombokPatternOff = null
        }
    }
}
