package com.intellij.advancedExpressionFolding.view

import com.intellij.openapi.fileTypes.PlainTextFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.Messages
import com.intellij.ui.ColoredListCellRenderer
import com.intellij.ui.SimpleTextAttributes
import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTabbedPane
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.ColumnInfo
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.ListTableModel
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.DefaultComboBoxModel
import javax.swing.DefaultListModel
import javax.swing.JComponent
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.JSlider
import javax.swing.JSpinner
import javax.swing.JTable
import javax.swing.ListSelectionModel
import javax.swing.SpinnerNumberModel
import javax.swing.SwingConstants
import com.intellij.ui.EditorTextField

class UiShowcaseDialog(private val project: Project?) : DialogWrapper(project) {

    init {
        title = "Documentation Automation UI Showcase"
        init()
    }

    override fun createCenterPanel(): JComponent {
        val tabbedPane = JBTabbedPane()
        tabbedPane.addTab("Quick Start", createQuickStartPanel())
        tabbedPane.addTab("Contract Viewer", createContractViewerPanel(project))
        tabbedPane.addTab("Policy Matrix", createPolicyMatrixPanel())
        tabbedPane.addTab("Event Timeline", createEventTimelinePanel())
        tabbedPane.addTab("Automation Toolkit", createAutomationToolkitPanel())
        tabbedPane.preferredSize = Dimension(640, 420)
        return tabbedPane
    }

    private fun createQuickStartPanel(): DialogPanel {
        return panel {
            group("Environment Setup") {
                row("Target workspace:") {
                    comboBox(DefaultComboBoxModel(arrayOf("Sandbox", "Staging", "Production")))
                        .applyToComponent {
                            selectedIndex = 0
                            toolTipText = "Choose where generated documentation will be published"
                        }
                }
                row {
                    checkBox("Sync service contracts on save")
                        .comment("Automatically watches schema files and regenerates playbooks.")
                }
                row {
                    checkBox("Validate policy modules before release")
                        .comment("Runs compliance checks against staging policies.")
                }
                row {
                    button("Run dry run") {
                        Messages.showInfoMessage(
                            "Simulated run finished without errors.",
                            "Automation Preview"
                        )
                    }
                    button("Open activity log") {
                        Messages.showInfoMessage(
                            "No recent automation runs logged.",
                            "Automation Preview"
                        )
                    }
                }
            }
            group("Quality Gates") {
                row("Coverage threshold:") {
                    val slider = JSlider(0, 100, 80).apply {
                        majorTickSpacing = 20
                        minorTickSpacing = 10
                        paintTicks = true
                        paintLabels = true
                    }
                    cell(slider)
                    cell(JBLabel("Blocks releases below the selected readiness score."))
                }
                row("Required peer reviews:") {
                    val spinner = JSpinner(SpinnerNumberModel(1, 1, 10, 1))
                    cell(spinner)
                    cell(JBLabel("Number of approvals before automation publishes."))
                }
            }
        }.apply {
            preferredSize = Dimension(600, 380)
        }
    }

    private fun createContractViewerPanel(project: Project?): JComponent {
        val contractPreview = """
            {
              "service": "Inventory",
              "version": "1.4.0",
              "endpoints": [
                { "path": "/stock/{id}", "method": "GET", "policy": "public" },
                { "path": "/stock", "method": "POST", "policy": "audited" }
              ]
            }
        """.trimIndent()
        val editor = EditorTextField(contractPreview, project, PlainTextFileType.INSTANCE)
        editor.setOneLineMode(false)
        editor.preferredSize = Dimension(560, 260)
        return JPanel(BorderLayout()).apply {
            border = JBUI.Borders.empty(12)
            add(JBLabel("Schema snapshot", SwingConstants.LEFT).apply {
                font = font.deriveFont(font.size2D + 1f)
            }, BorderLayout.NORTH)
            add(JBScrollPane(editor), BorderLayout.CENTER)
            add(JBLabel("Use IntelliJ code insight to validate and reformat contracts before generating docs."), BorderLayout.SOUTH)
        }
    }

