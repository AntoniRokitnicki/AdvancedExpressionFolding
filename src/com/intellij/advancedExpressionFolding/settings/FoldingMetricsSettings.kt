package com.intellij.advancedExpressionFolding.settings

import com.intellij.advancedExpressionFolding.metrics.FoldingMetrics
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(name = "AdvancedExpressionFoldingMetrics", storages = [Storage("advancedExpressionFoldingMetrics.xml")])
class FoldingMetricsSettings : PersistentStateComponent<FoldingMetricsSettings.State> {

    data class MetricsState(
        var foldCount: Int = 0,
        var linesSaved: Int = 0,
        var charactersSaved: Int = 0
    ) {
        fun toMetrics(): FoldingMetrics = FoldingMetrics(foldCount, linesSaved, charactersSaved)

        companion object {
            fun from(metrics: FoldingMetrics) = MetricsState(metrics.foldCount, metrics.linesSaved, metrics.charactersSaved)
        }
    }

    data class Entry(
        var fileUrl: String = "",
        var metrics: MetricsState = MetricsState()
    )

    data class State(
        var entries: MutableList<Entry> = mutableListOf()
    )

    private var state = State()

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = state.deepCopy()
    }

    fun updateMetrics(fileUrl: String, metrics: FoldingMetrics) {
        if (fileUrl.isBlank()) {
            return
        }
        val entries = state.entries
        val metricsState = MetricsState.from(metrics)
        val existingIndex = entries.indexOfFirst { it.fileUrl == fileUrl }
        if (existingIndex >= 0) {
            val entry = entries.removeAt(existingIndex)
            entry.metrics = metricsState
            entries.add(entry)
        } else {
            entries.add(Entry(fileUrl, metricsState))
        }
        ensureCapacity(entries)
    }

    fun getMetrics(fileUrl: String): FoldingMetrics? {
        if (fileUrl.isBlank()) {
            return null
        }
        return state.entries.firstOrNull { it.fileUrl == fileUrl }?.metrics?.toMetrics()
    }

    private fun State.deepCopy(): State {
        val copiedEntries = entries.map { entry ->
            entry.copy(metrics = entry.metrics.copy())
        }.toMutableList()
        return State(copiedEntries)
    }

    private fun ensureCapacity(entries: MutableList<Entry>) {
        while (entries.size > MAX_ENTRIES) {
            entries.removeAt(0)
        }
    }

    companion object {
        private const val MAX_ENTRIES = 50

        @JvmStatic
        fun getInstance(): FoldingMetricsSettings =
            ApplicationManager.getApplication().getService(FoldingMetricsSettings::class.java)
    }
}
