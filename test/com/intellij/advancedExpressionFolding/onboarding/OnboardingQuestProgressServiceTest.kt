package com.intellij.advancedExpressionFolding.onboarding

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class OnboardingQuestProgressServiceTest {

    @Test
    fun `new service starts with empty progress`() {
        val service = OnboardingQuestProgressService()

        assertTrue(service.completedQuests().isEmpty())
        OnboardingQuestRegistry.quests.forEach { quest ->
            assertFalse(service.isCompleted(quest.id))
        }
    }

    @Test
    fun `quest completion can be toggled`() {
        val service = OnboardingQuestProgressService()
        val quest = OnboardingQuestRegistry.quests.first()

        service.setCompleted(quest.id, true)
        assertTrue(service.isCompleted(quest.id))
        assertTrue(service.completedQuests().contains(quest.id))

        service.setCompleted(quest.id, false)
        assertFalse(service.isCompleted(quest.id))
        assertFalse(service.completedQuests().contains(quest.id))
    }
}
