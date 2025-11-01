package com.intellij.advancedExpressionFolding.action

import com.intellij.advancedExpressionFolding.view.UiShowcaseDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class OpenUiShowcaseAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        UiShowcaseDialog(project).show()
    }

    override fun update(event: AnActionEvent) {
        val project = event.getData(CommonDataKeys.PROJECT)
        event.presentation.isEnabled = project != null
    }
}
