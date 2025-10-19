package com.intellij.advancedExpressionFolding.settings

import com.intellij.advancedExpressionFolding.metrics.FoldingMetrics
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class FoldingMetricsSettingsTest {

    @Test
    fun `stores and retrieves metrics by file url`() {
        val settings = FoldingMetricsSettings()
        val url = "file://sample.java"
        val metrics = FoldingMetrics(10, 5, 120)

        settings.updateMetrics(url, metrics)

        assertEquals(metrics, settings.getMetrics(url))
    }

    @Test
    fun `evicts oldest entries when capacity exceeded`() {
        val settings = FoldingMetricsSettings()

        (0 until 60).forEach { index ->
            settings.updateMetrics("file://$index", FoldingMetrics(index, index + 1, index + 2))
        }

        assertNull(settings.getMetrics("file://0"))
        assertEquals(FoldingMetrics(59, 60, 61), settings.getMetrics("file://59"))
    }
}
