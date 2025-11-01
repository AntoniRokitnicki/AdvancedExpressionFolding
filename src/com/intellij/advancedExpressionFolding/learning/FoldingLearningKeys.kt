package com.intellij.advancedExpressionFolding.learning

import com.intellij.openapi.util.Key

object FoldingLearningKeys {
    val FOLD_TYPE: Key<String> = Key.create("AEF.FoldingLearning.FoldType")
    val DEFAULT_EXPANDED: Key<Boolean> = Key.create("AEF.FoldingLearning.DefaultExpanded")
}
