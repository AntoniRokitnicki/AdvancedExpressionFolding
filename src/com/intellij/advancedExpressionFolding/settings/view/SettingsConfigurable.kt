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
import com.intellij.ui.CollectionComboBoxModel
import com.intellij.ui.JBColor
import com.intellij.ui.SimpleListCellRenderer
import com.intellij.ui.components.ActionLink
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import com.intellij.util.ui.JBUI
import java.awt.Color.decode
import java.awt.FlowLayout
import java.net.URI
import javax.swing.JButton
import javax.swing.JPanel
import kotlin.reflect.KMutableProperty0

class SettingsConfigurable : EditorOptionsProvider, CheckboxesProvider() {
    private val settingsService = AdvancedExpressionFoldingSettings.getInstance()
    private val state = settingsService.state
    private var selectedPersonaId: String = state.activePersonaId
    private val personaModel = CollectionComboBoxModel<AdvancedExpressionFoldingSettings.PersonaProfileState>()
    private val personaTipLabel = JBLabel()
    private val conflictLabel = JBLabel().apply { foreground = JBColor.RED }
    private val diffPreview = JBTextArea(5, 60).apply {
        lineWrap = true
        wrapStyleWord = true
        isEditable = false
        border = JBUI.Borders.empty(4)
        text = "No persona changes pending."
    }
    private val auditArea = JBTextArea(6, 60).apply {
        lineWrap = true
        wrapStyleWord = true
        isEditable = false
        border = JBUI.Borders.empty(4)
        text = "No persona activity recorded yet."
    }
    private val personaExamplePanel = JPanel(FlowLayout(FlowLayout.LEFT, 0, 0)).apply {
        isOpaque = false
    }
    private val currentUser = System.getProperty("user.name") ?: "unknown"
    private lateinit var panel: DialogPanel
    private val allExampleFiles = mutableSetOf<ExampleFile>()
    private val pendingChanges = mutableMapOf<KMutableProperty0<Boolean>, Boolean>()
    private val propertyToCheckbox = mutableMapOf<KMutableProperty0<Boolean>, JBCheckBox>()

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

    override fun createComponent() = panel {
        row("Persona profile") {
            refreshPersonaModel()
            val renderer = SimpleListCellRenderer.create("", AdvancedExpressionFoldingSettings.PersonaProfileState::displayName)
            val combo = comboBox(personaModel, renderer).component
            combo.addActionListener { handlePersonaSelection() }
        }
        row {
            cell(personaTipLabel)
        }
        row {
            cell(JBScrollPane(diffPreview).apply { preferredSize = JBUI.size(420, 110) })
        }
        row {
            cell(conflictLabel)
        }
        row {
            label("Persona example kit:")
        }
        row {
            cell(personaExamplePanel)
        }
        row {
            label("Recent persona activity:")
        }
        row {
            cell(JBScrollPane(auditArea).apply { preferredSize = JBUI.size(420, 120) })
        }
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
    }.also {
        panel = it
        refreshPersonaMetadata()
        refreshDiffPreview()
        refreshConflictPreview()
        refreshAuditArea()
    }

    override fun isModified(): Boolean {
        return panel.isModified() || pendingChanges.isNotEmpty()
    }

    override fun apply() {
        val changeSet = pendingChanges.mapKeys { (property, _) -> property.name }
        pendingChanges.forEach { (property, value) ->
            property.set(value)
        }
        pendingChanges.clear()

        panel.apply()
        settingsService.commitPersonaChanges(selectedPersonaId, changeSet, currentUser)
        refreshPersonaModel()
        refreshPersonaMetadata()
        refreshDiffPreview()
        refreshConflictPreview()
        refreshAuditArea()
    }

