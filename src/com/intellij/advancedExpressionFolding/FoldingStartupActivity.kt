package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.extension.off
import com.intellij.ide.plugins.DisabledPluginsState
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class FoldingStartupActivity : ProjectActivity {

    private var runOnce = false

    override suspend fun execute(project: Project) {
        runOnce.off()?.run {
            @Suppress("UnstableApiUsage")
            DisabledPluginsState.addDisablePluginListener {
                DisabledPluginsState.getDisabledIds().firstOrNull {
                    it.idString == "com.github.advanced-java-folding2"
                }?.run {
                    FoldingService.get().clearAllKeys(project)
                }
            }
            runOnce = true
        }
    }
}
