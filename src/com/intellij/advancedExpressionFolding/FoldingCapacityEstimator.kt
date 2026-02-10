package com.intellij.advancedExpressionFolding

internal object FoldingCapacityEstimator {
    private const val REGIONS_PER_KILOBYTE = 6
    private const val MIN_INITIAL_CAPACITY = 256

    fun estimateInitialCapacity(textLength: Int): Int {
        val safeLength = textLength.coerceAtLeast(0)
        val kilobytes = maxOf(1, (safeLength + 1023) / 1024)
        val estimated = kilobytes * REGIONS_PER_KILOBYTE
        return estimated.coerceAtLeast(MIN_INITIAL_CAPACITY)
    }
}
