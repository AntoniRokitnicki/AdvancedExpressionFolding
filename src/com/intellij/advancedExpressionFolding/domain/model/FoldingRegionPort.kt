package com.intellij.advancedExpressionFolding.domain.model

interface FoldingRegionPort {
    val isManagedByPlugin: Boolean
    fun collapse()
    fun expand()
}
