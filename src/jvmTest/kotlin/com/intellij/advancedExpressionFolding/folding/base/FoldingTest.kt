package com.intellij.advancedExpressionFolding.folding.base

import com.intellij.advancedExpressionFolding.folding.base.folding.CollectionsStreamsFoldingTest
import com.intellij.advancedExpressionFolding.folding.base.folding.ControlFlowFoldingTest
import com.intellij.advancedExpressionFolding.folding.base.folding.DateOperationsFoldingTest
import com.intellij.advancedExpressionFolding.folding.base.folding.EmojiVisibilityFoldingTest
import com.intellij.advancedExpressionFolding.folding.base.folding.ExpressionCollapseFoldingTest
import com.intellij.advancedExpressionFolding.folding.base.folding.FoldingFeatureTestCase
import com.intellij.advancedExpressionFolding.folding.base.folding.GlobalSettingsFoldingTest
import com.intellij.advancedExpressionFolding.folding.base.folding.HidingSuppressionFoldingTest
import com.intellij.advancedExpressionFolding.folding.base.folding.KotlinLanguageFoldingTest
import com.intellij.advancedExpressionFolding.folding.base.folding.LogFoldingTest
import com.intellij.advancedExpressionFolding.folding.base.folding.LombokFoldingTest
import com.intellij.advancedExpressionFolding.folding.base.folding.UnclassifiedFeatureFoldingTest
import org.junitpioneer.jupiter.Stopwatch

@Stopwatch
open class FoldingTest : FoldingFeatureTestCase(),
    ExpressionCollapseFoldingTest,
    ControlFlowFoldingTest,
    DateOperationsFoldingTest,
    CollectionsStreamsFoldingTest,
    LombokFoldingTest,
    KotlinLanguageFoldingTest,
    LogFoldingTest,
    EmojiVisibilityFoldingTest,
    HidingSuppressionFoldingTest,
    GlobalSettingsFoldingTest,
    UnclassifiedFeatureFoldingTest

