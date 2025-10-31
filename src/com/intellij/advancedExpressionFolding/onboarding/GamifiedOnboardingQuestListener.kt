package com.intellij.advancedExpressionFolding.onboarding

import com.intellij.util.messages.Topic

/**
 * Broadcasts when quest progress or selection changes so UI surfaces can update.
 */
fun interface GamifiedOnboardingQuestListener {
    fun questStateChanged()

    companion object {
        val TOPIC: Topic<GamifiedOnboardingQuestListener> = Topic.create(
            "advanced.expression.folding.onboardingQuest",
            GamifiedOnboardingQuestListener::class.java,
        )
    }
}
