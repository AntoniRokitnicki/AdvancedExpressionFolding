package com.intellij.advancedExpressionFolding.infrastructure.editor

import com.intellij.advancedExpressionFolding.application.port.output.FoldingGroupRepository
import com.intellij.advancedExpressionFolding.domain.model.AdvancedFoldRegion
import com.intellij.advancedExpressionFolding.domain.model.FoldingGroup
import com.intellij.advancedExpressionFolding.isAdvancedExpressionFoldingGroup
import com.intellij.openapi.editor.Editor

class IntellijFoldingGroupRepository : FoldingGroupRepository {
    override fun load(editor: Editor): List<FoldingGroup> {
        if (editor.isDisposed) return emptyList()

        val regions = editor.foldingModel.allFoldRegions
            .asSequence()
            .filter { region -> region.isAdvancedExpressionFoldingGroup }
            .map(::AdvancedFoldRegion)
            .toList()

        return FoldingGroup.fromRegions(regions)?.let(::listOf) ?: emptyList()
    }
}
