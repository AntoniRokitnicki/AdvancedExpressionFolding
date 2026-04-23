package com.intellij.advancedExpressionFolding.processor.util

import kotlin.math.abs
import kotlin.random.Random

/**
 * Reinforcement-based tuner that adjusts a numeric threshold according to feedback from
 * backpressure observations. The implementation intentionally keeps the state space small
 * so it can run inside tests without additional dependencies.
 */
class AdaptiveThresholdTuner(
    private val minThreshold: Double,
    private val maxThreshold: Double,
    initialThreshold: Double,
    private val step: Double,
    private val learningRate: Double = 0.2,
    private val discountFactor: Double = 0.3,
    private val explorationRate: Double = 0.1,
    private val random: Random = Random.Default,
) {
    private val targetRatio = 1.0
    private val ratioTolerance = 0.1

    init {
        require(maxThreshold > minThreshold) { "maxThreshold must be greater than minThreshold" }
        require(step > 0) { "step must be greater than 0" }
        require(learningRate in 0.0..1.0) { "learningRate must be in [0, 1]" }
        require(discountFactor in 0.0..1.0) { "discountFactor must be in [0, 1]" }
        require(explorationRate in 0.0..1.0) { "explorationRate must be in [0, 1]" }
    }

    enum class PressureBand {
        LOW,
        TARGET,
        HIGH,
    }

    private enum class Action {
        LOWER,
        HOLD,
        RAISE,
    }

    private val qValues: MutableMap<Pair<PressureBand, Action>, Double> = HashMap()
    private var lastBand: PressureBand? = null
    private var lastAction: Action? = null

    var threshold: Double = initialThreshold.coerceIn(minThreshold, maxThreshold)
        private set

    /**
     * Chooses the next threshold based on the observed pressure ratio and stored Q-values.
     */
    fun decide(pressureRatio: Double): Double {
        val band = bandFor(pressureRatio)
        val action = selectAction(band)
        lastBand = band
        lastAction = action
        val newThreshold = when (action) {
            Action.LOWER -> threshold - step
            Action.HOLD -> threshold
            Action.RAISE -> threshold + step
        }.coerceIn(minThreshold, maxThreshold)
        threshold = newThreshold
        return threshold
    }

    /**
     * Applies reinforcement feedback. Optionally pass the next pressure ratio so the
     * temporal difference update can consider the expected future reward.
     */
    fun applyReward(reward: Double, nextPressureRatio: Double? = null) {
        val band = lastBand ?: return
        val action = lastAction ?: return
        val key = band to action
        val previous = qValues.getOrElse(key) { 0.0 }
        val future = nextPressureRatio?.let { next ->
            val nextBand = bandFor(next)
            Action.entries.maxOf { qValues.getOrElse(nextBand to it) { 0.0 } }
        } ?: 0.0
        val target = reward + discountFactor * future
        val updated = previous + learningRate * (target - previous)
        qValues[key] = updated
    }

    fun bandFor(pressureRatio: Double): PressureBand {
        if (pressureRatio.isNaN()) return PressureBand.TARGET
        val lowBoundary = targetRatio - ratioTolerance
        val highBoundary = targetRatio + ratioTolerance
        return when {
            pressureRatio < lowBoundary -> PressureBand.LOW
            pressureRatio > highBoundary -> PressureBand.HIGH
            else -> PressureBand.TARGET
        }
    }

    private fun selectAction(band: PressureBand): Action {
        if (random.nextDouble() < explorationRate) {
            return Action.entries[random.nextInt(Action.entries.size)]
        }

        val values = Action.entries.associateWith { qValues.getOrElse(band to it) { 0.0 } }
        if (values.values.all { abs(it) < 1e-9 }) {
            return defaultAction(band)
        }
        val bestValue = values.maxOf { it.value }
        val bestActions = values.filterValues { it == bestValue }.keys
        if (Action.HOLD in bestActions) {
            return Action.HOLD
        }
        val default = defaultAction(band)
        if (default in bestActions) {
            return default
        }
        return bestActions.first()
    }

    private fun defaultAction(band: PressureBand): Action = when (band) {
        PressureBand.LOW -> Action.LOWER
        PressureBand.TARGET -> Action.HOLD
        PressureBand.HIGH -> Action.RAISE
    }
}
