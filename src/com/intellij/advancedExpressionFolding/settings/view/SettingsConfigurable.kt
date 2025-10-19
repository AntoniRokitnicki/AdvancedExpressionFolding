package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.action.UpdateFoldedTextColorsAction
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.application.options.CodeStyle
import com.intellij.application.options.editor.EditorOptionsProvider
import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.ide.HelpTooltip
import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.Disposable
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.util.Disposer
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
import org.jetbrains.annotations.TestOnly

open class SettingsConfigurable : EditorOptionsProvider, CheckboxesProvider() {
    private val state = AdvancedExpressionFoldingSettings.getInstance().state
    private lateinit var panel: DialogPanel
    private val allExampleFiles = mutableSetOf<ExampleFile>()
    private val pendingChanges = mutableMapOf<KMutableProperty0<Boolean>, Boolean>()
    private val propertyToCheckbox = mutableMapOf<KMutableProperty0<Boolean>, JBCheckBox>()
    private lateinit var previewPanel: FoldingPreviewPanel
    private var panelDisposable: Disposable? = null
    private var pendingSnippet: String? = null
    private val stateBooleanProperties = AdvancedExpressionFoldingSettings.State::class.memberProperties
        .filterIsInstance<KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>>()
        .associateBy { it.name }

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

    override fun createComponent(): DialogPanel {
        panelDisposable?.let { Disposer.dispose(it) }
        previewPanel = FoldingPreviewPanel(
            state.copy().apply { memoryImprovement = false },
            state.previewSnippet,
            ::onSnippetChanged,
            project = currentProjectOrDefault()
        )
        panelDisposable = Disposer.newDisposable("AdvancedExpressionFolding.SettingsPreview")
        Disposer.register(panelDisposable!!, previewPanel)

        return panel {
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
                cell(previewPanel.component)
            }
            initialize(state)
        }.also {
            panel = it
            previewPanel.updatePreview(previewState())
        }
    }

    override fun isModified(): Boolean {
        return panel.isModified() || pendingChanges.isNotEmpty() || pendingSnippet != null
    }

    override fun apply() {
        pendingChanges.forEach { (property, value) ->
            property.set(value)
        }
        pendingChanges.clear()

        pendingSnippet?.let {
            state.previewSnippet = it
        }
        pendingSnippet = null

        panel.apply()
        previewPanel.updatePreview(previewState())
    }

    override fun reset() {
        pendingChanges.clear()
        propertyToCheckbox.forEach { (property, checkbox) ->
            checkbox.isSelected = property.get()
        }
        pendingSnippet = null
        previewPanel.setSnippet(state.previewSnippet, trackModification = false)
        previewPanel.updatePreview(state.copy().apply { memoryImprovement = false })
    }

    private fun onSnippetChanged(snippet: String) {
        pendingSnippet = snippet.takeIf { it != state.previewSnippet }
    }

    private fun previewState(): AdvancedExpressionFoldingSettings.State {
        val previewState = state.copy()
        pendingChanges.forEach { (property, value) ->
            stateBooleanProperties[property.name]?.setter?.call(previewState, value)
        }
        previewState.memoryImprovement = false
        return previewState
    }

    override fun disposeUIResources() {
        super.disposeUIResources()
        panelDisposable?.let { Disposer.dispose(it) }
        panelDisposable = null
    }

    @TestOnly
    internal fun previewTextForTests(): String = previewPanel.previewText

    @TestOnly
    internal fun previewDescriptorCountForTests(): Int = previewPanel.descriptorCountForTests()

    @TestOnly
    internal fun previewPlaceholdersForTests(): List<String?> = previewPanel.placeholdersForTests()

    private fun firstSourceRoot(project: Project) =
        ProjectRootManager.getInstance(project).contentSourceRoots.firstOrNull() ?: TODO("No sourceRoot found")

    private fun selectedProject(): Project = ProjectUtil.getActiveProject() ?: TODO("No project is opened")

    protected open fun currentProjectOrDefault(): Project = ProjectUtil.getActiveProject() ?: ProjectManager.getInstance().defaultProject

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
            val value = checkbox.isSelected
            if (value == property.get()) {
                pendingChanges.remove(property)
            } else {
                pendingChanges[property] = value
            }
            previewPanel.updatePreview(previewState())
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
