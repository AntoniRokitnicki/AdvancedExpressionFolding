package com.intellij.advancedExpressionFolding.action

import com.intellij.advancedExpressionFolding.FoldingService
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.IConfig
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class FoldingOnAction(private val state: IConfig = AdvancedExpressionFoldingSettings.getInstance().state) :
    AnAction(), IConfig by state {

    override fun actionPerformed(e: AnActionEvent) {
        if (!state.globalOn) {
            state.globalOn = true
        }
        e.getData(CommonDataKeys.EDITOR)?.let {
            FoldingService.get().fold(it, true)
        }
    }

    override fun isDumbAware() = false

}
