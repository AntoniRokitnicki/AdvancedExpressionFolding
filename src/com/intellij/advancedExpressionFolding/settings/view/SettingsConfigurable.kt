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
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import com.intellij.util.ui.JBUI
import java.awt.Color.decode
import java.awt.FlowLayout
import java.net.URI
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.SwingConstants
import javax.swing.Icon
import javax.swing.UIManager
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
        internal const val CHILD_ICON_PROPERTY = "advancedExpressionFolding.childIcon"
    }
    
    @CheckboxDsl
    override fun Panel.registerCheckbox(
        property: KMutableProperty0<Boolean>,
        title: String,
        block: (CheckboxBuilder.() -> Unit)?
    ) {
        val builder = CheckboxBuilder()
        block?.invoke(builder)

        val definition = builder.build(property, title)

        val checkbox = JBCheckBox(title)
        checkbox.isSelected = property.get()
        checkbox.addActionListener {
            pendingChanges[property] = checkbox.isSelected
        }
        propertyToCheckbox[property] = checkbox

        definition.icon?.let { overlayIcon ->
            val laf = UIManager.getLookAndFeelDefaults()
            val baseIcon = checkbox.icon ?: laf.getIcon("CheckBox.icon")
            val baseSelectedIcon = checkbox.selectedIcon ?: laf.getIcon("CheckBox.selectedIcon") ?: baseIcon
            val baseDisabledIcon = checkbox.disabledIcon ?: laf.getIcon("CheckBox.disabledIcon") ?: baseIcon
            val baseDisabledSelectedIcon = checkbox.disabledSelectedIcon
                ?: laf.getIcon("CheckBox.disabledSelectedIcon")
                ?: baseSelectedIcon
            val overlayGap = JBUI.scale(4)
            val textGap = JBUI.scale(8)

            checkbox.icon = composeCheckboxIcon(baseIcon, overlayIcon, overlayGap)
            checkbox.selectedIcon = composeCheckboxIcon(baseSelectedIcon, overlayIcon, overlayGap)
            checkbox.disabledIcon = composeCheckboxIcon(baseDisabledIcon, overlayIcon, overlayGap)
            checkbox.disabledSelectedIcon = composeCheckboxIcon(baseDisabledSelectedIcon, overlayIcon, overlayGap)
            checkbox.iconTextGap = textGap
            checkbox.horizontalTextPosition = SwingConstants.RIGHT
            checkbox.putClientProperty(CHILD_ICON_PROPERTY, overlayIcon)
        }

        row {
            cell(checkbox)
        }

        if (definition.exampleLinkMap != null || definition.docLink != null) {
            row {
                cell(createExamplePanel(definition.exampleLinkMap, definition.docLink))
            }
        }

    }
}

private fun composeCheckboxIcon(base: Icon?, overlay: Icon, gap: Int): Icon {
    return base?.let { CheckboxWithOverlayIcon(it, overlay, gap) } ?: overlay
}

internal class CheckboxWithOverlayIcon(
    private val base: Icon,
    private val overlay: Icon,
    private val gap: Int,
) : Icon {
    private val overlayOffsetX = base.iconWidth + gap
    private val height = maxOf(base.iconHeight, overlay.iconHeight)

    override fun getIconWidth(): Int = overlayOffsetX + overlay.iconWidth

    override fun getIconHeight(): Int = height

    override fun paintIcon(c: java.awt.Component?, g: java.awt.Graphics?, x: Int, y: Int) {
        base.paintIcon(c, g, x, y)
        val overlayY = y + (height - overlay.iconHeight) / 2
        overlay.paintIcon(c, g, x + overlayOffsetX, overlayY)
    }

}
