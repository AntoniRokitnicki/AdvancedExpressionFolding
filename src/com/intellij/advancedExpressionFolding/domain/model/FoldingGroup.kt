package com.intellij.advancedExpressionFolding.domain.model

class FoldingGroup(private val regions: List<AdvancedFoldRegion>) {
    fun apply(state: FoldingState) {
        regions.forEach { region ->
            region.apply(state)
        }
    }

    companion object {
        fun fromRegions(regions: List<AdvancedFoldRegion>): FoldingGroup? =
            regions.takeIf { it.isNotEmpty() }?.let(::FoldingGroup)
    }
}
