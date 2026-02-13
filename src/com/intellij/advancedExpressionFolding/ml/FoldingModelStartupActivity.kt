package com.intellij.advancedExpressionFolding.ml

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class FoldingModelStartupActivity : StartupActivity {
    override fun runActivity(project: Project) {
        FoldingModelService.getInstance().initialize()
    }
}
