package com.intellij.advancedExpressionFolding

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import kotlin.reflect.KFunction0

@EnabledIfEnvironmentVariable(named = "run-mode", matches = "never")
object PerformanceResult : FoldingTest() {

    @Suppress("unused")
    val result = performance { // sum=336
        ::testAppendSetterInterpolatedStringTestData to 4
        ::testArithmeticExpressionsTestData to 10
        ::testAssertTestData to 4
        ::testCompactControlFlowTestData to 4
        ::testConcatenationTestData to 6
        ::testConstTestData to 6
        ::testConstructorReferenceNotationTestData to 5
        ::testConstructorReferenceNotationWithConstTestData to 7
        ::testControlFlowMultiStatementTestData to 5
        ::testControlFlowSingleStatementTestData to 4
        ::testDestructuringAssignmentArrayTestData to 6
        ::testDestructuringAssignmentArrayWithoutValTestData to 4
        ::testDestructuringAssignmentListTestData to 5
        ::testDestructuringAssignmentListWithoutValTestData to 4
        ::testDynamicTestData to 5
        ::testElvisTestData to 5
        ::testEmojifyTestData to 16
        ::testEqualsCompareTestData to 4
        ::testExperimentalTestData to 4
        ::testExpressionFuncTestData to 4
        ::testFieldShiftBuilder to 5
        ::testFieldShiftFields to 5
        ::testFieldShiftSetters to 6
        ::testFinalEmojiTestData to 4
        ::testFinalRemovalTestData to 6
        ::testForRangeTestData to 6
        ::testGetSetPutTestData to 6
        ::testGetterSetterTestData to 4
        ::testIfNullSafeData to 6
        ::testInterfaceExtensionPropertiesTestData to 7
        ::testInterpolatedStringTestData to 4
        ::testLetReturnIt to 5
        ::testLocalDateLiteralPostfixTestData to 4
        ::testLocalDateLiteralTestData to 4
        ::testLocalDateTestData to 7
        ::testLogBrackets to 7
        ::testLombokDirtyOffTestData to 5
        ::testLombokPatternOffNegativeTestData to 19
        ::testLombokPatternOffTestData to 16
        ::testLombokTestData to 21
        ::testLombokUsageTestData to 4
        ::testMethodDefaultParametersTestData to 6
        ::testNullableAnnotationCheckNotNullTestData to 6
        ::testNullableAnnotationTestData to 6
        ::testOptionalTestData to 7
        ::testPatternMatchingInstanceofTestData to 5
        ::testPrintlnTestData to 4
        ::testSemicolonTestData to 5
        ::testSliceTestData to 4
        ::testSpreadTestData to 7
        ::testStringBuilderTestData to 4
        ::testSummaryParentOverrideTestData to 4
        ::testTypeCastTestData to 4
        ::testVarTestData to 4

    }

    class PerformanceDSL {
        infix fun (KFunction0<Unit>).to(@Suppress("UNUSED_PARAMETER") expectedValue: Int) {
            // ignore
        }
    }

    private fun performance(block: PerformanceDSL.() -> Unit) {
        val dsl = PerformanceDSL()
        dsl.block()
    }

}