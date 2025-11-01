package com.intellij.advancedExpressionFolding.view.sandbox

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

internal class DslSandboxToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = DslSandboxPanel(project)
        val content = ContentFactory.getInstance().createContent(panel, "Playground", false)
        toolWindow.contentManager.addContent(content)
    }
}
