package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.action.UpdateFoldedTextColorsAction
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.application.options.editor.EditorOptionsProvider
import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.ide.HelpTooltip
import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.application.ApplicationManager
import com.intellij.application.options.CodeStyle
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.encoding.EncodingProjectManager
import com.intellij.ui.JBColor
import com.intellij.ui.components.ActionLink
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import java.awt.Color.decode
import java.awt.FlowLayout
import java.io.File
import java.net.URI
import javax.swing.JButton
import javax.swing.JPanel
import kotlin.reflect.KMutableProperty0

class SettingsConfigurable : EditorOptionsProvider, CheckboxesProvider() {
    private val state = AdvancedExpressionFoldingSettings.getInstance().state
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
                showExamplePreview(project, file, desc)
            }
            actionLink.setIcon(AllIcons.Actions.CheckOut, true)
            HelpTooltip().setDescription("Preview $file before choosing whether to download it or check it out")
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
            val confirmed = Messages.showOkCancelDialog(
                project,
                "Checkout all examples into your current project? Existing files with the same name will be overwritten.",
                "Checkout Examples",
                "Checkout",
                Messages.getCancelButton(),
                null
            ) == Messages.OK

            if (!confirmed) {
                return@ActionLink
            }

            val sourceRoot = firstSourceRoot(project)

            WriteCommandAction.runWriteCommandAction(project) {
                val directory = sourceRoot.getOrCreatePackageDir()
                allExampleFiles.forEach {
                    createFile(directory, it, project)
                }
            }
        }
        actionLink.setIcon(AllIcons.Actions.CheckOut, true)
        HelpTooltip().setDescription("Checkout all examples into your current project after confirmation")
            .installOn(actionLink)
        return actionLink
    }

    private fun showExamplePreview(project: Project, file: ExampleFile, description: Description?) {
        val exampleContent = loadExampleContent(file)
        if (exampleContent == null) {
            Messages.showErrorDialog(project, "Unable to load example file $file.", "Example Not Available")
            return
        }

        ExamplePreviewDialog(
            project = project,
            fileName = file,
            description = description,
            exampleContent = exampleContent,
            onCheckout = {
                val sourceRoot = firstSourceRoot(project)
                WriteCommandAction.runWriteCommandAction(project) {
                    val directory = sourceRoot.getOrCreatePackageDir()
                    createFile(directory, file, project, exampleContent)?.open(project)
                }
            },
            onDownloadToTemp = {
                downloadExampleToTemp(project, file, exampleContent)
            }
        ).show()
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

    private fun firstSourceRoot(project: Project) =
        ProjectRootManager.getInstance(project).contentSourceRoots.firstOrNull() ?: TODO("No sourceRoot found")

    private fun selectedProject(): Project = ProjectUtil.getActiveProject() ?: TODO("No project is opened")

    private fun createFile(
        directory: VirtualFile,
        file: ExampleFile,
        project: Project,
        providedContent: String? = null
    ): VirtualFile? {
        val projectEncoding = EncodingProjectManager.getInstance(project).defaultCharset
        val lineSeparator = CodeStyle.getDefaultSettings().lineSeparator ?: System.lineSeparator()
        val originalContent = providedContent ?: loadExampleContent(file)
        val normalisedContent = originalContent?.replace("\n", lineSeparator) ?: return null
        val bytes = normalisedContent.toByteArray(charset = projectEncoding)

        val target = directory.findChild(file) ?: directory.createChildData(null, file)
        target.setBinaryContent(bytes)
        return target
    }

    private fun VirtualFile.getOrCreatePackageDir(): VirtualFile {
        val directory = this.findChild(EXAMPLE_DIR)?.takeIf {
            it.exists()
        } ?: this.createChildDirectory(null, EXAMPLE_DIR)
        return directory
    }

    private fun loadExampleContent(file: ExampleFile): String? {
        return javaClass.classLoader.getResource("$EXAMPLE_DIR/$file")?.readText()
    }

    private fun downloadExampleToTemp(project: Project, file: ExampleFile, content: String) {
        try {
            val tempDir = FileUtil.createTempDirectory("advanced-expression-folding-example", null, false)
            val tempFile = File(tempDir, file)
            FileUtil.writeToFile(tempFile, content)

            val virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(tempFile)
            if (virtualFile != null) {
                ApplicationManager.getApplication().invokeLater {
                    FileEditorManager.getInstance(project).openFile(virtualFile, true)
                }
            }

            Messages.showInfoMessage(
                project,
                "Example saved to ${tempFile.absolutePath}",
                "Download Complete"
            )
        } catch (t: Throwable) {
            Messages.showErrorDialog(
                project,
                "Unable to download example: ${t.message}",
                "Download Failed"
            )
        }
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
