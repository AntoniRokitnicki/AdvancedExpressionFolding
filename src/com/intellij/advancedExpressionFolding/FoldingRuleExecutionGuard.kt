package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.State
import com.intellij.psi.PsiElement
import java.util.ArrayDeque
import java.util.HashSet
import java.util.IdentityHashMap
import java.util.LinkedHashSet
import java.util.concurrent.ConcurrentHashMap

object FoldingRuleExecutionGuard {
    private const val MIN_SAMPLES = 5
    private const val EMA_ALPHA = 0.3
    private const val RESUME_RATIO = 0.6

    private val elementStack = ThreadLocal.withInitial { ArrayDeque<PsiElement>() }
    private val flagsByElement = ThreadLocal.withInitial { IdentityHashMap<PsiElement, MutableSet<String>>() }
    private val stats = ConcurrentHashMap<String, RuleStats>()

    @Volatile
    var durationOverride: ((String, Long) -> Long)? = null

    private data class RuleStats(
        var emaMillis: Double = 0.0,
        var sampleCount: Int = 0
    ) {
        fun update(sampleMillis: Double) {
            sampleCount++
            emaMillis = if (sampleCount == 1) {
                sampleMillis
            } else {
                (1 - EMA_ALPHA) * emaMillis + EMA_ALPHA * sampleMillis
            }
        }

        fun reset() {
            emaMillis = 0.0
            sampleCount = 0
        }
    }

    @JvmStatic
    fun enterBuild(element: PsiElement) {
        elementStack.get().push(element)
    }

    @JvmStatic
    fun exitBuild(element: PsiElement) {
        val stack = elementStack.get()
        if (stack.isNotEmpty()) {
            if (stack.peek() === element) {
                stack.pop()
            } else {
                stack.remove(element)
            }
        }
        val recorded = flagsByElement.get().remove(element)
        if (recorded != null && recorded.isNotEmpty()) {
            element.putUserData(Keys.RULE_FLAGS_KEY, HashSet(recorded))
        }
    }

    fun onFlagAccess(flag: String) {
        val currentElement = elementStack.get().peek() ?: return
        val map = flagsByElement.get()
        val set = map.getOrPut(currentElement) { LinkedHashSet() }
        set.add(flag)
    }

    @JvmStatic
    fun flagsFor(element: PsiElement): Set<String> {
        val fromCurrent = flagsByElement.get()[element]
        if (fromCurrent != null && fromCurrent.isNotEmpty()) {
            return fromCurrent
        }
        return element.getUserData(Keys.RULE_FLAGS_KEY) ?: emptySet()
    }

    @JvmStatic
    fun shouldSkip(flags: Set<String>, state: State): Boolean {
        if (flags.isEmpty()) return false
        return flags.any(state::isAutoDisabled)
    }

    @JvmStatic
    fun record(element: PsiElement, flags: Set<String>, durationNanos: Long, state: State) {
        if (flags.isEmpty()) return
        val threshold = state.throttleThresholdMillis.toDouble()
        if (threshold <= 0) {
            return
        }
        val resumeThreshold = threshold * RESUME_RATIO
        val project = element.project
        val override = durationOverride
        for (flag in flags) {
            val statsForFlag = stats.computeIfAbsent(flag) { RuleStats() }
            var duration = durationNanos
            if (override != null) {
                duration = override.invoke(flag, durationNanos)
            }
            val millis = duration / 1_000_000.0
            statsForFlag.update(millis)
            if (!state.isAutoDisabled(flag)) {
                if (statsForFlag.sampleCount >= MIN_SAMPLES && statsForFlag.emaMillis > threshold) {
                    if (state.setAutoDisabled(flag, true)) {
                        FoldingService.get().notifyRuleThrottled(project, flag)
                    }
                }
            } else if (statsForFlag.sampleCount >= MIN_SAMPLES && statsForFlag.emaMillis < resumeThreshold) {
                if (state.setAutoDisabled(flag, false)) {
                    FoldingService.get().clearNotification(flag)
                }
                resetStats(flag)
            }
        }
    }

    fun resetStats(flag: String) {
        stats.remove(flag)
        FoldingService.get().clearNotification(flag)
    }

    fun resetAll() {
        stats.clear()
        durationOverride = null
        elementStack.remove()
        flagsByElement.remove()
    }
}
