package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.DialogWrapper.DialogWrapperAction
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.JBEmptyBorder
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import javax.swing.Action
import javax.swing.JComponent
import javax.swing.JPanel

internal class BeginnerOnboardingTour(project: Project?) : DialogWrapper(project, true) {
    private data class Step(val title: String, val description: String)

    private val steps = listOf(
        Step(
            title = "See what folds are enabled",
            description = "Open any Java file and hover the gutter icons to preview how the plugin hides boilerplate. " +
                "These hints explain the fold so you never lose track of the original code."
        ),
        Step(
            title = "Experiment with bundled examples",
            description = "Use \"Checkout Examples\" in the settings panel to pull a playground of sample files. " +
                "Each file demonstrates a different category of folding in a safe sandbox."
        ),
        Step(
            title = "Tweak settings gradually",
            description = "Enable groups one at a time. The checkboxes update immediately, and you can restore defaults " +
                "whenever something feels unfamiliar."
        ),
        Step(
            title = "Need a reset?",
            description = "Click \"Restore defaults\" or re-run this tour anytime. The plugin never modifies your code; it only " +
                "changes how it is displayed."
        )
    )

    private var currentStepIndex = 0
    private val titleLabel = JBLabel()
    private val descriptionLabel = JBLabel()

    private val nextAction = object : DialogWrapperAction("Next") {
        override fun doAction(e: ActionEvent) {
            if (currentStepIndex >= steps.lastIndex) {
                close(OK_EXIT_CODE)
            } else {
                currentStepIndex += 1
                updateStep()
            }
        }
    }

    private val backAction = object : DialogWrapperAction("Back") {
        override fun doAction(e: ActionEvent) {
            if (currentStepIndex <= 0) {
                return
            }
            currentStepIndex -= 1
            updateStep()
        }
    }

    init {
        title = "Beginner Onboarding Tour"
        init()
        updateStep()
    }

    override fun createCenterPanel(): JComponent {
        return JPanel(BorderLayout()).apply {
            border = JBEmptyBorder(12)
            add(titleLabel, BorderLayout.NORTH)
            add(descriptionLabel, BorderLayout.CENTER)
        }
    }

    override fun createActions(): Array<Action> {
        return arrayOf(backAction, nextAction, cancelAction)
    }

    private fun updateStep() {
        val step = steps[currentStepIndex]
        titleLabel.text = "<html><b>${step.title}</b></html>"
        descriptionLabel.text = "<html style=\"width:320px;\">${step.description}</html>"
        backAction.isEnabled = currentStepIndex > 0
        nextAction.putValue(Action.NAME, if (currentStepIndex >= steps.lastIndex) "Done" else "Next")
    }
}
