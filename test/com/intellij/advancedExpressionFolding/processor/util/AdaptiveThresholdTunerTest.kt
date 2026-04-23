package com.intellij.advancedExpressionFolding.processor.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

private const val EPSILON = 1e-6

class AdaptiveThresholdTunerTest {
    @Test
    fun increasesThresholdWhenHighPressureKeepsPayingOff() {
        val tuner = AdaptiveThresholdTuner(
            minThreshold = 5.0,
            maxThreshold = 20.0,
            initialThreshold = 10.0,
            step = 1.0,
            explorationRate = 0.0,
        )

        val firstDecision = tuner.decide(pressureRatio = 1.6)
        assertEquals(11.0, firstDecision, EPSILON)

        tuner.applyReward(reward = 1.0)

        val secondDecision = tuner.decide(pressureRatio = 1.6)
        assertEquals(12.0, secondDecision, EPSILON)
    }

    @Test
    fun holdsThresholdWhenRaiseIsPenalized() {
        val tuner = AdaptiveThresholdTuner(
            minThreshold = 5.0,
            maxThreshold = 20.0,
            initialThreshold = 10.0,
            step = 1.0,
            explorationRate = 0.0,
        )

        tuner.decide(pressureRatio = 1.6)
        tuner.applyReward(reward = -1.0)

        val decision = tuner.decide(pressureRatio = 1.6)
        assertEquals(11.0, decision, EPSILON)
    }

    @Test
    fun lowersThresholdWhenLoadIsIdle() {
        val tuner = AdaptiveThresholdTuner(
            minThreshold = 5.0,
            maxThreshold = 20.0,
            initialThreshold = 10.0,
            step = 2.0,
            explorationRate = 0.0,
        )

        tuner.decide(pressureRatio = 0.4)
        tuner.applyReward(reward = 0.5)

        val decision = tuner.decide(pressureRatio = 0.4)
        assertEquals(6.0, decision, EPSILON)

        val boundedDecision = tuner.decide(pressureRatio = 0.1)
        assertEquals(5.0, boundedDecision, EPSILON)
    }
}
