package com.intellij.advancedExpressionFolding.onboarding

/**
 * Static catalogue of onboarding quests shown to newcomers.
 */
object OnboardingQuestRegistry {
    val quests: List<OnboardingQuest> = listOf(
        OnboardingQuest(
            id = "discover-foldings",
            title = "Discover folding switches",
            description = "Toggle a few folding checkboxes to learn what the plugin can collapse."
        ),
        OnboardingQuest(
            id = "checkout-examples",
            title = "Check out example files",
            description = "Use the checkout buttons below to pull sample files into your project."
        ),
        OnboardingQuest(
            id = "personalize-dynamic",
            title = "Personalize dynamic names",
            description = "Enable dynamic names so folded members adopt labels from your dynamic-ajf2.toml."
        )
    )

    fun findById(id: String): OnboardingQuest? = quests.firstOrNull { it.id == id }
}
