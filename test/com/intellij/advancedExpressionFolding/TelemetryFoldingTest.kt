package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.TestDynamicDataProvider
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.reflect.KMutableProperty0

class TelemetryFoldingTest : BaseTest() {
    private val settings: AdvancedExpressionFoldingSettings
        get() = AdvancedExpressionFoldingSettings.getInstance()
    private val state
        get() = settings.state
    private var telemetryEnabledBefore: Boolean = false

    @BeforeEach
    fun setUpTelemetry() {
        telemetryEnabledBefore = settings.state.telemetryEnabled
        settings.clearTelemetry()
    }

    @AfterEach
    fun tearDownTelemetry() {
        settings.state.telemetryEnabled = telemetryEnabledBefore
        settings.clearTelemetry()
    }

    @Test
    fun telemetryOptOutDoesNotCollectData() {
        settings.state.telemetryEnabled = false
        runFolding(
            state::concatenationExpressionsCollapse,
            state::optional,
            state::streamSpread,
        )
        val snapshot = settings.telemetrySnapshot()
        assertEquals(0L, snapshot.totalFoldRegions)
        assertTrue(snapshot.perRule.isEmpty())
    }

    @Test
    fun telemetryOptInCollectsData() {
        settings.state.telemetryEnabled = true
        runFolding(
            state::concatenationExpressionsCollapse,
            state::optional,
            state::streamSpread,
        )
        val snapshot = settings.telemetrySnapshot()
        assertTrue(snapshot.totalFoldRegions > 0)
        assertTrue(snapshot.perRule.values.any { it > 0 })
    }

    @Test
    fun telemetryClearResetsCounters() {
        settings.state.telemetryEnabled = true
        runFolding(
            state::concatenationExpressionsCollapse,
            state::optional,
            state::streamSpread,
        )
        assertTrue(settings.telemetrySnapshot().totalFoldRegions > 0)

        settings.clearTelemetry()
        val cleared = settings.telemetrySnapshot()
        assertEquals(0L, cleared.totalFoldRegions)
        assertTrue(cleared.perRule.isEmpty())
    }

    private fun runFolding(vararg properties: KMutableProperty0<Boolean>) {
        settings.disableAll()
        properties.forEach { it.set(true) }
        MethodCallFactory.initialize(TestDynamicDataProvider())
        super.doFoldingTest("ConcatenationTestData")
    }
}
