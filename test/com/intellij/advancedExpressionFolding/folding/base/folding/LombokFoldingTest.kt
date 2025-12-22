package com.intellij.advancedExpressionFolding.folding.base.folding

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface LombokFoldingTest : FoldingTestSection {
    @Test
    fun lombokTestData() = testCase.runFoldingTest(foldingState()::lombok)

    @Test
    fun lombokUsageTestData() = testCase.runFoldingTest(foldingState()::lombok)

    @Test
    fun lombokWithTestData() = testCase.runFoldingTest(foldingState()::lombok)

    @Test
    fun lombokDirtyOffTestData() = testCase.runFoldingTest(
        foldingState()::lombok,
        foldingState()::lombokDirtyOff,
    )

    @Test
    fun lombokOptionalDirtyTestData() = testCase.runFoldingTest(foldingState()::lombok)

    @Test
    fun lombokOptionalDirtyNoReferenceTestData() = testCase.runFoldingTest(foldingState()::lombok)

    @Test
    fun interfaceExtensionPropertiesTestData() = testCase.runFoldingTest(
        foldingState()::interfaceExtensionProperties,
        foldingState()::lombok,
        foldingState()::nullable,
    )

    @Test
    fun lombokPatternOffTestData() {
        val state = foldingState()
        state.lombokPatternOff = "LombokPa[t]{2}ernOffTestData"
        try {
            testCase.runFoldingTest(state::lombok)
        } finally {
            state.lombokPatternOff = null
        }
    }

    @Test
    fun lombokPatternOffNegativeTestData() {
        val state = foldingState()
        state.lombokPatternOff = "LombokPatternOffTestData"
        try {
            testCase.runFoldingTest(state::lombok)
        } finally {
            state.lombokPatternOff = null
        }
    }
}

@Disabled("Split from FoldingTest")
open class LombokFoldingTestCase : FoldingFeatureTestCase(), LombokFoldingTest
