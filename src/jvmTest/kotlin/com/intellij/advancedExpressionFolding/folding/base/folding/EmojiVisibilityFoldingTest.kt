package com.intellij.advancedExpressionFolding.folding.base.folding

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface EmojiVisibilityFoldingTest : FoldingTestSection {
    @Test
    fun finalRemovalTestData() = testCase.runFoldingTest(foldingState()::finalRemoval)

    @Test
    @Suppress("DEPRECATION")
    fun finalEmojiTestData() = testCase.runFoldingTest(foldingState()::finalEmoji)

    @Test
    @Suppress("DEPRECATION")
    fun emojifyTestData() = testCase.runFoldingTest(foldingState()::emojify)
}

@Disabled("Split from FoldingTest")
open class EmojiVisibilityFoldingTestCase : FoldingFeatureTestCase(), EmojiVisibilityFoldingTest
