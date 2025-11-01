package com.intellij.advancedExpressionFolding.view

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.PlainTextLanguage
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.OnePixelSplitter
import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.TreeSpeedSearch
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBLoadingPanel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBPanelWithEmptyText
import com.intellij.ui.components.JBTabbedPane
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.table.JBTable
import com.intellij.ui.treeStructure.SimpleTree
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import java.awt.BorderLayout
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.swing.JComponent
import javax.swing.JTextArea
import javax.swing.event.TreeSelectionListener
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel
import com.intellij.ui.CollectionListModel
import com.intellij.ui.ColoredListCellRenderer
import com.intellij.ui.SimpleColoredComponent
import com.intellij.ui.SimpleTextAttributes
import com.intellij.ui.EditorTextField

class DocumentationDashboardToolWindowFactory : ToolWindowFactory, DumbAware {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val tabs = createTabs(project)
        val content = toolWindow.contentManager.factory.createContent(tabs, null, false)
        toolWindow.contentManager.addContent(content)
    }

    private fun createTabs(project: Project): JBTabbedPane {
        val tabs = JBTabbedPane()
        val sections = listOf(
            OverviewSection(project),
            ContractExplorerSection(),
            PolicyMatrixSection(),
            GenerationTimelineSection(),
            GlossarySection(project)
        )
        sections.forEach { section ->
            tabs.addTab(section.title, section.component)
        }
        return tabs
    }
}

private interface DashboardSection {
    val title: String
    val component: JComponent
}

private class OverviewSection(project: Project) : DashboardSection {
    override val title: String = "Overview"

    private val summaryArea = JTextArea(
        "Generated documentation will appear here once the pipeline runs.\n" +
            "Use the other tabs to configure contracts, policies, and glossary entries."
    ).apply {
        isEditable = false
        lineWrap = true
        wrapStyleWord = true
        background = UIUtil.getPanelBackground()
        border = JBUI.Borders.empty()
    }

    private val loadingPanel = JBLoadingPanel(BorderLayout(), project).apply {
        setLoadingText("Awaiting generation run…")
        add(JBScrollPane(summaryArea), BorderLayout.CENTER)
        stopLoading()
    }

    override val component: JComponent = JBPanel<JBPanel<*>>(BorderLayout()).apply {
        border = JBUI.Borders.empty(12)
        val header = SimpleColoredComponent().apply {
            icon = AllIcons.General.Information
            append("Documentation snapshot", SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES)
        }
        add(header, BorderLayout.NORTH)
        add(loadingPanel, BorderLayout.CENTER)
    }
}

private class ContractExplorerSection : DashboardSection {
    override val title: String = "Contracts"

    private val root = DefaultMutableTreeNode("Services").apply {
        add(DefaultMutableTreeNode("CatalogService").apply {
            add(DefaultMutableTreeNode("v1"))
            add(DefaultMutableTreeNode("v2"))
        })
        add(DefaultMutableTreeNode("BillingService").apply {
            add(DefaultMutableTreeNode("public"))
            add(DefaultMutableTreeNode("internal"))
        })
        add(DefaultMutableTreeNode("PolicyService"))
    }

    private val treeModel = DefaultTreeModel(root)
    private val tree = SimpleTree(treeModel).apply {
        isRootVisible = true
        showsRootHandles = true
        emptyText.text = "No contracts discovered yet"
    }

    private val detailArea = JTextArea().apply {
        isEditable = false
        lineWrap = true
        wrapStyleWord = true
        border = JBUI.Borders.empty(10)
        text = "Select a contract revision to preview its metadata."
    }

    init {
        TreeSpeedSearch(tree)
        tree.addTreeSelectionListener(TreeSelectionListener { event ->
            val node = event?.path?.lastPathComponent as? DefaultMutableTreeNode
            detailArea.text = when {
                node == null || node === root -> "Select a contract revision to preview its metadata."
                node.childCount == 0 -> "Contract '${node.userObject}' is ready for documentation runs."
                else -> "Service '${node.userObject}' exposes ${node.childCount} revisions."
            }
        })
    }

    override val component: JComponent = JBPanel<JBPanel<*>>(BorderLayout()).apply {
        border = JBUI.Borders.empty(8)
        val decoratedTree = ToolbarDecorator.createDecorator(tree)
            .disableAddAction()
            .disableRemoveAction()
            .setToolbarBorder(JBUI.Borders.empty())
            .createPanel()
        val splitter = OnePixelSplitter(false, 0.35f).apply {
            firstComponent = decoratedTree
            secondComponent = JBScrollPane(detailArea)
        }
        add(splitter, BorderLayout.CENTER)
    }
}

private data class PolicyRow(
    val policy: String,
    val scope: String,
    val coverage: String,
    val lastReviewed: String
)

private class PolicyMatrixSection : DashboardSection {
    override val title: String = "Policies"

    private val rows = listOf(
        PolicyRow("Access control", "All services", "Full", "2024-04-12"),
        PolicyRow("Data retention", "Billing", "Partial", "2024-02-18"),
        PolicyRow("PII masking", "Catalog", "Full", "2024-03-03"),
        PolicyRow("Audit trail", "Policy", "Draft", "2024-05-21")
    )

