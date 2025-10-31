package com.intellij.advancedExpressionFolding.onboarding

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GamifiedOnboardingQuestServiceTest {

    private val service = GamifiedOnboardingQuestService()

    @Test
    fun `defaults to first quest`() {
        val defaultQuest = GamifiedOnboardingQuests.defaults().first()

        val activeQuest = service.getActiveQuest()

        assertNotNull(activeQuest)
        assertEquals(defaultQuest.id, activeQuest!!.id)
    }

    @Test
    fun `completing step is idempotent`() {
        val quest = service.getActiveQuest() ?: error("Active quest expected")
        val firstStep = quest.steps.first()

        val firstCompletion = service.completeStep(firstStep.id)
        val secondCompletion = service.completeStep(firstStep.id)
        val progress = service.getProgress(quest.id)

        assertTrue(firstCompletion)
        assertFalse(secondCompletion)
        assertEquals(setOf(firstStep.id), progress.completedStepIds)
        assertFalse(progress.rewardClaimed)
    }

    @Test
    fun `reward can be claimed exactly once`() {
        val quest = service.availableQuests().first()
        quest.steps.forEach { step ->
            assertTrue(service.completeStep(step.id))
        }
        val beforeClaimProgress = service.getProgress(quest.id)
        assertTrue(beforeClaimProgress.isQuestCompleted)

        val firstClaim = service.claimReward(quest.id)
        val secondClaim = service.claimReward(quest.id)
        val afterClaimProgress = service.getProgress(quest.id)

        assertTrue(firstClaim)
        assertFalse(secondClaim)
        assertTrue(afterClaimProgress.rewardClaimed)
    }
}
