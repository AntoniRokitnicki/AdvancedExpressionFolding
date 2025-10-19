package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

class RuleThrottleTest : BaseTest() {
    private val settingsService: AdvancedExpressionFoldingSettings
        get() = getInstance()
    private lateinit var originalState: AdvancedExpressionFoldingSettings.State
    private val flag = "emojify"

    @BeforeEach
    fun captureState() {
        originalState = settingsService.state.copy()
    }

    @AfterEach
    fun restoreState() {
        FoldingRuleExecutionGuard.resetAll()
        settingsService.loadState(originalState.copy())
    }

    @Test
    fun throttlesAndResumesSlowRule() {
        val settings = settingsService.state
        settingsService.disableAll()
        settings.globalOn = true
        settings.emojify = true

        val previousThreshold = settings.throttleThresholdMillis
        settings.throttleThresholdMillis = 1

        fixture.configureByFile("EmojifyTestData.java")
        val element = fixture.file

        repeat(6) {
            FoldingRuleExecutionGuard.record(element, setOf(flag), TimeUnit.MILLISECONDS.toNanos(5), settings)
        }
        assertTrue(settings.isAutoDisabled(flag))
        assertTrue(FoldingRuleExecutionGuard.shouldSkip(setOf(flag), settings))

        repeat(20) {
            FoldingRuleExecutionGuard.record(element, setOf(flag), TimeUnit.MICROSECONDS.toNanos(100), settings)
        }

        assertFalse(settings.isAutoDisabled(flag))
        assertFalse(FoldingRuleExecutionGuard.shouldSkip(setOf(flag), settings))
        settings.throttleThresholdMillis = previousThreshold
    }
}
