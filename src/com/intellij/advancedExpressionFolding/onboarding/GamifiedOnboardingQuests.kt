package com.intellij.advancedExpressionFolding.onboarding

/**
 * Provides the curated set of onboarding quests used by the plugin.
 */
object GamifiedOnboardingQuests {
    fun defaults(): List<OnboardingQuest> = listOf(
        OnboardingQuest(
            id = "get-started",
            title = "Get started with folding",
            description = "Introduce the most common folding controls in three short actions.",
            steps = listOf(
                QuestStep(
                    id = "enable-global-folding",
                    title = "Enable folding globally",
                    description = "Toggle Advanced Folding on to see expressions collapse automatically.",
                ),
                QuestStep(
                    id = "inspect-folded-region",
                    title = "Inspect a folded region",
                    description = "Hover a folded expression and read the inline summary tooltip.",
                ),
                QuestStep(
                    id = "toggle-single-fold",
                    title = "Manually toggle a fold",
                    description = "Use the gutter icon to temporarily expand a single folded section.",
                ),
            ),
            rewardPoints = 50,
        ),
        OnboardingQuest(
            id = "level-up",
            title = "Level up your shortcuts",
            description = "Learn the fastest shortcuts for enabling, disabling and refreshing folds.",
            steps = listOf(
                QuestStep(
                    id = "use-enable-shortcut",
                    title = "Use the enable shortcut",
                    description = "Press Alt+Shift+F (or Alt+T on macOS) to enable folding.",
                ),
                QuestStep(
                    id = "use-disable-shortcut",
                    title = "Use the disable shortcut",
                    description = "Press Alt+Shift+D (or Alt+Y on macOS) to disable folding.",
                ),
                QuestStep(
                    id = "refresh-folding",
                    title = "Refresh folding colors",
                    description = "Trigger the hidden refresh action from the settings view.",
                ),
            ),
            rewardPoints = 80,
        ),
    )
}
