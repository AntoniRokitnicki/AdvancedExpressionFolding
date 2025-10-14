package com.intellij.advancedExpressionFolding

import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable

@EnabledIfEnvironmentVariable(named = "run-mode", matches = "never")
object PerformanceResult : FoldingTest() {

    @Suppress("unused")
    val result = performance { // sum=370
        "appendSetterInterpolatedStringTestData" to 4
        "arithmeticExpressionsTestData" to 14
        "assertTestData" to 6
        "compactControlFlowTestData" to 5
        "concatenationTestData" to 7
        "constTestData" to 6
        "constructorReferenceNotationTestData" to 6
        "constructorReferenceNotationWithConstTestData" to 6
        "controlFlowMultiStatementTestData" to 5
        "controlFlowSingleStatementTestData" to 5
        "destructuringAssignmentArrayTestData" to 5
        "destructuringAssignmentArrayWithoutValTestData" to 5
        "destructuringAssignmentListTestData" to 5
        "destructuringAssignmentListWithoutValTestData" to 4
        "dynamicTestData" to 4
        "elvisTestData" to 4
        "emojifyTestData" to 13
        "equalsCompareTestData" to 4
        "experimentalTestData" to 5
        "expressionFuncTestData" to 6
        "fieldShiftBuilder" to 6
        "fieldShiftFields" to 6
        "fieldShiftSetters" to 6
        "finalEmojiTestData" to 4
        "finalRemovalTestData" to 4
        "forRangeTestData" to 5
        "getSetPutTestData" to 5
        "getterSetterTestData" to 4
        "ifNullSafeData" to 6
        "interfaceExtensionPropertiesTestData" to 8
        "interpolatedStringTestData" to 4
        "letReturnIt" to 5
        "localDateLiteralPostfixTestData" to 4
        "localDateLiteralTestData" to 4
        "localDateTestData" to 7
        "logBrackets" to 6
        "lombokDirtyOffTestData" to 6
        "lombokPatternOffNegativeTestData" to 23
        "lombokPatternOffTestData" to 19
        "lombokTestData" to 24
        "lombokUsageTestData" to 4
        "methodDefaultParametersTestData" to 7
        "nullableAnnotationCheckNotNullFieldShiftTestData" to 5
        "nullableAnnotationCheckNotNullTestData" to 7
        "nullableAnnotationTestData" to 7
        "optionalTestData" to 6
        "overrideHideTestData" to 6
        "patternMatchingInstanceofTestData" to 6
        "printlnTestData" to 4
        "semicolonTestData" to 4
        "sliceTestData" to 5
        "spreadTestData" to 6
        "stringBuilderTestData" to 5
        "summaryParentOverrideTestData" to 5
        "suppressWarningsHideTestData" to 4
        "typeCastTestData" to 4
        "varTestData" to 4

    }

    class PerformanceDSL(private val owner: PerformanceResult) {
        infix fun String.to(@Suppress("UNUSED_PARAMETER") expectedValue: Int) {
            owner.ensureGeneratedTest(this)
        }
    }

    private fun performance(block: PerformanceDSL.() -> Unit) {
        PerformanceDSL(this).block()
    }

}