    private val tableModel = javax.swing.table.DefaultTableModel(
        arrayOf("Policy", "Scope", "Coverage", "Last reviewed"),
        0
    ).apply {
        rows.forEach { row ->
            addRow(arrayOf(row.policy, row.scope, row.coverage, row.lastReviewed))
        }
    }

    private val table = JBTable(tableModel).apply {
        tableHeader.reorderingAllowed = false
        setEnabled(false)
    }

    override val component: JComponent = JBPanel<JBPanel<*>>(BorderLayout()).apply {
        border = JBUI.Borders.empty(12)
        val header = SimpleColoredComponent().apply {
            icon = AllIcons.Actions.Checked
            append("Policy coverage matrix", SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES)
        }
        add(header, BorderLayout.NORTH)
        add(JBScrollPane(table), BorderLayout.CENTER)
    }
}

private data class TimelineEntry(
    val timestamp: LocalDateTime,
    val result: String,
    val summary: String
)

private class GenerationTimelineSection : DashboardSection {
    override val title: String = "Timeline"

    private val formatter = DateTimeFormatter.ofPattern("MMM d, HH:mm")

    private val entries = listOf(
        TimelineEntry(LocalDateTime.now().minusHours(5), "Success", "Documentation generated for CatalogService v2"),
        TimelineEntry(LocalDateTime.now().minusHours(12), "Warning", "Policies partially applied to BillingService"),
        TimelineEntry(LocalDateTime.now().minusDays(1), "Failed", "Contract schema validation failed for PolicyService"),
    )

    private val listModel = CollectionListModel(entries)
    private val timelineList = JBList(listModel).apply {
        cellRenderer = object : ColoredListCellRenderer<TimelineEntry>() {
            override fun customizeCellRenderer(
                list: javax.swing.JList<out TimelineEntry>,
                value: TimelineEntry?,
                index: Int,
                selected: Boolean,
                hasFocus: Boolean
            ) {
                if (value == null) return
                icon = when (value.result) {
                    "Success" -> AllIcons.General.InspectionsOK
                    "Warning" -> AllIcons.General.Warning
                    else -> AllIcons.General.Error
                }
                append(formatter.format(value.timestamp), SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES)
                append(" — ${value.result}", SimpleTextAttributes.GRAYED_ATTRIBUTES)
                append("\n${value.summary}", SimpleTextAttributes.REGULAR_ATTRIBUTES)
            }
        }
    }

    private val statusPanel = JBPanelWithEmptyText().apply {
        emptyText.text = "Run generation to view history."
        layout = BorderLayout()
        add(JBScrollPane(timelineList), BorderLayout.CENTER)
    }

    override val component: JComponent = JBPanel<JBPanel<*>>(BorderLayout()).apply {
        border = JBUI.Borders.empty(10)
        val headerPanel = JBPanel<JBPanel<*>>().apply {
            layout = java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0)
            add(JBLabel("Recent runs"))
            val progress = javax.swing.JProgressBar()
            progress.isIndeterminate = false
            progress.maximum = 100
            progress.value = 60
            progress.string = "Sync health"
            progress.isStringPainted = true
            add(progress)
        }
        add(headerPanel, BorderLayout.NORTH)
        add(statusPanel, BorderLayout.CENTER)
    }
}

private data class GlossaryEntry(val term: String, val description: String)

private class GlossarySection(project: Project) : DashboardSection {
    override val title: String = "Glossary"

    private val terms = listOf(
        GlossaryEntry("SLO", "Service level objective that the contract guarantees"),
        GlossaryEntry("Runbook", "Step-by-step playbook generated from policies"),
        GlossaryEntry("Control", "Policy rule applied to a section of the document"),
        GlossaryEntry("Drift", "Mismatch between policy intent and service implementation")
    )

    private val model = CollectionListModel(terms)
    private val list = JBList(model).apply {
        cellRenderer = object : ColoredListCellRenderer<GlossaryEntry>() {
            override fun customizeCellRenderer(
                list: javax.swing.JList<out GlossaryEntry>,
                value: GlossaryEntry?,
                index: Int,
                selected: Boolean,
                hasFocus: Boolean
            ) {
                if (value == null) return
                append(value.term, SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES)
            }
        }
    }

    private val descriptionField = EditorTextField("", project, PlainTextLanguage.INSTANCE.associatedFileType).apply {
        isViewer = true
        border = JBUI.Borders.empty(12)
    }

    init {
        list.addListSelectionListener {
            val selected = list.selectedValue
            descriptionField.text = selected?.description ?: "Select a term to read its description"
        }
        if (model.size > 0) {
            list.selectedIndex = 0
            descriptionField.text = terms.first().description
        }
    }

    override val component: JComponent = panel {
        row {
            val splitter = OnePixelSplitter(false, 0.4f)
            splitter.firstComponent = JBScrollPane(list)
            splitter.secondComponent = descriptionField
            cell(splitter)
                .align(Align.FILL)
                .resizableColumn()
        }.resizableRow()
    }.apply {
        border = JBUI.Borders.empty(8)
        preferredSize = JBUI.size(360, 240)
    }
}
