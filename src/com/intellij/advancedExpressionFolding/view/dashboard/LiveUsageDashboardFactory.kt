package com.intellij.advancedExpressionFolding.view.dashboard

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class LiveUsageDashboardFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val service = project.service<LiveUsageDashboardService>()
        val panel = LiveUsageDashboardPanel(service)
        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, null, false)
        content.isCloseable = false
        toolWindow.contentManager.addContent(content)
    }
}
