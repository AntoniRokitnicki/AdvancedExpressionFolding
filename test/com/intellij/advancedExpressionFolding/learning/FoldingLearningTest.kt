package com.intellij.advancedExpressionFolding.learning

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FoldingLearningTest {

    @Test
    fun `telemetry event round trip`() {
        val event = FoldingTelemetryService.TelemetryEvent(
            ts = 123456789L,
            projectIdHash = 42,
            fileExt = "java",
            languageId = "JAVA",
            filePathHash = 99,
            foldType = "imports",
            regionStart = 10,
            regionEnd = 50,
            regionLength = 40,
            nestingDepth = 2,
            action = "collapse",
            timeSincePrevMs = 500L,
            caretOffset = 15,
            visibleLineCount = 30,
        )
        val parsed = FoldingTelemetryService.TelemetryEvent.fromCsv(event.toCsv())
        assertEquals(event, parsed)
    }

    @Test
    fun `dataset builder detects wants off`() {
        val baseTs = 1_000_000L
        val events = listOf(
            testEvent(baseTs, action = "open"),
            testEvent(baseTs + 1_000, action = "collapse"),
            testEvent(baseTs + 1_500, action = "expand"),
            testEvent(baseTs + 1_500 + 30_000, action = "expand"),
            testEvent(baseTs + 1_500 + 60_000, action = "expand"),
        )
        val dataset = FoldingRnnTrainer.buildDatasetForTesting(events)
        val positive = dataset.train.firstOrNull { it.label == 1.0 }
        assertNotNull(positive)
        assertEquals("imports", positive!!.foldType)
    }

    @Test
    fun `trainer produces a model`() {
        val baseTs = 2_000_000L
        val events = buildList {
            add(testEvent(baseTs, action = "open"))
            repeat(10) { index ->
                val t = baseTs + (index + 1) * 2_000
                add(testEvent(t, action = if (index % 2 == 0) "collapse" else "expand", delta = 2_000L))
            }
        }
        val dataset = FoldingRnnTrainer.buildDatasetForTesting(events)
        val model = FoldingRnnTrainer.trainForTesting(dataset)
        assertNotNull(model)
        val probability = model!!.predict(DoubleArray(8) { 0.2 }, "java", "imports")
        assertTrue(probability in 0.0..1.0)
    }

    private fun testEvent(ts: Long, action: String, delta: Long = 0L): FoldingTelemetryService.TelemetryEvent {
        return FoldingTelemetryService.TelemetryEvent(
            ts = ts,
            projectIdHash = 7,
            fileExt = "java",
            languageId = "JAVA",
            filePathHash = 11,
            foldType = "imports",
            regionStart = 0,
            regionEnd = 10,
            regionLength = 10,
            nestingDepth = 1,
            action = action,
            timeSincePrevMs = delta,
            caretOffset = 0,
            visibleLineCount = 20,
        )
    }
}
