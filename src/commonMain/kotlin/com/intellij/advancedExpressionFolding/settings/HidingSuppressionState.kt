package com.intellij.advancedExpressionFolding.settings

interface IHidingSuppressionState {
    val overrideHide: Boolean
    val suppressWarningsHide: Boolean
    val summaryParentOverride: Boolean
}

data class HidingSuppressionState(
    override val overrideHide: Boolean = true,
    override val suppressWarningsHide: Boolean = true,
    override val summaryParentOverride: Boolean = false,
) : IHidingSuppressionState
