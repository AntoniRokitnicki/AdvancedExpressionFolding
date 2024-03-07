package com.intellij.advancedExpressionFolding

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class FoldingOnAction(private val state: AdvancedExpressionFoldingSettings.IConfig = AdvancedExpressionFoldingSettings.getInstance().state) :
    AnAction(), AdvancedExpressionFoldingSettings.IConfig by state {

    override fun actionPerformed(e: AnActionEvent) {
        if (!state.globalOn) {
            state.globalOn = true
        }
        e.getData(CommonDataKeys.EDITOR)?.let {
            FoldingService.get().fold(it, true)
        }
    }

}
