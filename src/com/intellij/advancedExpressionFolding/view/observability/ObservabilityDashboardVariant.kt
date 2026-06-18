package com.intellij.advancedExpressionFolding.view.observability

import com.intellij.ui.OnePixelSplitter
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTabbedPane
import com.intellij.ui.table.JBTable
import com.intellij.ui.treeStructure.SimpleTree
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.SwingConstants
import javax.swing.table.DefaultTableModel
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeModel

/**
 * Provides five lightweight Swing-based variants for previewing the observability dashboard.
 */
enum class ObservabilityDashboardVariant(val displayName: String) {
    STATUS_CARDS("Status cards"),
    TAB_OVERVIEW("Tab overview"),
    SPLIT_SUMMARY("Split summary"),
    TABLE_MATRIX("Table matrix"),
    TREE_TIMELINE("Tree timeline");

    fun createComponent(): JPanel = when (this) {
        STATUS_CARDS -> buildStatusCards()
        TAB_OVERVIEW -> buildTabOverview()
        SPLIT_SUMMARY -> buildSplitSummary()
        TABLE_MATRIX -> buildTableMatrix()
        TREE_TIMELINE -> buildTreeTimeline()
    }

    private fun buildStatusCards(): JPanel {
        val container = JPanel(BorderLayout()).apply {
            border = BorderFactory.createEmptyBorder(16, 16, 16, 16)
        }
        val cardsPanel = JPanel(GridLayout(2, 2, 12, 12))
        cardsPanel.add(createCard("Ingestion", "Healthy", Color(0x2E7D32)))
        cardsPanel.add(createCard("Processing", "Stable", Color(0x1565C0)))
        cardsPanel.add(createCard("Storage", "Lagging", Color(0xEF6C00)))
        cardsPanel.add(createCard("Alerts", "Noisy", Color(0xC62828)))
        container.add(cardsPanel, BorderLayout.CENTER)
        return container
    }

    private fun buildTabOverview(): JPanel {
        val tabs = JBTabbedPane()
        tabs.addTab("Overview", JBScrollPane(buildStatusList()))
        tabs.addTab("Incidents", JBScrollPane(buildIncidentList()))
        tabs.addTab("Reliability", JBScrollPane(buildReliabilityList()))
        return JPanel(BorderLayout()).apply {
            border = BorderFactory.createEmptyBorder(8, 8, 8, 8)
            add(tabs, BorderLayout.CENTER)
        }
    }

    private fun buildSplitSummary(): JPanel {
        val splitter = OnePixelSplitter(false, 0.35f)
        splitter.firstComponent = JBScrollPane(buildStatusList())
        splitter.secondComponent = JBScrollPane(buildIncidentDetails())
        return JPanel(BorderLayout()).apply {
            border = BorderFactory.createEmptyBorder(8, 8, 8, 8)
            add(splitter, BorderLayout.CENTER)
        }
    }

    private fun buildTableMatrix(): JPanel {
        val headers = arrayOf("Subsystem", "Signal", "State", "Confidence")
        val rows = arrayOf(
            arrayOf("Ingestion", "Requests/min", "12.3k", "0.82"),
            arrayOf("Processing", "Lag time", "210 ms", "0.74"),
            arrayOf("Storage", "Hot retention", "48 h", "0.68"),
            arrayOf("Alerts", "Open incidents", "3", "0.61")
        )
        val model = DefaultTableModel(rows, headers)
        val table = JBTable(model).apply {
            tableHeader.reorderingAllowed = false
            preferredScrollableViewportSize = Dimension(420, 160)
        }
        return JPanel(BorderLayout()).apply {
            border = BorderFactory.createEmptyBorder(12, 12, 12, 12)
            add(JBScrollPane(table), BorderLayout.CENTER)
        }
    }

    private fun buildTreeTimeline(): JPanel {
        val root = DefaultMutableTreeNode("Last 24h")
        val ingestion = DefaultMutableTreeNode("Ingestion stable")
        val processing = DefaultMutableTreeNode("Processing spikes")
        processing.add(DefaultMutableTreeNode("08:15 UTC – Backlog cleared"))
        processing.add(DefaultMutableTreeNode("11:42 UTC – Alert acknowledged"))
        val storage = DefaultMutableTreeNode("Storage degradation")
        storage.add(DefaultMutableTreeNode("S3 retry increase"))
        val alerts = DefaultMutableTreeNode("Alert review")
        alerts.add(DefaultMutableTreeNode("Noise budget exceeded"))
        alerts.add(DefaultMutableTreeNode("Follow-up runbook"))
        root.add(ingestion)
        root.add(processing)
        root.add(storage)
        root.add(alerts)
        val tree = SimpleTree(DefaultTreeModel(root))
        tree.isRootVisible = true
        return JPanel(BorderLayout()).apply {
            border = BorderFactory.createEmptyBorder(8, 8, 8, 8)
            add(JBScrollPane(tree), BorderLayout.CENTER)
        }
    }

    private fun createCard(title: String, state: String, color: Color): JPanel {
        return JPanel(BorderLayout()).apply {
            border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 1, true),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
            )
            val titleLabel = JBLabel(title, SwingConstants.LEFT).apply {
                foreground = color
            }
            val stateLabel = JBLabel(state, SwingConstants.RIGHT).apply {
                font = font.deriveFont(font.size2D + 2f)
            }
            add(titleLabel, BorderLayout.NORTH)
            add(stateLabel, BorderLayout.SOUTH)
        }
    }

    private fun buildStatusList(): JBList<String> = JBList(
        listOf(
            "Collectors healthy",
            "5xx rate steady",
            "Stream lag 210 ms",
            "ClickHouse at 68% capacity"
        )
    )

    private fun buildIncidentList(): JBList<String> = JBList(
        listOf(
            "SLO breach – API latency",
            "Escalation pending – Alerts",
            "Resolved – Billing ingestion"
        )
    )

    private fun buildReliabilityList(): JBList<String> = JBList(
        listOf(
            "Confidence Intake 0.8",
            "Confidence Processing 0.75",
            "Confidence Visualization 0.7"
        )
    )

    private fun buildIncidentDetails(): JPanel {
        val panel = JPanel(BorderLayout())
        val header = JBLabel("Selected incident", SwingConstants.LEFT)
        val body = JPanel().apply {
            border = BorderFactory.createEmptyBorder(8, 8, 8, 8)
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            add(JBLabel("08:15 UTC – Spike in processing backlog"))
            add(Box.createVerticalStrut(4))
            add(JBLabel("Action: scaled Flink workers"))
            add(Box.createVerticalStrut(4))
            add(JBLabel("Confidence restored to 0.74"))
        }
        panel.add(header, BorderLayout.NORTH)
        panel.add(body, BorderLayout.CENTER)
        return panel
    }
}
