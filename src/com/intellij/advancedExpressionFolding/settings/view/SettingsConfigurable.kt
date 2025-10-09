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
import com.intellij.ui.CheckboxTree
import com.intellij.ui.CheckboxTree.CheckboxTreeCellRenderer
import com.intellij.ui.CheckedTreeNode
import com.intellij.ui.components.ActionLink
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import java.awt.Color.decode
import java.awt.Dimension
import java.awt.FlowLayout
import java.net.URI
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTree
import javax.swing.tree.DefaultMutableTreeNode
import kotlin.reflect.KMutableProperty0

class SettingsConfigurable : EditorOptionsProvider, CheckboxesProvider() {
    private val state = AdvancedExpressionFoldingSettings.getInstance().state
    private lateinit var panel: DialogPanel
    private val allExampleFiles = mutableSetOf<ExampleFile>()
    private lateinit var tree: CheckboxTree
    private val nodes = mutableListOf<SettingNode>()
    private val definitions = mutableListOf<CheckboxDefinition>()

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
        // collect definitions, adding additional UI rows such as text fields
        initialize(state)
        row {
            tree = buildTree()
            val scroll = JScrollPane(tree)
            scroll.preferredSize = Dimension(600, 400)
            cell(scroll)
        }
    }.also {
        panel = it
    }

    override fun isModified(): Boolean {
        return panel.isModified() || nodes.any { it.isModified() }
    }

    override fun apply() {
        nodes.forEach { it.apply() }
        panel.apply()
    }

    override fun reset() {
        nodes.forEach { it.reset() }
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

    private fun buildTree(): CheckboxTree {
        val root = CheckedTreeNode("root")
        val main = CheckedTreeNode("Main")
        val others = CheckedTreeNode("Additional")

        val mainPropertyNames = AdvancedExpressionFoldingSettings.allMainProperties()
            .map { it.name }
            .toSet()

        definitions.forEach { definition ->
            val node = SettingNode(definition)
            nodes.add(node)
            val parent = if (mainPropertyNames.contains(definition.property.name)) main else others
            parent.add(node)
        }

        root.add(main)
        root.add(others)

        val renderer = object : CheckboxTreeCellRenderer() {
            override fun customizeRenderer(
                tree: JTree,
                value: Any,
                selected: Boolean,
                expanded: Boolean,
                leaf: Boolean,
                row: Int,
                hasFocus: Boolean,
            ) {
                val node = value as? DefaultMutableTreeNode ?: return
                val text = node.userObject?.toString() ?: ""
                textRenderer.append(text)
            }
        }

        return CheckboxTree(renderer, root).apply {
            isRootVisible = false
        }
    }

    private class SettingNode(
        val definition: CheckboxDefinition,
    ) : CheckedTreeNode(definition.title) {
        private val property = definition.property

        init {
            isChecked = property.get()
        }

        fun apply() = property.set(isChecked)
        fun reset() {
            isChecked = property.get()
        }
        fun isModified() = isChecked != property.get()
    }
    
    @CheckboxDsl
    override fun Panel.registerCheckbox(
        property: KMutableProperty0<Boolean>,
        title: String,
        block: (CheckboxBuilder.() -> Unit)?,
    ) {
        val builder = CheckboxBuilder()
        block?.invoke(builder)
        definitions += builder.build(property, title)
    }
}
