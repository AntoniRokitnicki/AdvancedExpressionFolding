package com.intellij.advancedExpressionFolding.action

import com.intellij.advancedExpressionFolding.FoldingService
import com.intellij.advancedExpressionFolding.settings.IConfig
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class FoldingOnAction :
    AnAction(),
    IConfig by State()() {

    override fun actionPerformed(e: AnActionEvent) {
        if (!globalOn) {
            globalOn = true
        }
        e.getData(CommonDataKeys.EDITOR)?.let {
            FoldingService.get().fold(it, true)
        }
    }

    override fun isDumbAware() = false

}
