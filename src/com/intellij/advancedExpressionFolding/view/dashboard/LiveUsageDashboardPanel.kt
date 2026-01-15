package com.intellij.advancedExpressionFolding.view.dashboard

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import javax.swing.DefaultListModel
import javax.swing.JPanel

class LiveUsageDashboardPanel(
    private val service: LiveUsageDashboardService,
) : JPanel(BorderLayout()) {

    private val summaryLabel = JBLabel("No usages recorded yet")
    private val historyModel = DefaultListModel<String>()
    private val historyList = JBList(historyModel).apply {
        emptyText.text = "Usage history will appear here"
    }

    private val listener: (LiveUsageDashboardState) -> Unit = { state ->
        summaryLabel.text = buildSummary(state)
        updateHistory(state.history)
    }

    init {
        border = JBUI.Borders.empty(8)
        add(summaryLabel, BorderLayout.NORTH)
        add(JBScrollPane(historyList), BorderLayout.CENTER)
    }

    private fun buildSummary(state: LiveUsageDashboardState): String =
        if (state.totalUsages == 0) {
            "No usages recorded yet"
        } else {
            "Usages: ${state.totalUsages} • Files: ${state.distinctFiles}"
        }

    private fun updateHistory(history: List<String>) {
        historyModel.removeAllElements()
        history.forEach(historyModel::addElement)
    }

    override fun addNotify() {
        super.addNotify()
        service.addListener(listener)
    }

    override fun removeNotify() {
        service.removeListener(listener)
        super.removeNotify()
    }
}