    private fun createPolicyMatrixPanel(): JComponent {
        data class PolicyRow(val module: String, val status: String, val lastSync: String)

        val rows = listOf(
            PolicyRow("AccessControl", "Ready", "2 minutes ago"),
            PolicyRow("RetentionWindow", "Needs review", "12 minutes ago"),
            PolicyRow("AuditTrail", "Queued", "Just now")
        )

        val columns = arrayOf(
            object : ColumnInfo<PolicyRow, String>("Policy Module") {
                override fun valueOf(item: PolicyRow) = item.module
            },
            object : ColumnInfo<PolicyRow, String>("Status") {
                override fun valueOf(item: PolicyRow) = item.status
            },
            object : ColumnInfo<PolicyRow, String>("Last Sync") {
                override fun valueOf(item: PolicyRow) = item.lastSync
            }
        )

        val model = ListTableModel<PolicyRow>(*columns)
        model.items = rows

        val table: JTable = JBTable(model).apply {
            setShowGrid(false)
            selectionModel.selectionMode = ListSelectionModel.SINGLE_SELECTION
            emptyText.text = "No policy modules registered"
            columnModel.getColumn(0).preferredWidth = 180
            columnModel.getColumn(1).preferredWidth = 140
            columnModel.getColumn(2).preferredWidth = 120
        }

        return JPanel(BorderLayout()).apply {
            border = JBUI.Borders.empty(12)
            add(JBLabel("Policy alignment"), BorderLayout.NORTH)
            add(ToolbarDecorator.createDecorator(table).disableAddAction().disableRemoveAction().createPanel(), BorderLayout.CENTER)
        }
    }

    private fun createEventTimelinePanel(): JComponent {
        data class TimelineEntry(val time: String, val summary: String)

        val entries = listOf(
            TimelineEntry("08:12", "Contracts synced from repository"),
            TimelineEntry("08:15", "Policies validated against sandbox"),
            TimelineEntry("08:20", "Playbooks regenerated for Inventory"),
            TimelineEntry("08:24", "Notifications sent to compliance team")
        )

        val model = DefaultListModel<TimelineEntry>().apply {
            entries.forEach { addElement(it) }
        }
        val list = JBList<TimelineEntry>(model)
        list.cellRenderer = object : ColoredListCellRenderer<TimelineEntry>() {
            override fun customizeCellRenderer(
                list: JList<out TimelineEntry>,
                value: TimelineEntry?,
                index: Int,
                selected: Boolean,
                hasFocus: Boolean
            ) {
                if (value != null) {
                    append(value.time, SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES)
                    append(" — ")
                    append(value.summary)
                }
            }
        }
        return JBPanel<JBPanel<*>>(BorderLayout()).apply {
            border = JBUI.Borders.empty(12)
            add(JBLabel("Recent automation activity"), BorderLayout.NORTH)
            add(JBScrollPane(list), BorderLayout.CENTER)
        }
    }

    private fun createAutomationToolkitPanel(): JComponent {
        val items = listOf(
            "Provision developer preview",
            "Publish contract changelog",
            "Rebuild incident playbook",
            "Notify support rotation",
            "Archive previous release"
        )
        val model = DefaultListModel<String>().apply {
            items.forEach { addElement(it) }
        }
        val list = JBList<String>(model).apply {
            visibleRowCount = 5
            selectionMode = ListSelectionModel.SINGLE_SELECTION
            cellRenderer = object : ColoredListCellRenderer<String>() {
                override fun customizeCellRenderer(
                    list: JList<out String>,
                    value: String?,
                    index: Int,
                    selected: Boolean,
                    hasFocus: Boolean
                ) {
                    if (value != null) {
                        append("\u2610 ")
                        append(value)
                    }
                }
            }
        }
        val decorator = ToolbarDecorator.createDecorator(list)
            .disableAddAction()
            .disableRemoveAction()
        return JPanel(BorderLayout()).apply {
            border = JBUI.Borders.empty(12)
            add(JBLabel("Runbooks"), BorderLayout.NORTH)
            add(decorator.createPanel(), BorderLayout.CENTER)
        }
    }
}
