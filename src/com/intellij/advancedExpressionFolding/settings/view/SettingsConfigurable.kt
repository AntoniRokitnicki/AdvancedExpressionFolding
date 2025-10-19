package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingBuilder
import com.intellij.advancedExpressionFolding.action.UpdateFoldedTextColorsAction
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.application.options.CodeStyle
import com.intellij.application.options.editor.EditorOptionsProvider
import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.ide.HelpTooltip
import com.intellij.ide.highlighter.JavaFileType
import com.intellij.ide.impl.ProjectUtil
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.EditorKind
import com.intellij.openapi.editor.FoldRegion
import com.intellij.openapi.editor.ex.FoldingModelEx
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.editor.highlighter.EditorHighlighterFactory
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.encoding.EncodingProjectManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiJavaFile
import com.intellij.ui.JBColor
import com.intellij.ui.components.ActionLink
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import com.intellij.util.ui.JBUI
import java.awt.Color.decode
import java.awt.FlowLayout
import java.awt.Dimension
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
    private val booleanStateProperties = AdvancedExpressionFoldingSettings.State::class.memberProperties
        .filterIsInstance<KMutableProperty1<AdvancedExpressionFoldingSettings.State, Boolean>>()
        .associateBy { it.name }
    private var previewEditor: EditorEx? = null
    private var previewDocument: Document? = null
    private var previewProject: Project? = null
    private val logger = Logger.getInstance(SettingsConfigurable::class.java)

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
        disposePreviewEditor()
        val project = previewProject()
        val editor = createPreviewEditor(project)
        previewEditor = editor
        previewProject = project
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
                cell(JBLabel("Preview"))
            }
            row {
                cell(editor.component)
                    .align(Align.FILL)
                    .resizableColumn()
                    .applyToComponent {
                        preferredSize = Dimension(640, 260)
                        border = JBUI.Borders.empty(8, 0, 16, 0)
                    }
            }.resizableRow()
            initialize(state)
        }.also {
            panel = it
            refreshPreview()
        }
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
        refreshPreview()
    }

    override fun reset() {
        pendingChanges.clear()
        propertyToCheckbox.forEach { (property, checkbox) ->
            checkbox.isSelected = property.get()
        }
        refreshPreview()
    }

    override fun disposeUIResources() {
        disposePreviewEditor()
        propertyToCheckbox.clear()
        pendingChanges.clear()
    }

    private fun refreshPreview() {
        val editor = previewEditor ?: return
        val project = previewProject ?: return
        val document = previewDocument ?: return

        val psiDocumentManager = PsiDocumentManager.getInstance(project)
        psiDocumentManager.commitDocument(document)
        val psiFile = psiDocumentManager.getPsiFile(document) as? PsiJavaFile ?: return

        runCatching {
            val previewState = computePreviewState()
            val builder = AdvancedExpressionFoldingBuilder(previewState)
            val descriptors = builder.preview(psiFile, document).descriptors
            applyDescriptors(editor, descriptors)
        }.onFailure { throwable ->
            logger.warn("Failed to update settings preview", throwable)
        }
    }

    private fun computePreviewState(): AdvancedExpressionFoldingSettings.State {
        val previewState = state.copy()
        propertyToCheckbox.forEach { (property, checkbox) ->
            booleanStateProperties[property.name]?.set(previewState, checkbox.isSelected)
        }
        return previewState
    }

    private fun applyDescriptors(editor: EditorEx, descriptors: Array<FoldingDescriptor>) {
        val foldingModel = editor.foldingModel as? FoldingModelEx ?: return
        foldingModel.runBatchFoldingOperation {
            foldingModel.allFoldRegions.toList().forEach(foldingModel::removeFoldRegion)
            descriptors.forEach { descriptor ->
                val placeholder = descriptor.placeholderText ?: descriptor.element.text
                val region = try {
                    foldingModel.createFoldRegion(
                        descriptor.range.startOffset,
                        descriptor.range.endOffset,
                        placeholder,
                        descriptor.group,
                        false
                    )
                } catch (_: IllegalArgumentException) {
                    null
                }
                val collapseByDefault = descriptor.isCollapsedByDefault
                region?.let {
                    it.isExpanded = !(collapseByDefault ?: false)
                }
            }
        }
    }

    private fun createPreviewEditor(project: Project): EditorEx {
        val psiFile = PsiFileFactory.getInstance(project).createFileFromText(
            PREVIEW_FILE_NAME,
            JavaFileType.INSTANCE,
            PREVIEW_SNIPPET
        ) as PsiJavaFile
        val psiDocumentManager = PsiDocumentManager.getInstance(project)
        val document = psiFile.viewProvider.document
            ?: error("Unable to create document for settings preview")
        previewDocument = document
        psiDocumentManager.commitDocument(document)

        val editorFactory = EditorFactory.getInstance()
        val editor = editorFactory.createViewer(document, project, EditorKind.MAIN_EDITOR) as EditorEx
        editor.isViewer = true
        editor.settings.apply {
            isLineNumbersShown = false
            isLineMarkerAreaShown = false
            isFoldingOutlineShown = true
            isCaretRowShown = false
            additionalColumnsCount = 0
            additionalLinesCount = 0
        }
        editor.highlighter = EditorHighlighterFactory.getInstance().createEditorHighlighter(project, JavaFileType.INSTANCE)
        editor.setCaretEnabled(false)
        editor.component.apply {
            isFocusable = false
            border = JBUI.Borders.empty()
        }
        return editor
    }

    private fun updatePendingChanges(property: KMutableProperty0<Boolean>, value: Boolean) {
        if (value == property.get()) {
            pendingChanges.remove(property)
        } else {
            pendingChanges[property] = value
        }
    }

    private fun disposePreviewEditor() {
        previewEditor?.let { EditorFactory.getInstance().releaseEditor(it) }
        previewEditor = null
        previewDocument = null
        previewProject = null
    }

    private fun previewProject(): Project =
        ProjectUtil.getActiveProject() ?: ProjectManager.getInstance().defaultProject

    internal fun previewFoldPlaceholdersForTest(): List<String> =
        previewEditor?.foldingModel?.allFoldRegions
            ?.filter(FoldRegion::isValid)
            ?.mapNotNull(FoldRegion::getPlaceholderText)
            ?: emptyList()

    internal fun checkboxForTest(property: KMutableProperty0<Boolean>): JBCheckBox? =
        propertyToCheckbox[property]

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
        private const val PREVIEW_FILE_NAME = "AdvancedExpressionFoldingPreview.java"
        private const val PREVIEW_RESOURCE_PATH = "preview/SettingsPreviewSnippet.java"
        private const val FALLBACK_PREVIEW_SNIPPET = "class PreviewSnippet {}\n"
        private val PREVIEW_SNIPPET: String by lazy { loadPreviewSnippet() }

        private fun loadPreviewSnippet(): String {
            return SettingsConfigurable::class.java.classLoader
                ?.getResource(PREVIEW_RESOURCE_PATH)
                ?.readText()
                ?.replace("\r\n", "\n")
                ?: FALLBACK_PREVIEW_SNIPPET
        }
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
            updatePendingChanges(property, checkbox.isSelected)
            refreshPreview()
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
