package com.intellij.advancedExpressionFolding.view

import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import java.awt.BorderLayout
import javax.swing.JPanel

class FoldingSummaryPanel(items: List<String>) : JPanel(BorderLayout()) {

    private val summaryLabel = JBLabel("Folding Summary")
    private val list = JBList(items)

    init {
        add(summaryLabel, BorderLayout.NORTH)
        add(JBScrollPane(list), BorderLayout.CENTER)
    }

    fun setItems(values: List<String>) {
        list.setListData(values.toTypedArray())
    }
}
