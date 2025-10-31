package com.intellij.advancedExpressionFolding.view

import com.intellij.advancedExpressionFolding.FoldingService
import com.intellij.advancedExpressionFolding.openTextEditors
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.view.CheckboxBuilder
import com.intellij.advancedExpressionFolding.settings.view.CheckboxesProvider
import com.intellij.codeInsight.folding.CodeFoldingManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent
import javax.swing.SwingUtilities
import kotlin.reflect.KMutableProperty0

class LiveRuleTuningDashboardFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val dashboard = LiveRuleTuningDashboard(project)
        val content = ContentFactory.getInstance().createContent(dashboard.component, null, false)
        toolWindow.contentManager.addContent(content)
    }
}

private class LiveRuleTuningDashboard(
    private val project: Project
) : CheckboxesProvider() {

    private val settings = AdvancedExpressionFoldingSettings.getInstance()
    private val foldingService = FoldingService.get()

    val component: JComponent = ScrollPaneFactory.createScrollPane(createPanel())

    private fun createPanel(): JComponent {
        val dialogPanel = panel {
            row {
                val globalToggle = JBCheckBox("Enable folding", settings.state.globalOn)
                globalToggle.addActionListener {
                    settings.state.globalOn = globalToggle.isSelected
                    refreshEditors()
                }
                cell(globalToggle)
            }

            initialize(settings.state)
        }
        dialogPanel.isFocusable = false
        return dialogPanel
    }

    override fun Panel.registerCheckbox(
        property: KMutableProperty0<Boolean>,
        title: String,
        block: (CheckboxBuilder.() -> Unit)?
    ) {
        block?.invoke(CheckboxBuilder())

        val checkbox = JBCheckBox(title, property.get())
        checkbox.addActionListener {
            property.set(checkbox.isSelected)
            refreshEditors()
        }

        row { cell(checkbox) }
    }

    private fun refreshEditors() {
        val runnable = {
            foldingService.clearAllKeys(project)
            val foldingManager = CodeFoldingManager.getInstance(project)
            project.openTextEditors.forEach { editor ->
                foldingManager.updateFoldRegions(editor)
                foldingService.fold(editor, settings.state.globalOn)
            }
        }

        if (SwingUtilities.isEventDispatchThread()) {
            runnable()
        } else {
            ApplicationManager.getApplication().invokeLater(runnable)
        }
    }
}
