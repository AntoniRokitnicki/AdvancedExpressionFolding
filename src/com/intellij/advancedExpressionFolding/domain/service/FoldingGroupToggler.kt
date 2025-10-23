package com.intellij.advancedExpressionFolding.domain.service

import com.intellij.advancedExpressionFolding.domain.model.FoldingGroup
import com.intellij.advancedExpressionFolding.domain.model.FoldingState

class FoldingGroupToggler {
    fun toggle(groups: List<FoldingGroup>, state: FoldingState) {
        groups.forEach { group ->
            group.apply(state)
        }
    }
}
