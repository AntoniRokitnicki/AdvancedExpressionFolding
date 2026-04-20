package com.intellij.advancedExpressionFolding.onboarding

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.CollectionListModel
import com.intellij.ui.ColoredListCellRenderer
import com.intellij.ui.SimpleTextAttributes
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.content.ContentFactory
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.JProgressBar
import javax.swing.ListSelectionModel

/**
 * Tool window that showcases onboarding quests with their current progress.
 */
class GamifiedOnboardingToolWindowFactory : ToolWindowFactory, DumbAware {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = GamifiedOnboardingPanel()
        val content = ContentFactory.getInstance().createContent(panel, null, false)
        toolWindow.contentManager.addContent(content)
        Disposer.register(toolWindow.disposable, panel)
    }

    private class GamifiedOnboardingPanel : JPanel(BorderLayout()), Disposable {
        private val questService = service<GamifiedOnboardingQuestService>()
        private val questsModel = CollectionListModel(questService.availableQuests())
        private val questList = JBList(questsModel)
        private val questDetails = JPanel(BorderLayout())
        private val connection = ApplicationManager.getApplication().messageBus.connect(this)

        init {
            border = JBUI.Borders.empty()
            questList.cellRenderer = QuestCellRenderer()
            questList.visibleRowCount = 8
            questList.selectionMode = ListSelectionModel.SINGLE_SELECTION
            questList.addListSelectionListener { updateDetails(questList.selectedValue) }

            val listContainer = JPanel(BorderLayout())
            listContainer.border = JBUI.Borders.compound(
                JBUI.Borders.empty(8, 8, 8, 4),
                JBUI.Borders.customLine(UIUtil.getPanelBackground().darker(), 0, 0, 0, 1),
            )
            listContainer.add(JBLabel("Quests"), BorderLayout.NORTH)
            listContainer.add(JBScrollPane(questList), BorderLayout.CENTER)

            val detailContainer = JPanel(BorderLayout())
            detailContainer.border = JBUI.Borders.empty(8)
            detailContainer.add(questDetails, BorderLayout.CENTER)

            add(listContainer, BorderLayout.WEST)
            add(detailContainer, BorderLayout.CENTER)

            connection.subscribe(GamifiedOnboardingQuestListener.TOPIC, GamifiedOnboardingQuestListener { refreshSelection() })

            refreshSelection()
        }

        override fun dispose() {
            connection.disconnect()
        }

        private fun refreshSelection() {
            val activeQuest = questService.getActiveQuest()
            if (activeQuest != null) {
                questList.setSelectedValue(activeQuest, true)
            } else if (!questsModel.isEmpty) {
                questList.selectedIndex = 0
            }
            questList.repaint()
            updateDetails(questList.selectedValue)
        }

        private fun updateDetails(quest: OnboardingQuest?) {
            questDetails.removeAll()
            if (quest == null) {
                questDetails.add(createInfoLabel("No quests available."), BorderLayout.CENTER)
            } else {
                questDetails.add(createQuestDetail(quest), BorderLayout.CENTER)
            }
            questDetails.revalidate()
            questDetails.repaint()
        }

        private fun createQuestDetail(quest: OnboardingQuest): JComponent {
            val wrapper = JPanel(BorderLayout())
            wrapper.border = JBUI.Borders.empty()

            val header = JPanel(BorderLayout())
            header.border = JBUI.Borders.empty(0, 0, 8, 0)
            header.add(JBLabel(quest.title).apply { font = UIUtil.getLabelFont().deriveFont(UIUtil.getLabelFont().size2D + 1) }, BorderLayout.NORTH)
            header.add(JBLabel(quest.description).apply { foreground = UIUtil.getLabelDisabledForeground() }, BorderLayout.SOUTH)

            val progress = questService.getProgress(quest.id)
            val progressPanel = JPanel(BorderLayout())
            progressPanel.border = JBUI.Borders.empty(0, 0, 8, 0)
            val progressBar = JProgressBar(0, progress.totalSteps.coerceAtLeast(1))
            progressBar.value = progress.completedStepIds.size
            progressBar.isStringPainted = true
            progressBar.string = "${progress.completedStepIds.size}/${progress.totalSteps} steps"
            progressPanel.add(progressBar, BorderLayout.CENTER)

            val buttonPanel = JPanel(FlowLayout(FlowLayout.LEFT, 8, 0))
            val setActiveButton = JButton("Set active").apply {
                isEnabled = questService.getActiveQuest()?.id != quest.id
                addActionListener {
                    questService.setActiveQuest(quest.id)
                }
            }
            val resetButton = JButton("Reset").apply {
                addActionListener {
                    questService.resetQuest(quest.id)
                }
            }
            val claimButton = JButton("Claim reward").apply {
                isEnabled = progress.isQuestCompleted && !progress.rewardClaimed
                addActionListener {
                    questService.claimReward(quest.id)
                }
            }
            val refreshButton = JButton("Refresh").apply {
                addActionListener { refreshSelection() }
            }
            buttonPanel.add(setActiveButton)
            buttonPanel.add(resetButton)
            buttonPanel.add(claimButton)
            buttonPanel.add(refreshButton)

            val stepPanel = JPanel()
            stepPanel.layout = BoxLayout(stepPanel, BoxLayout.Y_AXIS)
            quest.steps.forEach { step ->
                val checkBox = JBCheckBox(step.title, progress.completedStepIds.contains(step.id))
                checkBox.toolTipText = step.description
                checkBox.isEnabled = false
                stepPanel.add(checkBox)
            }
            if (quest.steps.isEmpty()) {
                stepPanel.add(createInfoLabel("No steps required."))
            }

            val rewardLabel = JBLabel(
                when {
                    progress.rewardClaimed -> "Reward claimed: ${quest.rewardPoints} XP"
                    progress.isQuestCompleted -> "Reward ready: ${quest.rewardPoints} XP"
                    else -> "Reward: ${quest.rewardPoints} XP"
                },
            )
            rewardLabel.border = JBUI.Borders.empty(8, 0, 0, 0)

            wrapper.add(header, BorderLayout.NORTH)
            wrapper.add(progressPanel, BorderLayout.CENTER)
            wrapper.add(stepPanel, BorderLayout.SOUTH)

            val bottomBox = Box.createVerticalBox()
            bottomBox.add(rewardLabel)
            bottomBox.add(Box.createVerticalStrut(8))
            bottomBox.add(buttonPanel)

            val container = JPanel(BorderLayout())
            container.add(wrapper, BorderLayout.NORTH)
            container.add(bottomBox, BorderLayout.SOUTH)
            container.preferredSize = Dimension(0, 0)

            return container
        }

        private fun createInfoLabel(text: String): JBLabel = JBLabel(text).apply {
            foreground = UIUtil.getLabelDisabledForeground()
        }

        private inner class QuestCellRenderer : ColoredListCellRenderer<OnboardingQuest>() {
            override fun customizeCellRenderer(
                list: JList<out OnboardingQuest>,
                value: OnboardingQuest?,
                index: Int,
                selected: Boolean,
                hasFocus: Boolean,
            ) {
                if (value == null) {
                    append("", SimpleTextAttributes.REGULAR_ATTRIBUTES)
                    return
                }
                append(value.title, SimpleTextAttributes.REGULAR_ATTRIBUTES)
                val progress = questService.getProgress(value.id)
                val suffix = " ${progress.completedStepIds.size}/${progress.totalSteps}"
                append(suffix, SimpleTextAttributes.GRAYED_ATTRIBUTES)
                if (questService.getActiveQuest()?.id == value.id) {
                    append("  (active)", SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES)
                }
                if (progress.rewardClaimed) {
                    append("  ✓", SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES)
                }
            }
        }
    }
}
