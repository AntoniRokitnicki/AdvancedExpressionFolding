package com.intellij.advancedExpressionFolding.settings.state

interface IHidingSuppressionState {
    var overrideHide: Boolean
    var suppressWarningsHide: Boolean
    var summaryParentOverride: Boolean
}

data class HidingSuppressionState(
    override var overrideHide: Boolean = true,
    override var suppressWarningsHide: Boolean = true,
    override var summaryParentOverride: Boolean = false,
) : IHidingSuppressionState
