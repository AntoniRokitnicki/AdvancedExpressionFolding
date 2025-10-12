package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.telemetry.RuleUsageMetric
import com.intellij.advancedExpressionFolding.telemetry.UsageTelemetryService
import com.intellij.ui.JBColor
import com.intellij.ui.components.ActionLink
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.JBEmptyBorder
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Insets
import java.awt.RenderingHints
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.table.DefaultTableModel
import kotlin.math.max
import kotlin.math.roundToInt

class UsageTelemetryPanel : JPanel(BorderLayout()) {

    private val chart = UsageBarChart()
    private val infoLabel = JBLabel()
    private val refreshLink = ActionLink("Refresh") { refresh() }
    private val resetLink = ActionLink("Reset telemetry") {
        UsageTelemetryService.resetTelemetry()
        refresh()
    }
    private val tableModel = object : DefaultTableModel(arrayOf("Rule", "Activations", "Last used"), 0) {
        override fun isCellEditable(row: Int, column: Int): Boolean = false
    }
    private val table = JBTable(tableModel).apply {
        tableHeader.reorderingAllowed = false
        setShowGrid(false)
        columnModel.getColumn(1).preferredWidth = 90
        columnModel.getColumn(2).preferredWidth = 140
        autoResizeMode = JBTable.AUTO_RESIZE_LAST_COLUMN
    }
    private val tableScroll = JBScrollPane(table).apply {
        preferredSize = Dimension(10, 140)
        border = JBEmptyBorder(0)
    }
    private var telemetryEnabled = false

    init {
        border = JBEmptyBorder(12)
        val header = JPanel(BorderLayout()).apply {
            border = JBEmptyBorder(0, 0, 8, 0)
            add(infoLabel, BorderLayout.CENTER)
            val actions = JPanel(BorderLayout()).apply {
                border = JBEmptyBorder(0, 8, 0, 0)
                add(refreshLink, BorderLayout.NORTH)
                add(resetLink, BorderLayout.SOUTH)
            }
            add(actions, BorderLayout.EAST)
        }
        add(header, BorderLayout.NORTH)

        val center = JPanel(BorderLayout(0, 8)).apply {
            add(chart, BorderLayout.NORTH)
            add(tableScroll, BorderLayout.CENTER)
        }
        add(center, BorderLayout.CENTER)

        updateDisabledState()
    }

    fun updateEnabled(enabled: Boolean) {
        telemetryEnabled = enabled
        refresh()
    }

    fun refresh() {
        if (!telemetryEnabled) {
            updateDisabledState()
            return
        }

        refreshLink.isEnabled = true
        val snapshot = UsageTelemetryService.getInstance()?.getMetricsSnapshot().orEmpty()
        if (snapshot.isEmpty()) {
            infoLabel.text = "Fold code to populate usage telemetry (stored locally)."
            chart.update(emptyList())
            tableModel.rowCount = 0
            resetLink.isEnabled = false
            return
        }

        val entries = snapshot.entries.map { (ruleId, metric) ->
            UsageEntry(ruleId, metric)
        }.sortedByDescending { it.metric.count }

        infoLabel.text = "Top ${minOf(entries.size, MAX_CHART_BARS)} folding rules by activations."
        chart.update(entries.take(MAX_CHART_BARS))

        tableModel.rowCount = 0
        entries.forEach { entry ->
            tableModel.addRow(arrayOf(entry.displayName, entry.metric.count, entry.relativeLastUsed()))
        }
        resetLink.isEnabled = true
    }

    private fun updateDisabledState() {
        infoLabel.text = "Enable telemetry to visualize folding usage trends."
        chart.update(emptyList())
        tableModel.rowCount = 0
        refreshLink.isEnabled = telemetryEnabled
        resetLink.isEnabled = false
    }

    private data class UsageEntry(
        val ruleId: String,
        val metric: RuleUsageMetric,
    ) {
        val displayName: String = toDisplayName(ruleId)
        val shortName: String = shorten(displayName)

        fun relativeLastUsed(): String {
            if (metric.lastUsedEpochMs == 0L) {
                return "—"
            }
            val now = Instant.now()
            val last = Instant.ofEpochMilli(metric.lastUsedEpochMs)
            if (last.isAfter(now)) {
                return displayFormatter.format(last)
            }
            val duration = Duration.between(last, now)
            val days = duration.toDays()
            val hours = duration.toHours()
            val minutes = duration.toMinutes()
            return when {
                days > 0 -> "$days d ago"
                hours > 0 -> "$hours h ago"
                minutes > 0 -> "$minutes m ago"
                else -> "just now"
            }
        }

        companion object {
            private val displayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault())
            private fun toDisplayName(ruleId: String): String {
                val simple = ruleId.substringAfterLast('.')
                return simple.replace(Regex("(?<!^)(?=[A-Z])"), " ").trim()
            }

            private fun shorten(name: String): String {
                val trimmed = name.take(MAX_LABEL_LENGTH)
                return if (trimmed.length < name.length) "$trimmed…" else trimmed
            }
        }
    }

    private class UsageBarChart : JComponent() {
        private var entries: List<UsageEntry> = emptyList()

        init {
            preferredSize = Dimension(420, 220)
        }

        fun update(entries: List<UsageEntry>) {
            this.entries = entries
            repaint()
        }

        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            val g2 = g as Graphics2D
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

            val insets = Insets(16, 36, 36, 16)
            val originX = insets.left
            val originY = height - insets.bottom
            val availableWidth = width - insets.left - insets.right
            val availableHeight = height - insets.top - insets.bottom

            g2.color = JBColor.border()
            g2.drawLine(originX, originY, originX + availableWidth, originY)
            g2.drawLine(originX, originY, originX, originY - availableHeight)

            if (entries.isEmpty()) {
                val message = "No telemetry samples yet."
                val metrics = g2.fontMetrics
                val x = originX + (availableWidth - metrics.stringWidth(message)) / 2
                val y = originY - availableHeight / 2
                g2.color = JBColor.GRAY
                g2.drawString(message, max(originX, x), max(insets.top + metrics.ascent, y))
                return
            }

            val maxCount = entries.maxOf { it.metric.count }
            if (maxCount <= 0) {
                return
            }

            val barWidth = max(12, availableWidth / (entries.size * 2))
            entries.forEachIndexed { index, entry ->
                val ratio = entry.metric.count.toDouble() / maxCount
                val barHeight = (availableHeight * ratio).roundToInt()
                val x = originX + (index * 2 + 1) * barWidth
                val y = originY - barHeight

                g2.color = BAR_COLORS[index % BAR_COLORS.size]
                g2.fillRoundRect(x, y, barWidth, barHeight, 8, 8)

                g2.color = JBColor.foreground()
                val label = entry.shortName
                val metrics = g2.fontMetrics
                val labelX = x + (barWidth - metrics.stringWidth(label)) / 2
                val labelY = originY + metrics.ascent + 4
                g2.drawString(label, max(originX, labelX), labelY)
            }
        }
    }

    companion object {
        private const val MAX_CHART_BARS = 8
        private const val MAX_LABEL_LENGTH = 14
        private val BAR_COLORS = listOf(
            JBColor(Color(0x4C78A8), Color(0x4C78A8)),
            JBColor(Color(0xF58518), Color(0xF58518)),
            JBColor(Color(0xE45756), Color(0xE45756)),
            JBColor(Color(0x72B7B2), Color(0x72B7B2)),
            JBColor(Color(0x54A24B), Color(0x54A24B)),
            JBColor(Color(0xEECA3B), Color(0xEECA3B)),
        )
    }
}
