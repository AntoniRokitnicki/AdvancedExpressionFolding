package com.intellij.advancedExpressionFolding.discovery

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.ListTableModel
import com.intellij.util.ui.UIUtil
import com.intellij.util.ui.ColumnInfo
import com.intellij.ui.JBSplitter
import com.intellij.openapi.Disposable
import java.awt.BorderLayout
import java.util.Locale
import javax.swing.BorderFactory
import javax.swing.JPanel
import javax.swing.ListSelectionModel

internal class RuleDiscoveryPanel(project: Project) : JPanel(BorderLayout()), Disposable {

    private val tableModel = ListTableModel<RuleRow>(
        RuleColumn("ID"),
        RuleColumn("Language"),
        SupportColumn(),
        PrecisionColumn(),
        RecallColumn()
    )
    private val table = JBTable(tableModel)
    private val detailsArea = JBTextArea()
    private val statsLabel = JBLabel()
    private var currentReport: RuleDiscoveryReport? = null

    init {
        border = BorderFactory.createEmptyBorder(8, 8, 8, 8)
        table.selectionModel.selectionMode = ListSelectionModel.SINGLE_SELECTION
        table.setShowGrid(false)
        table.emptyText.text = "No rule candidates yet"
        table.selectionModel.addListSelectionListener {
            val index = table.selectedRow
            val rule = if (index in 0 until tableModel.rowCount) {
                currentReport?.rules?.getOrNull(index)
            } else null
            displayRule(rule)
        }

        detailsArea.isEditable = false
        detailsArea.background = UIUtil.getPanelBackground()
        detailsArea.font = UIUtil.getLabelFont()

        val splitter = JBSplitter(true, 0.45f)
        splitter.firstComponent = JBScrollPane(table)

        val detailPanel = JPanel(BorderLayout())
        detailPanel.add(statsLabel, BorderLayout.NORTH)
        detailPanel.add(JBScrollPane(detailsArea), BorderLayout.CENTER)
        splitter.secondComponent = detailPanel

        add(splitter, BorderLayout.CENTER)
    }

    fun update(report: RuleDiscoveryReport?) {
        currentReport = report
        ApplicationManager.getApplication().invokeLater {
            val items = report?.rules?.map { RuleRow(it) } ?: emptyList()
            tableModel.setItems(items)
            tableModel.fireTableDataChanged()
            statsLabel.text = report?.let {
                "${it.rules.size} rules · occurrences=${it.stats.totalOccurrences} · noise=${String.format(Locale.US, "%.2f", it.stats.overallNoiseRate)}"
            } ?: ""
            if (tableModel.rowCount > 0) {
                table.setRowSelectionInterval(0, 0)
            } else {
                detailsArea.text = ""
            }
        }
    }

    private fun displayRule(rule: RuleCandidate?) {
        if (rule == null) {
            detailsArea.text = ""
            return
        }
        val builder = StringBuilder()
        builder.appendLine("Description: ${rule.predicateDescription}")
        builder.appendLine("Psi checks:")
        rule.psiChecks.forEach { builder.appendLine("  - $it") }
        builder.appendLine()
        builder.appendLine("Predicate:")
        rule.predicateSnippet.lines().forEach { builder.appendLine("  $it") }
        builder.appendLine()
        builder.appendLine("Metrics:")
        builder.appendLine("  Support: ${rule.metrics.support}")
        builder.appendLine("  Precision: ${String.format(Locale.US, "%.2f", rule.metrics.precision)}")
        builder.appendLine("  Recall: ${String.format(Locale.US, "%.2f", rule.metrics.recall)}")
        builder.appendLine("  Noise: ${String.format(Locale.US, "%.2f", rule.metrics.noiseRate)}")
        builder.appendLine()
        if (rule.examples.isNotEmpty()) {
            builder.appendLine("Examples:")
            rule.examples.forEach { example ->
                builder.appendLine("  - ${example.fileHash} @ ${example.range}")
                example.code.lines().forEach { builder.appendLine("      $it") }
            }
        }
        detailsArea.text = builder.toString()
        detailsArea.caretPosition = 0
    }

    override fun dispose() {
    }

    private class RuleColumn(@Suppress("unused") private val name: String) : ColumnInfo<RuleRow, String>(name) {
        override fun valueOf(item: RuleRow): String = item.rule.id
    }

    private class SupportColumn : ColumnInfo<RuleRow, Int>("Support") {
        override fun valueOf(item: RuleRow): Int = item.rule.metrics.support
    }

    private class PrecisionColumn : ColumnInfo<RuleRow, String>("Precision") {
        override fun valueOf(item: RuleRow): String = String.format(Locale.US, "%.2f", item.rule.metrics.precision)
    }

    private class RecallColumn : ColumnInfo<RuleRow, String>("Recall") {
        override fun valueOf(item: RuleRow): String = String.format(Locale.US, "%.2f", item.rule.metrics.recall)
    }

    private data class RuleRow(val rule: RuleCandidate)
}