    override fun reset() {
        pendingChanges.clear()
        propertyToCheckbox.forEach { (property, checkbox) ->
            checkbox.isSelected = property.get()
        }
        selectedPersonaId = state.activePersonaId
        refreshPersonaModel()
        settingsService.setActivePersona(selectedPersonaId, applyColorScheme = false)
        refreshPersonaMetadata()
        refreshDiffPreview()
        refreshConflictPreview()
        refreshAuditArea()
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

    private fun refreshPersonaModel() {
        val descriptors = settingsService.personaDescriptors()
        personaModel.replaceAll(descriptors.toMutableList())
        val selected = descriptors.firstOrNull { it.id == selectedPersonaId } ?: descriptors.firstOrNull()
        personaModel.selectedItem = selected
        selectedPersonaId = selected?.id ?: selectedPersonaId
    }

    private fun handlePersonaSelection() {
        val persona = selectedPersona() ?: return
        selectedPersonaId = persona.id
        pendingChanges.clear()
        settingsService.setActivePersona(selectedPersonaId, currentUser)
        refreshCheckboxesFromState()
        refreshPersonaMetadata()
        refreshDiffPreview()
        refreshConflictPreview()
        refreshAuditArea()
    }

    private fun refreshCheckboxesFromState() {
        propertyToCheckbox.forEach { (property, checkbox) ->
            checkbox.isSelected = property.get()
        }
    }

    private fun refreshPersonaMetadata() {
        val persona = selectedPersona()
        if (persona == null) {
            personaTipLabel.text = "No persona available."
            personaExamplePanel.removeAll()
            personaExamplePanel.add(JBLabel("No persona examples registered."))
        } else {
            personaTipLabel.text = persona.onboardingTip ?: "No onboarding tip for ${persona.displayName}."
            refreshPersonaExamples(persona)
        }
        personaExamplePanel.revalidate()
        personaExamplePanel.repaint()
    }

    private fun refreshPersonaExamples(persona: AdvancedExpressionFoldingSettings.PersonaProfileState?) {
        personaExamplePanel.removeAll()
        val exampleFile = persona?.exampleFile
        if (exampleFile != null) {
            val examplePanel = createExamplePanel(mapOf(exampleFile to "(${persona.displayName})"))
            personaExamplePanel.add(examplePanel)
        } else {
            personaExamplePanel.add(JBLabel("No persona examples registered."))
        }
    }

    private fun refreshDiffPreview() {
        val persona = selectedPersona()
        if (persona == null) {
            diffPreview.text = "No persona selected."
            return
        }
        val baseline = persona.propertySnapshot
        val previewState = baseline.toMutableMap()
        for ((property, value) in pendingChanges) {
            previewState[property.name] = value
        }
        val diffs = previewState.keys
            .mapNotNull { name ->
                val personaValue = baseline[name] as? Boolean ?: return@mapNotNull null
                val previewValue = previewState[name] as? Boolean ?: return@mapNotNull null
                if (personaValue != previewValue) name to (personaValue to previewValue) else null
            }
            .sortedBy { it.first }
        diffPreview.text = if (diffs.isEmpty()) {
            "No persona changes pending."
        } else {
            diffs.joinToString("\n") { (name, values) ->
                "${persona.displayName}: $name ${values.first} → ${values.second}"
            }
        }
        diffPreview.caretPosition = 0
    }

    private fun refreshConflictPreview() {
        val persona = selectedPersona()
        if (persona == null) {
            conflictLabel.text = ""
            conflictLabel.foreground = JBColor.GRAY
            return
        }
        val conflicts = settingsService.detectConflicts(persona.id, pendingChangesAsMap())
        if (conflicts.isEmpty()) {
            conflictLabel.text = "No conflicts detected."
            conflictLabel.foreground = JBColor.GRAY
        } else {
            conflictLabel.text = conflicts.joinToString("\n")
            conflictLabel.foreground = JBColor.RED
        }
    }

    private fun refreshAuditArea() {
        val entries = settingsService.auditTrail()
            .filter { it.personaId == selectedPersonaId }
            .takeLast(10)
        auditArea.text = if (entries.isEmpty()) {
            "No persona activity recorded yet."
        } else {
            entries.joinToString("\n") {
                "[${settingsService.formatTimestamp(it.timestampMillis)}] ${it.message}"
            }
        }
        auditArea.caretPosition = 0
    }

    private fun pendingChangesAsMap(): Map<String, Boolean> = pendingChanges.mapKeys { (property, _) -> property.name }

    private fun selectedPersona(): AdvancedExpressionFoldingSettings.PersonaProfileState? =
        personaModel.selectedItem as? AdvancedExpressionFoldingSettings.PersonaProfileState

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
            val selected = checkbox.isSelected
            if (selected == property.get()) {
                pendingChanges.remove(property)
            } else {
                pendingChanges[property] = selected
            }
            refreshDiffPreview()
            refreshConflictPreview()
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
