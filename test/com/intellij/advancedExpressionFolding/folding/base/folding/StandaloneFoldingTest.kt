package com.intellij.advancedExpressionFolding.folding.base.folding

import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.DynamicMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.dynamic.IDynamicDataProvider
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface StandaloneFoldingTest : FoldingTestSection {
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

    @Test
    fun dynamicTestData() {
        val state = foldingState()
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
        testCase.runFoldingTest(state::dynamic, state::getSetExpressionsCollapse, dynamic = dynamicProvider)
    }

    @Test
    @Suppress("DEPRECATION")
    fun arithmeticExpressionsTestData() = testCase.runFoldingTest(foldingState()::arithmeticExpressions)

    @Test
    fun experimentalTestData() = testCase.runFoldingTest(
        foldingState()::experimental,
        foldingState()::nullable,
        foldingState()::const,
        foldingState()::lombok,
        foldingState()::getExpressionsCollapse,
    )

    @Test
    fun semicolonTestData() = testCase.runReadOnlyFoldingTest(foldingState()::semicolonsCollapse)
}

@Disabled("Split from FoldingTest")
open class StandaloneFoldingTestCase : FoldingFeatureTestCase(), StandaloneFoldingTest
