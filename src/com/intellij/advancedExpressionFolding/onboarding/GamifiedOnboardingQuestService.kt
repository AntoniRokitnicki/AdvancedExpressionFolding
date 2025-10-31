package com.intellij.advancedExpressionFolding.onboarding

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.ProjectManager
import com.intellij.ui.EditorNotifications
import com.intellij.util.xmlb.annotations.Property
import com.intellij.util.xmlb.annotations.Tag
import java.util.LinkedHashSet

/**
 * Tracks the player's progress through onboarding quests and persists state across IDE restarts.
 */
@Service(Service.Level.APP)
@State(name = "AdvancedExpressionFoldingGamifiedOnboarding", storages = [Storage("advancedExpressionFoldingOnboarding.xml")])
class GamifiedOnboardingQuestService : PersistentStateComponent<GamifiedOnboardingQuestService.State> {

    private var state = State()

    private val quests = GamifiedOnboardingQuests.defaults().associateBy(OnboardingQuest::id)

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = state.normalize()
        ensureActiveQuestPresent()
        notifyStateChanged()
    }

    fun availableQuests(): List<OnboardingQuest> = quests.values.toList()

    fun getActiveQuest(): OnboardingQuest? {
        ensureActiveQuestPresent()
        return state.activeQuestId?.let(quests::get)
    }

    fun setActiveQuest(questId: String) {
        require(quests.containsKey(questId)) { "Unknown quest id: $questId" }
        state.activeQuestId = questId
        notifyStateChanged()
    }

    fun completeStep(stepId: String): Boolean {
        val quest = getActiveQuest() ?: return false
        if (quest.steps.none { it.id == stepId }) {
            return false
        }
        val progress = state.progressStateFor(quest.id)
        if (progress.completedStepIds.contains(stepId)) {
            return false
        }
        progress.completedStepIds.add(stepId)
        notifyStateChanged()
        return true
    }

    fun isQuestCompleted(questId: String): Boolean {
        val quest = quests[questId] ?: return false
        val progress = state.progress.firstOrNull { it.questId == questId } ?: return false
        return progress.completedStepIds.containsAll(quest.steps.map(QuestStep::id))
    }

    fun claimReward(questId: String): Boolean {
        val quest = quests[questId] ?: return false
        if (!isQuestCompleted(questId)) {
            return false
        }
        val progress = state.progressStateFor(quest.id)
        if (progress.rewardClaimed) {
            return false
        }
        progress.rewardClaimed = true
        notifyStateChanged()
        return true
    }

    fun getProgress(questId: String): QuestProgress {
        val quest = quests[questId]
            ?: error("Unknown quest id: $questId")
        val progress = state.progressStateFor(quest.id)
        return QuestProgress(
            questId = quest.id,
            completedStepIds = progress.completedStepIds.toSet(),
            totalSteps = quest.steps.size,
            rewardClaimed = progress.rewardClaimed,
        )
    }

    fun resetQuest(questId: String) {
        val iterator = state.progress.iterator()
        while (iterator.hasNext()) {
            if (iterator.next().questId == questId) {
                iterator.remove()
                break
            }
        }
        if (state.activeQuestId == questId) {
            state.activeQuestId = null
            ensureActiveQuestPresent()
        }
        notifyStateChanged()
    }

    private fun ensureActiveQuestPresent() {
        if (state.activeQuestId != null && quests.containsKey(state.activeQuestId)) {
            return
        }
        state.activeQuestId = quests.keys.firstOrNull()
    }

    private fun State.progressStateFor(questId: String): QuestProgressState {
        var questProgress = progress.firstOrNull { it.questId == questId }
        if (questProgress == null) {
            questProgress = QuestProgressState(questId)
            this.progress.add(questProgress)
        }
        questProgress.trimToQuest(quests[questId])
        return questProgress
    }

    private fun QuestProgressState.trimToQuest(quest: OnboardingQuest?) {
        if (quest == null) {
            completedStepIds.clear()
            rewardClaimed = false
            return
        }
        val allowedSteps = quest.steps.map(QuestStep::id).toSet()
        completedStepIds.retainAll(allowedSteps)
        if (!completedStepIds.containsAll(allowedSteps)) {
            rewardClaimed = false
        }
    }

    data class State(
        var activeQuestId: String? = null,
        @get:Property(surroundWithTag = false)
        var progress: MutableList<QuestProgressState> = mutableListOf(),
    ) {
        fun normalize(): State {
            val normalizedProgress = progress
                .map { it.copy(completedStepIds = LinkedHashSet(it.completedStepIds)) }
                .distinctBy(QuestProgressState::questId)
                .toMutableList()
            return copy(progress = normalizedProgress)
        }
    }

    @Tag("quest")
    data class QuestProgressState(
        var questId: String = "",
        var completedStepIds: MutableSet<String> = LinkedHashSet(),
        var rewardClaimed: Boolean = false,
    )

    private fun notifyStateChanged() {
        val application = ApplicationManager.getApplication() ?: return
        application.messageBus.syncPublisher(GamifiedOnboardingQuestListener.TOPIC).questStateChanged()
        application.invokeLater {
            ProjectManager.getInstance().openProjects.forEach { project ->
                EditorNotifications.getInstance(project).updateAllNotifications()
            }
        }
    }
}
