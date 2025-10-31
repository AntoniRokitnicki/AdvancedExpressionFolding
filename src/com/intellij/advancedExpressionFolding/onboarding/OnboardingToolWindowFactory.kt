package com.intellij.advancedExpressionFolding.onboarding

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.ScrollPaneFactory
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.content.ContentFactory
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import java.awt.Component
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JPanel

class OnboardingToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val content = ContentFactory.getInstance().createContent(createContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    private fun createContent(): JComponent {
        val questsPanel = JPanel()
        questsPanel.layout = BoxLayout(questsPanel, BoxLayout.Y_AXIS)
        questsPanel.border = JBUI.Borders.empty(12)
        questsPanel.isOpaque = false

        val progress = OnboardingQuestProgressService.getInstance()

        OnboardingQuestRegistry.quests.forEachIndexed { index, quest ->
            val checkbox = JBCheckBox(quest.title)
            checkbox.alignmentX = Component.LEFT_ALIGNMENT
            checkbox.isSelected = progress.isCompleted(quest.id)
            checkbox.addActionListener {
                progress.setCompleted(quest.id, checkbox.isSelected)
            }
            questsPanel.add(checkbox)

            if (quest.description.isNotBlank()) {
                val description = JBLabel(quest.description)
                description.alignmentX = Component.LEFT_ALIGNMENT
                description.foreground = JBUI.CurrentTheme.Label.disabledForeground()
                description.border = JBUI.Borders.emptyLeft(24)
                questsPanel.add(description)
            }

            if (index != OnboardingQuestRegistry.quests.lastIndex) {
                questsPanel.add(Box.createVerticalStrut(12))
            }
        }

        val scrollPane = ScrollPaneFactory.createScrollPane(questsPanel, true)
        scrollPane.border = JBUI.Borders.empty()

        val wrapper = JPanel(BorderLayout())
        wrapper.add(scrollPane, BorderLayout.CENTER)
        return wrapper
    }
}
