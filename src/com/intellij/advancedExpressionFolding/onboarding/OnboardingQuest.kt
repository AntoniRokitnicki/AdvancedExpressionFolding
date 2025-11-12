package com.intellij.advancedExpressionFolding.onboarding

/**
 * Describes a quest that introduces a feature during the onboarding experience.
 */
data class OnboardingQuest(
    val id: String,
    val title: String,
    val description: String,
    val steps: List<QuestStep>,
    val rewardPoints: Int,
)

/**
 * Represents a single actionable step in a quest.
 */
data class QuestStep(
    val id: String,
    val title: String,
    val description: String,
)
