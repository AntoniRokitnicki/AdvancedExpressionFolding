package com.intellij.advancedExpressionFolding

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import kotlin.reflect.KFunction0

@EnabledIfEnvironmentVariable(named = "run-mode", matches = "never")
object PerformanceResult : FoldingTest() {

    @Suppress("unused")
    val result = performance { // sum=336
        ::appendSetterInterpolatedStringTestData to 4
        ::arithmeticExpressionsTestData to 10
        ::assertTestData to 4
        ::compactControlFlowTestData to 4
        ::concatenationTestData to 6
        ::constTestData to 6
        ::constructorReferenceNotationTestData to 5
        ::constructorReferenceNotationWithConstTestData to 7
        ::controlFlowMultiStatementTestData to 5
        ::controlFlowSingleStatementTestData to 4
        ::destructuringAssignmentArrayTestData to 6
        ::destructuringAssignmentArrayWithoutValTestData to 4
        ::destructuringAssignmentListTestData to 5
        ::destructuringAssignmentListWithoutValTestData to 4
        ::dynamicTestData to 5
        ::elvisTestData to 5
        ::emojifyTestData to 16
        ::equalsCompareTestData to 4
        ::experimentalTestData to 4
        ::expressionFuncTestData to 4
        ::fieldShiftBuilder to 5
        ::fieldShiftFields to 5
        ::fieldShiftSetters to 6
        ::finalEmojiTestData to 4
        ::finalRemovalTestData to 6
        ::forRangeTestData to 6
        ::getSetPutTestData to 6
        ::getterSetterTestData to 4
        ::ifNullSafeData to 6
        ::interfaceExtensionPropertiesTestData to 7
        ::interpolatedStringTestData to 4
        ::letReturnIt to 5
        ::localDateLiteralPostfixTestData to 4
        ::localDateLiteralTestData to 4
        ::localDateTestData to 7
        ::logBrackets to 7
        ::lombokDirtyOffTestData to 5
        ::lombokPatternOffNegativeTestData to 19
        ::lombokPatternOffTestData to 16
        ::lombokTestData to 21
        ::lombokUsageTestData to 4
        ::methodDefaultParametersTestData to 6
        ::nullableAnnotationCheckNotNullTestData to 6
        ::nullableAnnotationTestData to 6
        ::optionalTestData to 7
        ::patternMatchingInstanceofTestData to 5
        ::printlnTestData to 4
        ::semicolonTestData to 5
        ::sliceTestData to 4
        ::spreadTestData to 7
        ::stringBuilderTestData to 4
        ::summaryParentOverrideTestData to 4
        ::typeCastTestData to 4
        ::varTestData to 4

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