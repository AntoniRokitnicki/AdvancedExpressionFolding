package com.intellij.advancedExpressionFolding.action

import com.intellij.advancedExpressionFolding.view.ContractToolingShowcaseDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class ShowContractToolingComponentsAction : AnAction("Show Contract Tooling Components") {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        ContractToolingShowcaseDialog(project).show()
    }
}
