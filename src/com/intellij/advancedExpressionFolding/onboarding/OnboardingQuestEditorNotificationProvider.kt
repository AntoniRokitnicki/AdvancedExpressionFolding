package com.intellij.advancedExpressionFolding.onboarding

import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.EditorNotificationPanel
import com.intellij.ui.EditorNotificationProvider
import java.util.function.Function

/**
 * Editor banner that highlights the current onboarding quest and remaining work.
 */
class OnboardingQuestEditorNotificationProvider : EditorNotificationProvider, DumbAware {

    override fun collectNotificationData(project: Project, file: VirtualFile): Function<in FileEditor, out EditorNotificationPanel?>? {
        val questService = service<GamifiedOnboardingQuestService>()
        val activeQuest = questService.getActiveQuest() ?: return null
        val progress = questService.getProgress(activeQuest.id)

        val message = buildString {
            append(activeQuest.title)
            append(" — ")
            if (progress.isQuestCompleted) {
                append("All steps complete")
            } else {
                val remaining = progress.remainingSteps
                append("$remaining step${if (remaining == 1) "" else "s"} remaining")
            }
        }

        return Function { _: FileEditor ->
            EditorNotificationPanel().apply {
                text = message
                toolTipText = activeQuest.description
                createActionLabel("View quests") {
                    ToolWindowManager.getInstance(project)
                        .getToolWindow(TOOLWINDOW_ID)
                        ?.activate(null, true)
                }
                if (progress.isQuestCompleted && !progress.rewardClaimed) {
                    createActionLabel("Claim reward") {
                        questService.claimReward(activeQuest.id)
                    }
                }
                if (!progress.isQuestCompleted) {
                    createActionLabel("Reset progress") {
                        questService.resetQuest(activeQuest.id)
                    }
                }
            }
        }
    }

    companion object {
        private const val TOOLWINDOW_ID = "Onboarding Quests"
    }
}
