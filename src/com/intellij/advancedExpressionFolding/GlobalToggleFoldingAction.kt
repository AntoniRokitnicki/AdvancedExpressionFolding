package com.intellij.advancedExpressionFolding

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction

class GlobalToggleFoldingAction(private val state: AdvancedExpressionFoldingSettings.IConfig = AdvancedExpressionFoldingSettings.getInstance().state) :
    ToggleAction(), AdvancedExpressionFoldingSettings.IConfig by state {

    override fun isSelected(e: AnActionEvent): Boolean = globalOn

    override fun setSelected(e: AnActionEvent, state: Boolean) {
        globalOn = state
    }

    override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.EDT
}
