package com.intellij.advancedExpressionFolding.discovery

import org.apache.commons.math3.ml.clustering.Clusterable

internal data class IndexedPoint(
    val index: Int,
    private val coordinates: DoubleArray
) : Clusterable {
    override fun getPoint(): DoubleArray = coordinates
}
