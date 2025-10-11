package com.intellij.advancedExpressionFolding.telemetry

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.TestOnly
import java.time.Clock
import kotlin.jvm.JvmOverloads

@State(
    name = "AdvancedExpressionFoldingUsageTelemetry",
    storages = [Storage("advanced-expression-folding-telemetry.xml")]
)
class UsageTelemetryService @JvmOverloads constructor(
    private val clock: Clock = Clock.systemUTC()
) : PersistentStateComponent<UsageTelemetryState> {

    private var state = UsageTelemetryState()

    override fun getState(): UsageTelemetryState = state

    override fun loadState(state: UsageTelemetryState) {
        this.state = state.deepCopy()
    }

    fun recordUsage(expressionClass: Class<out Expression>) {
        val ruleId = expressionClass.name
        val metric = state.metrics.getOrPut(ruleId) { RuleUsageMetric() }
        metric.count += 1
        metric.lastUsedEpochMs = clock.millis()
    }

    fun getMetricsSnapshot(): Map<String, RuleUsageMetric> =
        state.metrics.mapValuesTo(linkedMapOf()) { (_, metric) -> metric.deepCopy() }

    fun reset() {
        state.metrics.clear()
    }

    @TestOnly
    fun dumpState(): UsageTelemetryState = state.deepCopy()

    companion object {
        @JvmStatic
        fun getInstance(): UsageTelemetryService? =
            ApplicationManager.getApplication()?.getService(UsageTelemetryService::class.java)

        @JvmStatic
        fun recordUsageIfEnabled(expressionClass: Class<out Expression>) {
            val application = ApplicationManager.getApplication() ?: return
            if (!AdvancedExpressionFoldingSettings.getInstance().state.telemetryEnabled) {
                return
            }
            application.getService(UsageTelemetryService::class.java).recordUsage(expressionClass)
        }

        @JvmStatic
        fun resetTelemetry() {
            getInstance()?.reset()
        }
    }
}

data class UsageTelemetryState(
    var metrics: MutableMap<String, RuleUsageMetric> = linkedMapOf()
) {
    fun deepCopy(): UsageTelemetryState = UsageTelemetryState(metrics.mapValuesTo(linkedMapOf()) { it.value.deepCopy() })
}

data class RuleUsageMetric(
    var count: Long = 0,
    var lastUsedEpochMs: Long = 0,
) {
    fun deepCopy(): RuleUsageMetric = RuleUsageMetric(count, lastUsedEpochMs)
}
