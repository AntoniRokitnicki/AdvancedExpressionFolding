package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.application.options.editor.EditorOptionsProvider
import java.awt.BorderLayout
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Settings UI exposing a searchable tree of checkboxes backed by the plugin's state.
 * The tree operates on a copy of the settings and the state is only persisted on [apply].
 */
class SettingsConfigurable : EditorOptionsProvider {

    private val settings = AdvancedExpressionFoldingSettings.getInstance()
    private var workingState = settings.state.copy()
    private lateinit var treePanel: SearchableCheckboxTreePanel

    override fun getId() = "advanced.expression.folding"

    override fun getDisplayName() = "Advanced Expression Folding 2"

    override fun getHelpTopic() = null

    override fun createComponent(): JComponent {
        workingState = settings.state.copy()
        treePanel = SearchableCheckboxTreePanel(buildOptionTree(workingState))
        return JPanel(BorderLayout()).apply {
            add(treePanel, BorderLayout.CENTER)
        }
    }

    override fun isModified(): Boolean = workingState != settings.state

    override fun apply() {
        settings.loadState(workingState)
    }

    override fun reset() {
        workingState = settings.state.copy()
        treePanel.setRoot(buildOptionTree(workingState))
    }
}

