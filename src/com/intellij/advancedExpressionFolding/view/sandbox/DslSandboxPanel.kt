package com.intellij.advancedExpressionFolding.view.sandbox

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.CollectionListModel
import com.intellij.ui.ColoredListCellRenderer
import com.intellij.ui.JBSplitter
import com.intellij.ui.SimpleTextAttributes
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.ListSelectionModel

internal class DslSandboxPanel(project: Project) : SimpleToolWindowPanel(true, true) {

    private val engine = DslSandboxEngine()
    private val historyModel = CollectionListModel<DslSandboxEngine.StateFrame>()
    private val scriptArea = JBTextArea(initialScript).apply {
        lineWrap = false
        wrapStyleWord = false
        margin = JBUI.insets(4)
    }
    private val historyList = JBList(historyModel)
    private val statePreview = JBTextArea().apply {
        isEditable = false
        lineWrap = true
        wrapStyleWord = true
        emptyText.text = "Run the script to inspect state snapshots"
    }
    private val statusLabel = JBLabel("Idle")
    private val runButton = JButton("Run Script")
    private val rollbackButton = JButton("Rollback to Selection")

    private var frames: MutableList<DslSandboxEngine.StateFrame> = mutableListOf()

    init {
        scriptArea.preferredSize = Dimension(400, 240)
        historyList.cellRenderer = createHistoryRenderer()
        historyList.selectionMode = ListSelectionModel.SINGLE_SELECTION
        historyList.addListSelectionListener {
            val index = historyList.selectedIndex
            updateStatePreview(index)
            updateControls()
        }

        runButton.addActionListener { runScript() }
        rollbackButton.addActionListener { rollbackToSelection() }
        rollbackButton.isEnabled = false

        toolbar = createToolbarPanel()
        setContent(createContentPanel())

        runScript()
    }

    private fun createToolbarPanel(): JComponent {
        return JPanel(FlowLayout(FlowLayout.LEFT, 8, 4)).apply {
            add(runButton)
            add(rollbackButton)
            add(statusLabel)
        }
    }

    private fun createContentPanel(): JComponent {
        val scriptPanel = JPanel(BorderLayout()).apply {
            add(JBLabel("Scenario Script"), BorderLayout.NORTH)
            add(JBScrollPane(scriptArea), BorderLayout.CENTER)
        }

        val historyPanel = JPanel(BorderLayout()).apply {
            add(JBLabel("Execution Timeline"), BorderLayout.NORTH)
            add(JBScrollPane(historyList), BorderLayout.CENTER)
        }

        val previewPanel = JPanel(BorderLayout()).apply {
            add(JBLabel("State Snapshot"), BorderLayout.NORTH)
            add(JBScrollPane(statePreview), BorderLayout.CENTER)
        }

        val rightSplitter = JBSplitter(true, 0.45f)
        rightSplitter.firstComponent = historyPanel
        rightSplitter.secondComponent = previewPanel

        val mainSplitter = JBSplitter(false, 0.4f)
        mainSplitter.firstComponent = scriptPanel
        mainSplitter.secondComponent = rightSplitter

        return mainSplitter
    }

    private fun runScript() {
        val result = try {
            engine.execute(scriptArea.text)
        } catch (t: Throwable) {
            listOf(
                DslSandboxEngine.StateFrame(
                    index = 0,
                    command = "(initial)",
                    snapshot = emptyMap(),
                    message = "Error: ${t.message ?: t.javaClass.simpleName}",
                    isError = true
                )
            )
        }

        frames = result.toMutableList()
        historyModel.replaceAll(frames)
        historyList.selectedIndex = frames.lastIndex
        updateStatePreview(historyList.selectedIndex)
        statusLabel.text = frames.lastOrNull()?.message ?: "Idle"
        updateControls()
    }

    private fun rollbackToSelection() {
        val index = historyList.selectedIndex
        if (index <= 0 || index >= frames.size) {
            return
        }
        frames = frames.take(index + 1).toMutableList()
        historyModel.replaceAll(frames)
        historyList.selectedIndex = index
        statusLabel.text = "Rolled back to #$index"
        updateStatePreview(index)
        updateControls()
    }

    private fun updateStatePreview(index: Int) {
        val frame = frames.getOrNull(index)
        statePreview.text = when (frame) {
            null -> "No frame selected"
            else -> buildString {
                appendLine(formatState(frame.snapshot))
                appendLine()
                append(frame.message)
            }
        }
    }

    private fun formatState(state: Map<String, String>): String {
        if (state.isEmpty()) {
            return "{ }"
        }
        return buildString {
            appendLine("{")
            state.entries.forEachIndexed { idx, entry ->
                append("  ")
                append(entry.key)
                append(": \"")
                append(entry.value)
                append("\"")
                if (idx < state.size - 1) {
                    append(',')
                }
                appendLine()
            }
            append('}')
        }
    }

    private fun updateControls() {
        if (frames.isEmpty()) {
            rollbackButton.isEnabled = false
            return
        }
        val selection = historyList.selectedIndex
        rollbackButton.isEnabled = selection in 0 until frames.lastIndex
        val errorIndex = frames.indexOfLast { it.isError }
        if (errorIndex >= 0 && selection == frames.lastIndex) {
            statusLabel.text = frames[errorIndex].message
        }
    }

    private fun createHistoryRenderer(): ColoredListCellRenderer<DslSandboxEngine.StateFrame> {
        return object : ColoredListCellRenderer<DslSandboxEngine.StateFrame>() {
            override fun customizeCellRenderer(
                list: javax.swing.JList<out DslSandboxEngine.StateFrame>,
                value: DslSandboxEngine.StateFrame?,
                index: Int,
                selected: Boolean,
                hasFocus: Boolean
            ) {
                if (value == null) return
                val title = if (value.index == 0) "Initial state" else "#${value.index} ${value.command}"
                val attributes = if (value.isError) SimpleTextAttributes.ERROR_ATTRIBUTES else SimpleTextAttributes.REGULAR_ATTRIBUTES
                append(title, attributes)
                append("  ", SimpleTextAttributes.GRAYED_ATTRIBUTES)
                append(value.message, if (value.isError) SimpleTextAttributes.ERROR_ATTRIBUTES else SimpleTextAttributes.GRAYED_ATTRIBUTES)
            }
        }
    }

    companion object {
        private val initialScript = """
            # Prototype DSL scenario
            SET arena Eclipse
            SET energy 5
            INCR energy 3
            APPEND log Launch Sequence ->
            SNAPSHOT warm-up
            EMIT All systems green
            INCR energy -2
            DELETE arena
            HALT
        """.trimIndent()
    }
}
