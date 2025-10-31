package com.intellij.advancedExpressionFolding.settings

interface IUnclassifiedFeatureState {
    val semicolonsCollapse: Boolean
    // NEW OPTION VAR
}

data class UnclassifiedFeatureState(
    override val semicolonsCollapse: Boolean = false,
    // NEW OPTION VAR
) : IUnclassifiedFeatureState
