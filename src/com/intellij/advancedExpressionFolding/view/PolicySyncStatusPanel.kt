package com.intellij.advancedExpressionFolding.view

import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.JBUI
import javax.swing.BoxLayout
import javax.swing.JProgressBar
import javax.swing.JPanel

class PolicySyncStatusPanel : JPanel() {

    private val title = JBLabel("Policy Synchronization")
    private val statusLabel = JBLabel("Idle")
    private val progressBar = JProgressBar(0, 100)

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
        border = JBUI.Borders.empty(8)
        add(title)
        add(statusLabel)
        add(progressBar)
    }

    fun updateStatus(message: String, progress: Int) {
        statusLabel.text = message
        progressBar.value = progress.coerceIn(0, 100)
    }
}
