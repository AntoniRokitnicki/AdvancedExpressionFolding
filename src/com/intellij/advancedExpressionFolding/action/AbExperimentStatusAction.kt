package com.intellij.advancedExpressionFolding.action

import com.intellij.advancedExpressionFolding.ab.AbTestingService
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.ui.Messages

class AbExperimentStatusAction : DumbAwareAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val actionId = ActionManager.getInstance().getId(this) ?: return
        val experiment = actionId.removePrefix(ACTION_ID_PREFIX)
        val variant = AbTestingService.getInstance().variant(experiment)
        val message = "Experiment \"$experiment\" assigned variant \"$variant\"."
        Messages.showInfoMessage(e.project, message, DIALOG_TITLE)
    }

    companion object {
        private const val ACTION_ID_PREFIX = "AdvancedExpressionFolding.AbTesting.Experiment."
        private const val DIALOG_TITLE = "A/B Testing"
    }
}
