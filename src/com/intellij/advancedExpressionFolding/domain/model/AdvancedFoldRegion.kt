package com.intellij.advancedExpressionFolding.domain.model

import com.intellij.openapi.editor.FoldRegion

class AdvancedFoldRegion(private val delegate: FoldRegion) {
    fun apply(state: FoldingState) {
        delegate.isExpanded = state.isExpanded
    }
}
