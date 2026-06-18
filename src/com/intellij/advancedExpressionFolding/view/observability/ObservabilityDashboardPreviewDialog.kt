package com.intellij.advancedExpressionFolding.view.observability

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.Dimension
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Dialog that lets developers preview five IntelliJ UI variants for the observability dashboard.
 */
class ObservabilityDashboardPreviewDialog(project: Project) : DialogWrapper(project, true) {
    private val cards = JPanel(CardLayout())
    private val comboBox = JComboBox(ObservabilityDashboardVariant.entries.toTypedArray())

    init {
        title = "Observability Dashboard Variants"
        cards.preferredSize = Dimension(600, 360)
        initCards()
        initComboBox()
        init()
    }

    override fun createCenterPanel(): JComponent {
        return JPanel(BorderLayout()).apply {
            border = JBUI.Borders.empty(8)
            add(buildHeader(), BorderLayout.NORTH)
            add(cards, BorderLayout.CENTER)
        }
    }

    private fun buildHeader(): JPanel {
        return JPanel(BorderLayout()).apply {
            border = JBUI.Borders.emptyBottom(8)
            add(JBLabel("Variant"), BorderLayout.WEST)
            add(comboBox, BorderLayout.CENTER)
        }
    }

    private fun initCards() {
        ObservabilityDashboardVariant.entries.forEach { variant ->
            cards.add(variant.createComponent(), variant.name)
        }
        (cards.layout as CardLayout).show(cards, ObservabilityDashboardVariant.STATUS_CARDS.name)
    }

    private fun initComboBox() {
        comboBox.selectedItem = ObservabilityDashboardVariant.STATUS_CARDS
        comboBox.addActionListener {
            val selected = comboBox.selectedItem as? ObservabilityDashboardVariant ?: return@addActionListener
            (cards.layout as CardLayout).show(cards, selected.name)
        }
    }
}
