package com.intellij.advancedExpressionFolding.onboarding

/**
 * Minimal representation of a quest surfaced in the onboarding flow.
 */
data class OnboardingQuest(
    val id: String,
    val title: String,
    val description: String
)
