package com.intellij.advancedExpressionFolding.discovery

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

internal class RuleDiscoveryToolWindowFactory : ToolWindowFactory, DumbAware {

    override fun shouldBeAvailable(project: Project): Boolean {
        return AdvancedExpressionFoldingSettings.getInstance().state.discoverNewFoldRules
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = RuleDiscoveryPanel(project)
        val content = ContentFactory.getInstance().createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
        val manager = project.getService(RuleDiscoveryManager::class.java)
        panel.update(manager.currentReport())
        val connection = project.messageBus.connect(panel)
        connection.subscribe(RuleDiscoveryListener.TOPIC, RuleDiscoveryListener { report ->
            panel.update(report)
        })
    }
}
