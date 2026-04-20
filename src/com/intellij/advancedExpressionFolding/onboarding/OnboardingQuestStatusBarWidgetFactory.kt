package com.intellij.advancedExpressionFolding.onboarding

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.service
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory
import com.intellij.util.Consumer
import javax.swing.JComponent
import java.awt.Component
import java.awt.event.MouseEvent

/**
 * Status bar widget showing the active onboarding quest progress.
 */
class OnboardingQuestStatusBarWidgetFactory : StatusBarWidgetFactory, DumbAware {
    override fun getId(): String = WIDGET_ID

    override fun getDisplayName(): String = "Onboarding quest progress"

    override fun isAvailable(project: Project): Boolean = true

    override fun createWidget(project: Project): StatusBarWidget = OnboardingQuestStatusBarWidget(project)

    override fun disposeWidget(widget: StatusBarWidget) {
        widget.dispose()
    }

    override fun canBeEnabledOn(statusBar: StatusBar): Boolean = true

    private class OnboardingQuestStatusBarWidget(private val project: Project) : StatusBarWidget, StatusBarWidget.Multiframe {
        private val questService = service<GamifiedOnboardingQuestService>()
        private var statusBar: StatusBar? = null
        private var cachedText: String = ""
        private var tooltip: String? = null
        private val connection = ApplicationManager.getApplication().messageBus.connect()
        private val presentation = object : StatusBarWidget.TextPresentation {
            override fun getText(): String = cachedText
            override fun getTooltipText(): String? = tooltip
            override fun getClickConsumer(): Consumer<MouseEvent>? = null
            override fun getAlignment(): Float = Component.CENTER_ALIGNMENT
        }

        init {
            connection.subscribe(GamifiedOnboardingQuestListener.TOPIC, GamifiedOnboardingQuestListener { updateText() })
            updateText()
        }

        override fun ID(): String = WIDGET_ID

        override fun install(statusBar: StatusBar) {
            this.statusBar = statusBar
        }

        override fun dispose() {
            connection.disconnect()
        }

        override fun getComponent(): JComponent? = null

        override fun getPresentation(): StatusBarWidget.WidgetPresentation = presentation

        override fun copy(): StatusBarWidget = OnboardingQuestStatusBarWidget(project)

        private fun updateText() {
            val activeQuest = questService.getActiveQuest()
            if (activeQuest == null) {
                cachedText = "No onboarding quest"
                tooltip = null
            } else {
                val progress = questService.getProgress(activeQuest.id)
                val completed = progress.completedStepIds.size
                val total = progress.totalSteps
                val rewardSuffix = when {
                    progress.rewardClaimed -> " • reward claimed"
                    progress.isQuestCompleted -> " • reward ready"
                    else -> ""
                }
                cachedText = "${activeQuest.title}: $completed/$total$rewardSuffix"
                tooltip = activeQuest.description
            }
            statusBar?.updateWidget(ID())
        }
    }

    companion object {
        private const val WIDGET_ID = "advanced.expression.folding.onboardingQuestStatus"
    }
}
