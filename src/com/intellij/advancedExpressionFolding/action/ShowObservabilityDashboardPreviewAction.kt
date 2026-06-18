package com.intellij.advancedExpressionFolding.action

import com.intellij.advancedExpressionFolding.view.observability.ObservabilityDashboardPreviewDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware

class ShowObservabilityDashboardPreviewAction : AnAction(), DumbAware {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        ObservabilityDashboardPreviewDialog(project).show()
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = e.project != null
    }
}
