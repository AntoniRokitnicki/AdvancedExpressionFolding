package com.intellij.advancedExpressionFolding.view

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.dsl.builder.panel
import javax.swing.JComponent
import javax.swing.JList
import javax.swing.JScrollPane

class ContractSelectionDialog(
    project: Project,
    private val contracts: List<String>
) : DialogWrapper(project) {

    private val list = JList(contracts.toTypedArray())

    init {
        title = "Choose Contract"
        init()
    }

    override fun createCenterPanel(): JComponent {
        return panel {
            row { label("Available contracts") }
            row { cell(JScrollPane(list)).resizableColumn() }
        }
    }

    fun selectedContract(): String? {
        return contracts.getOrNull(list.selectedIndex)
    }
}
