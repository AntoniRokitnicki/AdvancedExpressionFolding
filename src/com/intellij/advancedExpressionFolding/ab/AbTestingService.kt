package com.intellij.advancedExpressionFolding.ab

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.abs

private const val UNINITIALIZED_SEED: Long = Long.MIN_VALUE

@State(name = "AdvancedExpressionFoldingAbTesting", storages = [Storage("advancedExpressionFoldingAbTesting.xml")])
class AbTestingService(private val seedProvider: () -> Long = defaultSeedProvider) :
    PersistentStateComponent<AbTestingService.State> {

    data class State(
        var seed: Long = UNINITIALIZED_SEED,
        var assignments: MutableMap<String, String> = mutableMapOf()
    )

    private var state: State = State()
        set(value) {
            field = value
            ensureSeedInitialized()
        }

    init {
        ensureSeedInitialized()
    }

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = State(
            seed = state.seed,
            assignments = state.assignments.toMutableMap()
        )
    }

    @Synchronized
    fun variant(experiment: String, variants: List<String> = DEFAULT_VARIANTS): String {
        require(experiment.isNotBlank()) { "Experiment key must not be blank" }
        require(variants.isNotEmpty()) { "At least one variant is required" }

        state.assignments[experiment]?.let { stored ->
            if (variants.contains(stored)) {
                return stored
            }
        }

        val variant = selectVariant(experiment, variants)
        state.assignments[experiment] = variant
        return variant
    }

    @Synchronized
    fun isInVariant(
        experiment: String,
        variant: String,
        variants: List<String> = DEFAULT_VARIANTS
    ): Boolean = variant(experiment, variants) == variant

    @TestOnly
    @Synchronized
    fun clearAssignments() {
        state.assignments.clear()
    }

    @TestOnly
    fun snapshotState(): State {
        return State(
            seed = state.seed,
            assignments = state.assignments.toMutableMap()
        )
    }

    private fun ensureSeedInitialized() {
        if (state.seed == UNINITIALIZED_SEED) {
            state.seed = sanitizeSeed(seedProvider())
        }
    }

    private fun selectVariant(experiment: String, variants: List<String>): String {
        val mix = sanitizeSeed(state.seed xor experiment.hashCode().toLong())
        val index = (mix % variants.size).toInt()
        return variants[index]
    }

    companion object {
        private val DEFAULT_VARIANTS = listOf("control", "treatment")

        private val defaultSeedProvider: () -> Long = {
            ThreadLocalRandom.current().nextLong()
        }

        private fun sanitizeSeed(value: Long): Long {
            return if (value == Long.MIN_VALUE) 0 else abs(value)
        }

        @JvmStatic
        fun getInstance(): AbTestingService {
            return ApplicationManager.getApplication().getService(AbTestingService::class.java)
        }
    }
}
