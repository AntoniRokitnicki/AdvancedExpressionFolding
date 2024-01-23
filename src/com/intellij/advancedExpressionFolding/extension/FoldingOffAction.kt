package com.intellij.advancedExpressionFolding.extension

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys

class FoldingOffAction : AnAction() {
    
    override fun actionPerformed(e: AnActionEvent) {
        e.getData(CommonDataKeys.EDITOR)?.let {
            FoldingService.get().fold(it, false)
        }
    }

}
