package com.intellij.advancedExpressionFolding


import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.application.options.CodeStyle
import com.intellij.application.options.editor.CodeFoldingOptionsProvider
import com.intellij.icons.AllIcons.Actions.CheckOut
import com.intellij.ide.BrowserUtil
import com.intellij.ide.HelpTooltip
import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.command.WriteCommandAction.runWriteCommandAction
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.options.BeanConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.encoding.EncodingProjectManager
import com.intellij.ui.components.ActionLink
import java.awt.Component
import java.awt.FlowLayout
import java.awt.GridLayout
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel
import kotlin.reflect.KMutableProperty0


abstract class AbstractExpressionFoldingOptionsProvider : BeanConfigurable<AdvancedExpressionFoldingSettings.State?>(
    AdvancedExpressionFoldingSettings.getInstance().state
), CodeFoldingOptionsProvider {

    private val log: Logger = Logger.getInstance(AdvancedExpressionFoldingOptionsProvider::class.java)
    private val checkboxAddonMap = HashMap<String, Component>()

    private val exampleList = mutableListOf<ExampleFile>()

    protected fun checkBox(
        title: String,
        property: KMutableProperty0<Boolean>,
        exampleLinkMap: Map<ExampleFile, Description?>? = null,
        docLink: UrlSuffix? = null
    ) {
        super.checkBox(title, property)

        try {
            val panel = JPanel()
            val gridLayout = FlowLayout(FlowLayout.LEFT)
            panel.setLayout(gridLayout)
            docLink?.let {
                addLink(panel, "https://github.com/AntoniRokitnicki/AdvancedExpressionFolding/wiki$it")
            }
            exampleLinkMap?.onEach { (file, desc) ->
                exampleList.add(file)
                val suffix = desc?.let {
                    " $it"
                } ?: ""
                val description = "example$suffix"

                addExample(
                    panel,
                    description,
                    file
                )
            }
            checkboxAddonMap[title] = panel
        } catch (e: Exception) {
            log.error("Unexpected issue while creating checkBox for $title", e);
        }
    }

    private fun addLink(panel: JPanel, url: UrlSuffix): Component? {
        val actionLink = ActionLink("doc") {
            BrowserUtil.browse(url)
        }
        actionLink.setExternalLinkIcon()
        return panel.add(actionLink)
    }

    private fun addExample(panel: JPanel, title: Description, file: ExampleFile): Component? {
        val actionLink = ActionLink(title) {
            val project = selectedProject()
            val sourceRoot = firstSourceRoot(project)

            runWriteCommandAction(project) {
                val directory = sourceRoot.getOrCreatePackageDir()
                createFileIfExists(directory, file, project)?.open(project)
            }
        }
        actionLink.setIcon(CheckOut, true)
        HelpTooltip().setDescription("WARNING: Clicking this button will checkout $file into your current project")
            .installOn(actionLink)
        return panel.add(actionLink)
    }


    private fun createDownloadExamplesLink(): ActionLink {
        val actionLink = ActionLink("Checkout Examples to Current Project") {
            val project = selectedProject()
            val sourceRoot = firstSourceRoot(project)

            runWriteCommandAction(project) {
                val directory = sourceRoot.getOrCreatePackageDir()
                exampleList.forEach {
                    createFileIfExists(directory, it, project)
                }
            }
        }
        actionLink.setIcon(CheckOut, true)
        HelpTooltip().setDescription("WARNING: Clicking this button will checkout examples into your current project")
            .installOn(actionLink)
        return actionLink
    }

    override fun createComponent(): JComponent? {
        val superComponent = super.createComponent() ?: return null
        try {
            val mainPanel = superComponent.components?.firstOrNull().asInstance<JPanel>() ?: return superComponent
            val withLinks = mainPanel.components?.filterIsInstance<JCheckBox>()?.mapIndexed { index, checkBox ->
                val text = checkBox.text
                val link = checkboxAddonMap[text] ?: return@mapIndexed null
                Pair(link, index + 1)
            }?.filterNotNull()?.reversed() ?: return superComponent

            withLinks.forEach { (link, index) ->
                mainPanel.add(link, index)
            }
            mainPanel.add(createDownloadExamplesLink(), 0)

            (mainPanel.layout as? GridLayout)?.let {
                it.rows += withLinks.size + 1
            }
        } catch (e: Exception) {
            log.error("Unexpected issue while creating component", e);
        }
        return superComponent
    }

    private fun firstSourceRoot(project: Project) =
        ProjectRootManager.getInstance(project).contentSourceRoots.firstOrNull() ?: TODO("No sourceRoot found")

    private fun selectedProject(): Project = ProjectUtil.getActiveProject() ?: TODO("No project is opened")

    private fun createFileIfExists(
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

}

typealias Description = String
typealias UrlSuffix = String
typealias ExampleFile = String

private const val EXAMPLE_DIR = "data"