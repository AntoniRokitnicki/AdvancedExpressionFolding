package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.action.UpdateFoldedTextColorsAction
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.application.options.CodeStyle
import com.intellij.application.options.editor.EditorOptionsProvider
import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.ide.HelpTooltip
import com.intellij.ide.impl.ProjectUtil
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
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
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import java.awt.Color.decode
import java.awt.FlowLayout
import java.net.URI
import javax.swing.JButton
import javax.swing.JPanel
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties

class SettingsConfigurable : EditorOptionsProvider, CheckboxesProvider() {
    private val state = AdvancedExpressionFoldingSettings.getInstance().state
    private lateinit var panel: DialogPanel
    private val allExampleFiles = mutableSetOf<ExampleFile>()
    private val pendingChanges = mutableMapOf<KMutableProperty0<Boolean>, Boolean>()
    private val propertyToCheckbox = mutableMapOf<KMutableProperty0<Boolean>, JBCheckBox>()
    private val stateBooleanProperties: Map<String, KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>> =
        AdvancedExpressionFoldingSettings.State::class.memberProperties
            .filterIsInstance<KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>>()
            .associateBy { it.name }
    private var bulkUpdateInProgress = false

    override fun getId() = "advanced.expression.folding"

    override fun getDisplayName() = "Advanced Expression Folding 2"

    override fun getHelpTopic() = null

    internal fun createExamplePanel(examples: Map<ExampleFile, Description?>? = null, docLink: UrlSuffix? = null): JPanel {
        val panel = JPanel(FlowLayout(FlowLayout.LEFT))

        examples?.forEach { (file, desc) ->
            val suffix = desc?.let { " $it" } ?: ""
            val description = "example$suffix"

            val actionLink = ActionLink(description) {
                val project = selectedProject() ?: return@ActionLink
                val sourceRoot = firstSourceRoot(project) ?: return@ActionLink

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

    internal fun createDownloadExamplesLink(): ActionLink {
        val actionLink = ActionLink("Checkout Examples to Current Project") {
            val project = selectedProject() ?: return@ActionLink
            val sourceRoot = firstSourceRoot(project) ?: return@ActionLink

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
        row {
            val enableAllButton = JButton("Enable all")
            enableAllButton.addActionListener {
                applyBulkChange { enableAll() }
            }
            cell(enableAllButton)

            val disableAllButton = JButton("Disable all")
            disableAllButton.addActionListener {
                applyBulkChange { disableAll() }
            }
            cell(disableAllButton)
        }
        initialize(state)
    }.also {
        panel = it
    }

    override fun isModified(): Boolean {
        return panel.isModified() || pendingChanges.isNotEmpty()
    }

    override fun apply() {
        pendingChanges.forEach { (property, value) ->
            property.set(value)
        }
        pendingChanges.clear()
        
        panel.apply()
    }

    override fun reset() {
        pendingChanges.clear()
        propertyToCheckbox.forEach { (property, checkbox) ->
            checkbox.isSelected = property.get()
        }
    }

    private fun firstSourceRoot(project: Project): VirtualFile? {
        val sourceRoot = ProjectRootManager.getInstance(project).contentSourceRoots.firstOrNull()
        if (sourceRoot == null) {
            notifyWarning("No source roots found in project \"${project.name}\".", project)
        }
        return sourceRoot
    }

    private fun selectedProject(): Project? {
        val project = ProjectUtil.getActiveProject()
        if (project == null) {
            notifyWarning("No open project found. Please open a project before checking out examples.")
        }
        return project
    }

    private fun notifyWarning(message: String, project: Project? = null) {
        NotificationGroupManager.getInstance()
            .getNotificationGroup(NOTIFICATION_GROUP_ID)
            .createNotification(message, NotificationType.WARNING)
            .notify(project)
    }

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
        private const val NOTIFICATION_GROUP_ID = "Advanced Expression Folding"
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
            if (bulkUpdateInProgress) {
                return@addActionListener
            }

            val value = checkbox.isSelected
            if (property.get() == value) {
                pendingChanges.remove(property)
            } else {
                pendingChanges[property] = value
            }
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

    private fun applyBulkChange(action: AdvancedExpressionFoldingSettings.() -> Unit) {
        val currentState = AdvancedExpressionFoldingSettings.getInstance().state.copy()
        val temporarySettings = AdvancedExpressionFoldingSettings()
        temporarySettings.loadState(currentState)
        temporarySettings.action()
        val updatedState = temporarySettings.state

        bulkUpdateInProgress = true
        try {
            propertyToCheckbox.forEach { (property, checkbox) ->
                val stateProperty = stateBooleanProperties[property.name] ?: return@forEach
                val newValue = stateProperty.get(updatedState)
                checkbox.isSelected = newValue
                if (property.get() == newValue) {
                    pendingChanges.remove(property)
                } else {
                    pendingChanges[property] = newValue
                }
            }
        } finally {
            bulkUpdateInProgress = false
        }
    }
}
