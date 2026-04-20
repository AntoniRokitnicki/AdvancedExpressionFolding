package com.intellij.advancedExpressionFolding.onboarding

/**
 * Immutable snapshot of a quest progress used by the UI layer.
 */
data class QuestProgress(
    val questId: String,
    val completedStepIds: Set<String>,
    val totalSteps: Int,
    val rewardClaimed: Boolean,
) {
    val isQuestCompleted: Boolean
        get() = totalSteps == 0 || completedStepIds.size >= totalSteps

    val remainingSteps: Int
        get() = (totalSteps - completedStepIds.size).coerceAtLeast(0)
}
