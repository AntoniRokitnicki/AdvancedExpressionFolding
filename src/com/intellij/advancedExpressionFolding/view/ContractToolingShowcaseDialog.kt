package com.intellij.advancedExpressionFolding.view

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.util.ui.JBUI
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTabbedPane
import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel

class ContractToolingShowcaseDialog(private val project: Project) : DialogWrapper(project) {

    init {
        title = "Contract Tooling Components"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val tabbedPane = JBTabbedPane()

        tabbedPane.add("Folding Summary", createFoldingSummaryPanel())
        tabbedPane.add("Policy Sync", createPolicySyncPanel())
        tabbedPane.add("Documentation", createDocumentationPanel())
        tabbedPane.add("Playbooks", createPlaybookPanel())
        tabbedPane.add("Contract Selection", createContractSelectionPanel())
        tabbedPane.add("Usage View", createUsageViewPanel())

        return tabbedPane
    }

    private fun createFoldingSummaryPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        panel.border = JBUI.Borders.empty(12)

        val summaryPanel = FoldingSummaryPanel(
            listOf(
                "Contracts collapsed: 8",
                "Policy blocks collapsed: 5",
                "Warnings suppressed: 2"
            )
        )

        panel.add(JBLabel("Preview of folding summary output"), BorderLayout.NORTH)
        panel.add(summaryPanel, BorderLayout.CENTER)

        return panel
    }

    private fun createPolicySyncPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        panel.border = JBUI.Borders.empty(12)

        val statusPanel = PolicySyncStatusPanel().apply {
            updateStatus("Synchronizing policies...", 60)
        }

        val refreshButton = JButton("Complete Sync").apply {
            addActionListener {
                statusPanel.updateStatus("Policies synchronized", 100)
            }
        }

        panel.add(JBLabel("Policy synchronization status"), BorderLayout.NORTH)
        panel.add(statusPanel, BorderLayout.CENTER)
        panel.add(refreshButton, BorderLayout.SOUTH)

        return panel
    }

    private fun createDocumentationPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        panel.border = JBUI.Borders.empty(12)

        val documentationPanel = DocumentationPreviewPanel(project).apply {
            setPreviewText(
                """
                ## Policy Documentation
                
                This panel previews the generated contract documentation before publishing.
                Update the source rules to refresh the preview.
                """.trimIndent()
            )
        }

        panel.add(JBLabel("Documentation preview"), BorderLayout.NORTH)
        panel.add(documentationPanel, BorderLayout.CENTER)

        return panel
    }

    private fun createPlaybookPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        panel.border = JBUI.Borders.empty(12)

        panel.add(JBLabel("Playbook generation controls"), BorderLayout.NORTH)
        panel.add(PlaybookGenerationToolbar(project), BorderLayout.CENTER)

        return panel
    }

    private fun createContractSelectionPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        panel.border = JBUI.Borders.empty(12)

        val openDialogButton = JButton("Open Contract Selector").apply {
            addActionListener {
                val dialog = ContractSelectionDialog(
                    project,
                    listOf("AccessPolicy", "PaymentContract", "NotificationRule")
                )
                if (dialog.showAndGet()) {
                    val chosen = dialog.selectedContract() ?: "No contract selected"
                    Messages.showInfoMessage(project, chosen, "Selected Contract")
                }
            }
        }

        panel.add(JBLabel("Launch the dedicated contract selection dialog"), BorderLayout.NORTH)
        panel.add(openDialogButton, BorderLayout.CENTER)

        return panel
    }

    private fun createUsageViewPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        panel.border = JBUI.Borders.empty(12)

        val openUsageButton = JButton("Open Usage View").apply {
            addActionListener {
                FindUsageCustomView(project, "Contract usages preview")
                Messages.showInfoMessage(
                    project,
                    "Usage view initialized in the Find tool window.",
                    "Usage View"
                )
            }
        }

        panel.add(JBLabel("Initialize the custom usage view"), BorderLayout.NORTH)
        panel.add(openUsageButton, BorderLayout.CENTER)

        return panel
    }
}
