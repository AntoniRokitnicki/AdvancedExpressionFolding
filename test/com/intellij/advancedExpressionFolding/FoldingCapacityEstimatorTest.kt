package com.intellij.advancedExpressionFolding

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FoldingCapacityEstimatorTest {

    @Test
    fun `tiny document uses minimum capacity`() {
        val capacity = FoldingCapacityEstimator.estimateInitialCapacity(42)

        assertEquals(256, capacity)
    }

    @Test
    fun `negative length coerces to minimum`() {
        val capacity = FoldingCapacityEstimator.estimateInitialCapacity(-1)

        assertEquals(256, capacity)
    }

    @Test
    fun `kilobyte rounding retains minimum capacity`() {
        val capacity = FoldingCapacityEstimator.estimateInitialCapacity(1024)

        assertEquals(256, capacity)
    }

    @Test
    fun `large document computes expected capacity`() {
        val fiftyKilobytes = 50 * 1024

        val capacity = FoldingCapacityEstimator.estimateInitialCapacity(fiftyKilobytes)

        assertEquals(50 * 6, capacity)
    }
}
