package com.intellij.advancedExpressionFolding.settings

interface IUnclassifiedFeatureState {
    val semicolonsCollapse: Boolean
    val stringEscapesVisualizeNewlines: Boolean
}

data class UnclassifiedFeatureState(
    override val semicolonsCollapse: Boolean = false,
    override val stringEscapesVisualizeNewlines: Boolean = true,
) : IUnclassifiedFeatureState
