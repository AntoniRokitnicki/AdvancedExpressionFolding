package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.action.UpdateFoldedTextColorsAction
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.application.options.CodeStyle
import com.intellij.application.options.editor.EditorOptionsProvider
import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.ide.HelpTooltip
import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.encoding.EncodingProjectManager
import com.intellij.ui.JBColor
import com.intellij.ui.components.ActionLink
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTabbedPane
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.table.JBTable
import java.awt.Color.decode
import java.awt.Dimension
import java.awt.FlowLayout
import java.net.URI
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.table.DefaultTableModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale
import kotlin.reflect.KMutableProperty0

class SettingsConfigurable : EditorOptionsProvider, CheckboxesProvider() {
    private val state = AdvancedExpressionFoldingSettings.getInstance().state
    private lateinit var panel: DialogPanel
    private val allExampleFiles = mutableSetOf<ExampleFile>()
    private val pendingChanges = mutableMapOf<KMutableProperty0<Boolean>, Boolean>()
    private val propertyToCheckbox = mutableMapOf<KMutableProperty0<Boolean>, JBCheckBox>()
    private lateinit var telemetryPanel: DialogPanel
    private lateinit var telemetryTableModel: DefaultTableModel
    private lateinit var telemetryTable: JBTable
    private lateinit var telemetryTotalLabel: JBLabel
    private lateinit var telemetryLastUpdatedLabel: JBLabel
    private lateinit var telemetryInfoLabel: JBLabel
    private lateinit var telemetryClearButton: JButton
    private lateinit var telemetryCheckbox: JBCheckBox
    private val telemetryDateTimeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault())

    override fun getId() = "advanced.expression.folding"

    override fun getDisplayName() = "Advanced Expression Folding 2"

    override fun getHelpTopic() = null

    private fun createExamplePanel(examples: Map<ExampleFile, Description?>? = null, docLink: UrlSuffix? = null): JPanel {
        val panel = JPanel(FlowLayout(FlowLayout.LEFT))

        examples?.forEach { (file, desc) ->
            val suffix = desc?.let { " $it" } ?: ""
            val description = "example$suffix"

            val actionLink = ActionLink(description) {
                val project = selectedProject()
                val sourceRoot = firstSourceRoot(project)

                WriteCommandAction.runWriteCommandAction(project) {
                    val directory = sourceRoot.getOrCreatePackageDir()
                    createFile(directory, file, project)?.open(project)
                }
            }
            actionLink.setIcon(AllIcons.Actions.CheckOut, true)
            HelpTooltip().setDescription("WARNING: Clicking this button will checkout $file into your current project")
                .installOn(actionLink)
            panel.add(actionLink)

            allExampleFiles.add(file)
        }

        docLink?.let {
            val actionLink = ActionLink("doc") {
                BrowserUtil.browse(URI(docLink))
            }
            actionLink.setExternalLinkIcon()
            panel.add(actionLink)
        }

        return panel
    }

    private fun createDownloadExamplesLink(): ActionLink {
        val actionLink = ActionLink("Checkout Examples to Current Project") {
            val project = selectedProject()
            val sourceRoot = firstSourceRoot(project)

            WriteCommandAction.runWriteCommandAction(project) {
                val directory = sourceRoot.getOrCreatePackageDir()
                allExampleFiles.forEach {
                    createFile(directory, it, project)
                }
            }
        }
        actionLink.setIcon(AllIcons.Actions.CheckOut, true)
        HelpTooltip().setDescription("WARNING: Clicking this button will checkout examples into your current project")
            .installOn(actionLink)
        return actionLink
    }

    private fun createOptionsPanel(): DialogPanel = panel {
        row {
            val button =
                JButton("Apply folded color: ${if (!JBColor.isBright()) "soft blue" else "dark navy"}")
            button.foreground = if (!JBColor.isBright()) decode("#7ca0bb") else decode("#000091")
            button.addActionListener {
                UpdateFoldedTextColorsAction.changeFoldingColors()
            }
            cell(button)
        }
        row {
            cell(createDownloadExamplesLink())
        }
        initialize(state)
    }

    private fun createTelemetryPanel(): DialogPanel {
        telemetryTableModel = object : DefaultTableModel(arrayOf("Rule", "Fold regions"), 0) {
            override fun isCellEditable(row: Int, column: Int) = false
            override fun getColumnClass(columnIndex: Int): Class<*> =
                if (columnIndex == 1) java.lang.Long::class.java else String::class.java
        }
        telemetryTable = JBTable(telemetryTableModel).apply {
            emptyText.text = "No telemetry data collected yet."
            setShowGrid(false)
            tableHeader.reorderingAllowed = false
            autoCreateRowSorter = true
            preferredScrollableViewportSize = Dimension(420, 220)
        }
        telemetryTotalLabel = JBLabel()
        telemetryLastUpdatedLabel = JBLabel()
        telemetryInfoLabel = JBLabel()
        telemetryClearButton = JButton("Clear collected data").apply {
            addActionListener {
                AdvancedExpressionFoldingSettings.getInstance().clearTelemetry()
                refreshTelemetryView()
            }
        }
        val telemetryProperty = state::telemetryEnabled
        telemetryCheckbox = JBCheckBox("Enable telemetry collection").apply {
            isSelected = telemetryProperty.get()
            addActionListener {
                pendingChanges[telemetryProperty] = isSelected
                updateTelemetryStatus(isSelected)
            }
        }
        propertyToCheckbox[telemetryProperty] = telemetryCheckbox

        return panel {
            row {
                cell(JBLabel("Share anonymous usage metrics to help improve folding heuristics."))
            }
            row {
                cell(telemetryCheckbox)
            }
            row {
                cell(telemetryInfoLabel)
            }
            row {
                cell(JBScrollPane(telemetryTable)).resizableColumn()
            }.resizableRow()
            row {
                cell(telemetryTotalLabel)
            }
            row {
                cell(telemetryLastUpdatedLabel)
            }
            row {
                cell(telemetryClearButton)
            }
        }
    }

    private fun refreshTelemetryView() {
        if (!this::telemetryTableModel.isInitialized) {
            return
        }
        val snapshot = AdvancedExpressionFoldingSettings.getInstance().telemetrySnapshot()
        telemetryTableModel.rowCount = 0
        snapshot.perRule.entries
            .sortedByDescending { it.value }
            .forEach { (rule, count) ->
                telemetryTableModel.addRow(arrayOf<Any>(rule, count))
            }
        telemetryTable.emptyText.text = if (snapshot.perRule.isEmpty()) {
            "No telemetry data collected yet."
        } else {
            ""
        }
        telemetryTotalLabel.text = "Total fold regions recorded: ${snapshot.totalFoldRegions}"
        telemetryLastUpdatedLabel.text = if (snapshot.lastUpdatedTimestamp == 0L) {
            "Last updated: —"
        } else {
            "Last updated: ${telemetryDateTimeFormatter.format(Instant.ofEpochMilli(snapshot.lastUpdatedTimestamp))}"
        }
        telemetryClearButton.isEnabled = snapshot.totalFoldRegions > 0
        if (this::telemetryCheckbox.isInitialized) {
            updateTelemetryStatus(telemetryCheckbox.isSelected)
        }
    }

    private fun updateTelemetryStatus(isEnabled: Boolean) {
        if (!this::telemetryInfoLabel.isInitialized) {
            return
        }
        telemetryInfoLabel.text = if (isEnabled) {
            "<html>Telemetry is enabled. Data is stored locally until you clear it.</html>"
        } else {
            "<html>Telemetry is disabled. Enable it to start collecting metrics.<br>Existing data remains stored locally.</html>"
        }
    }

    override fun createComponent(): JComponent {
        val optionsPanel = createOptionsPanel()
        val metricsPanel = createTelemetryPanel()
        panel = optionsPanel
        telemetryPanel = metricsPanel
        val tabbedPane = JBTabbedPane()
        tabbedPane.addTab("Folding", optionsPanel)
        tabbedPane.addTab("Telemetry", metricsPanel)
        refreshTelemetryView()
        return tabbedPane
    }

    override fun isModified(): Boolean {
        val optionsModified = if (this::panel.isInitialized) panel.isModified() else false
        val metricsModified = if (this::telemetryPanel.isInitialized) telemetryPanel.isModified() else false
        return optionsModified || metricsModified || pendingChanges.isNotEmpty()
    }

    override fun apply() {
        pendingChanges.forEach { (property, value) ->
            property.set(value)
        }
        pendingChanges.clear()

        if (this::panel.isInitialized) {
            panel.apply()
        }
        if (this::telemetryPanel.isInitialized) {
            telemetryPanel.apply()
        }
        refreshTelemetryView()
    }

    override fun reset() {
        pendingChanges.clear()
        propertyToCheckbox.forEach { (property, checkbox) ->
            checkbox.isSelected = property.get()
        }
        if (this::telemetryCheckbox.isInitialized) {
            updateTelemetryStatus(telemetryCheckbox.isSelected)
        }
        refreshTelemetryView()
    }

    private fun firstSourceRoot(project: Project) =
        ProjectRootManager.getInstance(project).contentSourceRoots.firstOrNull() ?: TODO("No sourceRoot found")

    private fun selectedProject(): Project = ProjectUtil.getActiveProject() ?: TODO("No project is opened")

    private fun createFile(
        directory: VirtualFile,
        file: ExampleFile,
        project: Project
    ): VirtualFile? {
        val projectEncoding = EncodingProjectManager.getInstance(project).defaultCharset
        val lineSeparator = CodeStyle.getDefaultSettings().lineSeparator
        return javaClass.classLoader.getResource("$EXAMPLE_DIR/$file")
            ?.readText()
            ?.replace("\n", lineSeparator)?.let { fileContent ->
                val newFile = directory.createChildData(null, file)
                newFile.setBinaryContent(fileContent.toByteArray(charset = projectEncoding))
                newFile
            }
    }

    private fun VirtualFile.getOrCreatePackageDir(): VirtualFile {
        val directory = this.findChild(EXAMPLE_DIR)?.takeIf {
            it.exists()
        } ?: this.createChildDirectory(null, EXAMPLE_DIR)
        return directory
    }

    private fun VirtualFile.open(project: Project) {
        val fileEditorManager = FileEditorManager.getInstance(project)
        fileEditorManager.openFile(this, true)
    }

    companion object {
        private const val EXAMPLE_DIR = "data"
    }
    
    @CheckboxDsl
    override fun Panel.registerCheckbox(
        property: KMutableProperty0<Boolean>,
        title: String,
        block: (CheckboxBuilder.() -> Unit)?
    ) {
        val builder = CheckboxBuilder()
        block?.invoke(builder)
        
        val checkbox = JBCheckBox(title)
        checkbox.isSelected = property.get()
        checkbox.addActionListener {
            pendingChanges[property] = checkbox.isSelected
        }
        propertyToCheckbox[property] = checkbox
        
        row {
            cell(checkbox)
        }
        
        val definition = builder.build(property, title)
        if (definition.exampleLinkMap != null || definition.docLink != null) {
            row {
                cell(createExamplePanel(definition.exampleLinkMap, definition.docLink))
            }
        }

    }
}
