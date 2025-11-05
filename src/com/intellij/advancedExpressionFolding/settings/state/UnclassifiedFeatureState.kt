package com.intellij.advancedExpressionFolding.settings.state

interface IUnclassifiedFeatureState {
    var semicolonsCollapse: Boolean
    // NEW OPTION VAR
}

data class UnclassifiedFeatureState(
    override var semicolonsCollapse: Boolean = false,
    // NEW OPTION VAR
) : IUnclassifiedFeatureState
