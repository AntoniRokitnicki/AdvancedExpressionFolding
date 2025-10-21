package com.intellij.advancedExpressionFolding.folding.base.folding

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface KotlinLanguageFoldingTest : FoldingTestSection {
    @Test
    fun letReturnIt() = testCase.runFoldingTest(
        foldingState()::varExpressionsCollapse,
        foldingState()::kotlinQuickReturn,
    )

    @Test
    fun ifNullSafeData() = testCase.runFoldingTest(
        foldingState()::checkExpressionsCollapse,
        foldingState()::getSetExpressionsCollapse,
        foldingState()::ifNullSafe,
    )

    @Test
    fun destructuringAssignmentArrayTestData() = testCase.runFoldingTest(
        foldingState()::destructuring,
        foldingState()::getSetExpressionsCollapse,
        foldingState()::varExpressionsCollapse,
    )

    @Test
    fun destructuringAssignmentArrayWithoutValTestData() = testCase.runFoldingTest(
        foldingState()::destructuring,
        foldingState()::getSetExpressionsCollapse,
    )

    @Test
    fun destructuringAssignmentListTestData() = testCase.runFoldingTest(
        foldingState()::destructuring,
        foldingState()::getSetExpressionsCollapse,
        foldingState()::varExpressionsCollapse,
    )

    @Test
    fun destructuringAssignmentListWithoutValTestData() = testCase.runFoldingTest(
        foldingState()::destructuring,
        foldingState()::getSetExpressionsCollapse,
    )

    @Test
    fun printlnTestData() = testCase.runFoldingTest(foldingState()::println)

    @Test
    fun nullableAnnotationTestData() = testCase.runFoldingTest(
        foldingState()::nullable,
        foldingState()::lombok,
    )

    @Test
    fun nullableAnnotationCheckNotNullTestData() = testCase.runFoldingTest(
        foldingState()::nullable,
        foldingState()::getSetExpressionsCollapse,
    )

    @Test
    fun nullableAnnotationCheckNotNullFieldShiftTestData() = testCase.runFoldingTest(
        foldingState()::nullable,
        foldingState()::getSetExpressionsCollapse,
        foldingState()::fieldShift,
    )

    @Test
    fun constTestData() = testCase.runFoldingTest(foldingState()::const)

    @Test
    fun expressionFuncTestData() = testCase.runFoldingTest(foldingState()::expressionFunc)

    @Test
    fun patternMatchingInstanceofTestData() = testCase.runFoldingTest(foldingState()::patternMatchingInstanceof)

    @Test
    fun constructorReferenceNotationTestData() = testCase.runFoldingTest(foldingState()::constructorReferenceNotation)

    @Test
    fun constructorReferenceNotationWithConstTestData() = testCase.runFoldingTest(
        foldingState()::constructorReferenceNotation,
        foldingState()::const,
    )

    @Test
    fun methodDefaultParametersTestData() = testCase.runFoldingTest(foldingState()::methodDefaultParameters)
}

@Disabled("Split from FoldingTest")
open class KotlinLanguageFoldingTestCase : FoldingFeatureTestCase(), KotlinLanguageFoldingTest
