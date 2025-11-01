package com.intellij.advancedExpressionFolding.view

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.IdeFocusManager
import com.intellij.util.ui.JBUI
import javax.swing.JPanel

class PlaybookGenerationToolbar(private val project: Project) : JPanel() {

    init {
        border = JBUI.Borders.empty(4)
        val actionGroup = DefaultActionGroup().apply {
            add(StartGenerationAction())
            add(CancelGenerationAction())
            add(RefreshPreviewAction())
        }
        val toolbar = ActionManager.getInstance().createActionToolbar("PlaybookGeneration", actionGroup, true)
        toolbar.setTargetComponent(this)
        add(toolbar.component)
    }

    private inner class StartGenerationAction : AnAction("Start") {
        override fun actionPerformed(e: AnActionEvent) {
            Messages.showInfoMessage(project, "Playbook generation started", "Playbooks")
        }
    }

    private inner class CancelGenerationAction : AnAction("Cancel") {
        override fun actionPerformed(e: AnActionEvent) {
            Messages.showInfoMessage(project, "Playbook generation cancelled", "Playbooks")
        }
    }

    private inner class RefreshPreviewAction : AnAction("Refresh") {
        override fun actionPerformed(e: AnActionEvent) {
            IdeFocusManager.getInstance(project).requestFocus(this@PlaybookGenerationToolbar, true)
            Messages.showInfoMessage(project, "Preview refreshed", "Playbooks")
        }
    }
}
