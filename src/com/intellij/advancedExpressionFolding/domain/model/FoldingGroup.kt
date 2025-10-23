package com.intellij.advancedExpressionFolding.domain.model

class FoldingGroup(private val regions: List<FoldingRegionPort>) {
    val managedRegions: List<FoldingRegionPort>
        get() = regions.filter(FoldingRegionPort::isManagedByPlugin)
}
