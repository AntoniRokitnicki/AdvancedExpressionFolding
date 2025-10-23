package com.intellij.advancedExpressionFolding.domain.model

enum class FoldingToggleState {
    COLLAPSED,
    EXPANDED;

    val shouldCollapse: Boolean
        get() = this == COLLAPSED

    companion object {
        fun from(enabled: Boolean): FoldingToggleState = if (enabled) COLLAPSED else EXPANDED
    }
}
