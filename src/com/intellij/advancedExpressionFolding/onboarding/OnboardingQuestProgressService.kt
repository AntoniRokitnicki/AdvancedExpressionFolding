package com.intellij.advancedExpressionFolding.onboarding

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service

/**
 * Persists quest completion flags so onboarding progress survives IDE restarts.
 */
@Service
@State(
    name = "AdvancedExpressionFoldingOnboarding",
    storages = [Storage("advancedExpressionFoldingOnboarding.xml")]
)
class OnboardingQuestProgressService : PersistentStateComponent<OnboardingQuestProgressService.State> {
    data class State(
        var completedQuestIds: MutableSet<String> = mutableSetOf()
    )

    private var state = State()

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = State(state.completedQuestIds.toMutableSet())
    }

    fun isCompleted(id: String): Boolean = state.completedQuestIds.contains(id)

    fun setCompleted(id: String, completed: Boolean) {
        if (completed) {
            state.completedQuestIds.add(id)
        } else {
            state.completedQuestIds.remove(id)
        }
    }

    fun completedQuests(): Set<String> = state.completedQuestIds

    companion object {
        fun getInstance(): OnboardingQuestProgressService = service()
    }
}
