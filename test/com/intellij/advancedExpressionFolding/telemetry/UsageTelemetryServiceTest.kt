package com.intellij.advancedExpressionFolding.telemetry

import com.intellij.advancedExpressionFolding.expression.Expression
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

private class DummyExpression : Expression()

class UsageTelemetryServiceTest {

    @Test
    fun `record usage increments count and updates timestamp`() {
        val fixedClock = Clock.fixed(Instant.parse("2024-01-01T12:00:00Z"), ZoneOffset.UTC)
        val service = UsageTelemetryService(fixedClock)

        service.recordUsage(DummyExpression::class.java)

        val snapshot = service.getMetricsSnapshot()
        val metric = snapshot[DummyExpression::class.java.name]
        assertNotNull(metric)
        assertEquals(1, metric!!.count)
        assertEquals(fixedClock.millis(), metric.lastUsedEpochMs)
    }

    @Test
    fun `recording twice accumulates counts`() {
        val fixedClock = Clock.fixed(Instant.parse("2024-01-01T12:00:00Z"), ZoneOffset.UTC)
        val service = UsageTelemetryService(fixedClock)

        service.recordUsage(DummyExpression::class.java)
        service.recordUsage(DummyExpression::class.java)

        val metric = service.getMetricsSnapshot()[DummyExpression::class.java.name]
        assertEquals(2, metric!!.count)
    }

    @Test
    fun `reset clears recorded metrics`() {
        val fixedClock = Clock.fixed(Instant.parse("2024-01-01T12:00:00Z"), ZoneOffset.UTC)
        val service = UsageTelemetryService(fixedClock)

        service.recordUsage(DummyExpression::class.java)
        service.reset()

        assertTrue(service.getMetricsSnapshot().isEmpty())
    }

    @Test
    fun `snapshot returns defensive copies`() {
        val fixedClock = Clock.fixed(Instant.parse("2024-01-01T12:00:00Z"), ZoneOffset.UTC)
        val service = UsageTelemetryService(fixedClock)
        service.recordUsage(DummyExpression::class.java)

        val snapshot = service.getMetricsSnapshot().toMutableMap()
        snapshot[DummyExpression::class.java.name]?.count = 42

        val freshSnapshot = service.getMetricsSnapshot()
        assertEquals(1, freshSnapshot[DummyExpression::class.java.name]?.count)
    }
}
