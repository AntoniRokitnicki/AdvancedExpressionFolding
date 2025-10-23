package com.intellij.advancedExpressionFolding.domain.service

import com.intellij.advancedExpressionFolding.domain.model.FoldingGroup
import com.intellij.advancedExpressionFolding.domain.model.FoldingToggleState

class FoldingDomainService {
    fun applyState(group: FoldingGroup, state: FoldingToggleState) {
        group.managedRegions.forEach { region ->
            if (state.shouldCollapse) {
                region.collapse()
            } else {
                region.expand()
            }
        }
    }
}
