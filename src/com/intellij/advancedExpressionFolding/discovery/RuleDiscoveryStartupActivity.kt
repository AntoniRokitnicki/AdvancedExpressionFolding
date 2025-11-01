package com.intellij.advancedExpressionFolding.discovery

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

internal class RuleDiscoveryStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        project.getService(RuleDiscoveryManager::class.java).initialize()
    }
}
