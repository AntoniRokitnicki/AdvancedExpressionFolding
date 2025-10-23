package com.intellij.advancedExpressionFolding.domain.model

enum class FoldingState(val isCollapsed: Boolean) {
    COLLAPSED(true),
    EXPANDED(false);

    val isExpanded: Boolean
        get() = !isCollapsed

    companion object {
        fun fromCollapseFlag(collapse: Boolean): FoldingState = if (collapse) COLLAPSED else EXPANDED
    }
}
