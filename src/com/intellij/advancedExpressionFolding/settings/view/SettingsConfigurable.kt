package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingBuilder
import com.intellij.advancedExpressionFolding.action.UpdateFoldedTextColorsAction
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.application.options.CodeStyle
import com.intellij.application.options.editor.EditorOptionsProvider
import com.intellij.icons.AllIcons
import com.intellij.ide.BrowserUtil
import com.intellij.ide.HelpTooltip
import com.intellij.ide.impl.ProjectUtil
import com.intellij.ide.highlighter.JavaFileType
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.util.Computable
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.encoding.EncodingProjectManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFileFactory
import com.intellij.ui.JBColor
import com.intellij.ui.OnePixelSplitter
import com.intellij.ui.SimpleListCellRenderer
import com.intellij.ui.components.ActionLink
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.panel
import java.awt.Color.decode
import java.awt.FlowLayout
import java.net.URI
import java.net.JarURLConnection
import java.net.URL
import java.awt.BorderLayout
import java.awt.Component
import java.awt.datatransfer.StringSelection
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JButton
import javax.swing.JPanel
import kotlin.reflect.KMutableProperty0

class SettingsConfigurable : EditorOptionsProvider, CheckboxesProvider() {
    private val state = AdvancedExpressionFoldingSettings.getInstance().state
    private lateinit var panel: DialogPanel
    private val allExampleFiles = mutableSetOf<ExampleFile>()
    private val pendingChanges = mutableMapOf<KMutableProperty0<Boolean>, Boolean>()
    private val propertyToCheckbox = mutableMapOf<KMutableProperty0<Boolean>, JBCheckBox>()
    private val previewExampleFiles: List<ExampleFile> by lazy { loadPreviewExampleFiles() }
    private val loadedSnippets = mutableMapOf<ExampleFile, String>()
    private var currentSnippet: ExampleFile = DEFAULT_SNIPPET
    private var previewDocument: Document? = null
    private var beforeEditor: EditorEx? = null
    private var afterEditor: EditorEx? = null
    private var snippetSelector: ComboBox<ExampleFile>? = null
    private var previewInitialized = false

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

    override fun createComponent(): JComponent {
        disposePreviewEditors()
        propertyToCheckbox.clear()

        val previewSection = createPreviewSection().apply {
            alignmentX = Component.LEFT_ALIGNMENT
        }
        val checkboxesPanel = panel {
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
        }.apply {
            alignmentX = Component.LEFT_ALIGNMENT
        }

        val container = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            add(previewSection)
            add(checkboxesPanel)
        }

        val scrollPane = JBScrollPane(container).apply {
            border = null
        }

        previewInitialized = true
        refreshPreview()

        return scrollPane
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
        updatePreviewFromUi()
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
        private const val DEFAULT_SNIPPET = "OptionalTestData.java"
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
            updatePreviewFromUi()
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

    private fun createPreviewSection(): JComponent {
        return JPanel(BorderLayout()).apply {
            border = com.intellij.util.ui.JBUI.Borders.empty(0, 0, 12, 0)
            add(createPreviewHeader(), BorderLayout.NORTH)
            add(createPreviewEditors(), BorderLayout.CENTER)
        }
    }

    private fun createPreviewHeader(): JComponent {
        val label = JBLabel("Preview").apply {
            font = com.intellij.util.ui.JBFont.label().asBold()
        }

        val controls = JPanel(BorderLayout(com.intellij.util.ui.JBUI.scale(8), 0)).apply {
            border = com.intellij.util.ui.JBUI.Borders.empty()
            add(createSnippetSelector(), BorderLayout.CENTER)
            add(createPreviewToolbar(), BorderLayout.EAST)
        }

        return JPanel(BorderLayout(com.intellij.util.ui.JBUI.scale(8), 0)).apply {
            border = com.intellij.util.ui.JBUI.Borders.empty(0, 0, 8, 0)
            add(label, BorderLayout.WEST)
            add(controls, BorderLayout.CENTER)
        }
    }

    private fun createSnippetSelector(): ComboBox<ExampleFile> {
        val availableSnippets = previewExampleFiles
        val comboBox = ComboBox<ExampleFile>(availableSnippets.toTypedArray())
        comboBox.renderer = SimpleListCellRenderer.create<ExampleFile> { label, value, _ ->
            label.text = value?.removeSuffix(".java") ?: ""
        }
        val initialSnippet = currentSnippet.takeIf { availableSnippets.contains(it) }
            ?: availableSnippets.firstOrNull()
            ?: DEFAULT_SNIPPET
        currentSnippet = initialSnippet
        comboBox.selectedItem = initialSnippet
        comboBox.addActionListener {
            val selected = comboBox.selectedItem as? ExampleFile ?: return@addActionListener
            if (selected != currentSnippet) {
                setSnippet(selected)
            } else {
                updatePreviewFromUi()
            }
        }
        comboBox.maximumSize = com.intellij.util.ui.JBUI.size(280, comboBox.preferredSize.height)
        snippetSelector = comboBox
        return comboBox
    }

    private fun createPreviewToolbar(): JComponent {
        val actionGroup = DefaultActionGroup().apply {
            add(object : DumbAwareAction("Reset snippet", "Restore the default preview snippet", AllIcons.Actions.Rollback) {
                override fun actionPerformed(event: com.intellij.openapi.actionSystem.AnActionEvent) {
                    val selector = snippetSelector
                    val defaultSnippet = previewExampleFiles.firstOrNull() ?: DEFAULT_SNIPPET
                    if (selector?.selectedItem != defaultSnippet) {
                        selector?.selectedItem = defaultSnippet
                    } else {
                        setSnippet(defaultSnippet)
                    }
                }
            })
            add(object : DumbAwareAction("Copy original", "Copy the original snippet", AllIcons.Actions.Copy) {
                override fun actionPerformed(event: com.intellij.openapi.actionSystem.AnActionEvent) {
                    previewDocument?.let {
                        CopyPasteManager.getInstance().setContents(StringSelection(it.text))
                    }
                }
            })
            add(object : DumbAwareAction("Copy folded", "Copy the folded preview", AllIcons.Actions.Copy) {
                override fun actionPerformed(event: com.intellij.openapi.actionSystem.AnActionEvent) {
                    afterEditor?.let { editor ->
                        val foldedText = editor.foldedText()
                        CopyPasteManager.getInstance().setContents(StringSelection(foldedText))
                    }
                }
            })
        }

        val toolbar = ActionManager.getInstance().createActionToolbar(
            "AdvancedExpressionFoldingPreview",
            actionGroup,
            true
        ).apply {
            targetComponent = null
        }
        return toolbar.component
    }

    private fun createPreviewEditors(): JComponent {
        val document = previewDocument ?: createPreviewDocument()
        val before = beforeEditor ?: createEditor(document)
        val after = afterEditor ?: createEditor(document)

        beforeEditor = before
        afterEditor = after

        val beforePanel = JPanel(BorderLayout()).apply {
            border = com.intellij.util.ui.JBUI.Borders.empty()
            add(JBLabel("Original"), BorderLayout.NORTH)
            add(before.component, BorderLayout.CENTER)
        }

        val afterPanel = JPanel(BorderLayout()).apply {
            border = com.intellij.util.ui.JBUI.Borders.empty()
            add(JBLabel("Folded"), BorderLayout.NORTH)
            add(after.component, BorderLayout.CENTER)
        }

        val splitter = OnePixelSplitter(false, 0.5f).apply {
            firstComponent = beforePanel
            secondComponent = afterPanel
            preferredSize = com.intellij.util.ui.JBUI.size(0, 320)
        }

        return splitter
    }

    private fun createPreviewDocument(): Document {
        val text = loadSnippetText(currentSnippet)
        return EditorFactory.getInstance().createDocument(text).also {
            previewDocument = it
        }
    }

    private fun createEditor(document: Document): EditorEx {
        val editor = EditorFactory.getInstance().createViewer(document, previewProject()) as EditorEx
        editor.settings.apply {
            isLineMarkerAreaShown = false
            isLineNumbersShown = true
            isFoldingOutlineShown = false
            isRightMarginShown = false
            isUseSoftWraps = true
        }
        editor.setCaretEnabled(false)
        editor.component.border = com.intellij.util.ui.JBUI.Borders.empty()
        return editor
    }

    private fun setSnippet(exampleFile: ExampleFile) {
        val document = previewDocument ?: return
        val text = loadSnippetText(exampleFile)
        ApplicationManager.getApplication().runWriteAction {
            document.setText(text)
            beforeEditor?.caretModel?.moveToOffset(0)
            afterEditor?.caretModel?.moveToOffset(0)
        }
        currentSnippet = exampleFile
        updatePreviewFromUi()
    }

    private fun loadSnippetText(exampleFile: ExampleFile): String {
        return loadedSnippets.getOrPut(exampleFile) {
            javaClass.classLoader.getResource("$EXAMPLE_DIR/$exampleFile")?.readText()
                ?: "// Unable to load $exampleFile"
        }
    }

    private fun loadPreviewExampleFiles(): List<ExampleFile> {
        val discovered = collectExampleFiles()

        val ordered = linkedSetOf(DEFAULT_SNIPPET)
        discovered.sorted().forEach { file ->
            if (file.endsWith(".java")) {
                ordered.add(file)
            }
        }
        val result = ordered.toList()
        if (currentSnippet !in result) {
            currentSnippet = result.firstOrNull() ?: DEFAULT_SNIPPET
        }
        return if (result.isEmpty()) {
            listOf(DEFAULT_SNIPPET)
        } else {
            result
        }
    }

    private fun collectExampleFiles(): Set<ExampleFile> {
        val classLoader = javaClass.classLoader
        val urls = mutableListOf<URL>()

        fun collectResources(path: String) {
            runCatching { classLoader.getResources(path) }.getOrNull()?.let { enumeration ->
                while (enumeration.hasMoreElements()) {
                    urls += enumeration.nextElement()
                }
            }
        }

        collectResources(EXAMPLE_DIR)
        collectResources("$EXAMPLE_DIR/")

        if (urls.isEmpty()) {
            classLoader.getResource(EXAMPLE_DIR)?.let(urls::add)
            classLoader.getResource("$EXAMPLE_DIR/")?.let(urls::add)
        }

        if (urls.isEmpty()) {
            return emptySet()
        }

        return urls.flatMap(::loadExampleFilesFromResource).toSet()
    }

    private fun loadExampleFilesFromResource(resource: URL): List<ExampleFile> {
        return when (resource.protocol) {
            "file" -> loadExampleFilesFromFileSystem(resource)
            "jar" -> loadExampleFilesFromJar(resource)
            else -> emptyList()
        }
    }

    private fun loadExampleFilesFromFileSystem(resource: URL): List<ExampleFile> {
        return runCatching {
            Files.list(Paths.get(resource.toURI())).use { stream ->
                stream
                    .filter(Files::isRegularFile)
                    .map { path -> path.fileName.toString() }
                    .toList()
            }
        }.getOrElse { emptyList() }
    }

    private fun loadExampleFilesFromJar(resource: URL): List<ExampleFile> {
        val connection = resource.openConnection()
        if (connection is JarURLConnection) {
            val prefix = connection.entryName?.let { name ->
                if (name.endsWith('/')) name else "$name/"
            } ?: "$EXAMPLE_DIR/"
            val entries = connection.jarFile.entries()
            val files = mutableListOf<ExampleFile>()
            while (entries.hasMoreElements()) {
                val entry = entries.nextElement()
                if (!entry.isDirectory && entry.name.startsWith(prefix)) {
                    val relativeName = entry.name.removePrefix(prefix)
                    if (!relativeName.contains('/')) {
                        files += relativeName
                    }
                }
            }
            return files
        }
        return emptyList()
    }

    private fun refreshPreview() {
        if (!previewInitialized) {
            return
        }
        val editor = afterEditor ?: return
        if (editor.isDisposed) {
            return
        }
        val document = previewDocument ?: return
        val project = previewProject()

        val descriptors = ApplicationManager.getApplication().runReadAction(Computable {
            val psiFile = PsiFileFactory.getInstance(project).createFileFromText(
                "PreviewSnippet.java",
                JavaFileType.INSTANCE,
                document.text
            )
            val psiDocument = PsiDocumentManager.getInstance(project).getDocument(psiFile) ?: return@Computable emptyArray()
            withPreviewState {
                AdvancedExpressionFoldingBuilder().buildFoldRegions(psiFile, psiDocument, false)
            }
        })

        ApplicationManager.getApplication().runWriteAction {
            applyFoldRegions(editor, descriptors)
        }
    }

    private fun applyFoldRegions(editor: EditorEx, descriptors: Array<com.intellij.lang.folding.FoldingDescriptor>) {
        val foldingModel = editor.foldingModel
        foldingModel.runBatchFoldingOperation {
            foldingModel.allFoldRegions.toList().forEach { region ->
                foldingModel.removeFoldRegion(region)
            }
            descriptors.forEach { descriptor ->
                val placeholder = descriptor.placeholderText ?: return@forEach
                val foldRegion = foldingModel.createFoldRegion(
                    descriptor.range.startOffset,
                    descriptor.range.endOffset,
                    placeholder,
                    descriptor.group,
                    false
                )
                foldRegion?.isExpanded = false
            }
        }
        editor.caretModel.moveToOffset(0)
        editor.scrollingModel.scrollToCaret(com.intellij.openapi.editor.ScrollType.CENTER_UP)
    }

    private fun withPreviewState(action: () -> Array<com.intellij.lang.folding.FoldingDescriptor>): Array<com.intellij.lang.folding.FoldingDescriptor> {
        if (propertyToCheckbox.isEmpty()) {
            return action()
        }
        val previousValues = propertyToCheckbox.keys.associateWith { property ->
            property.get()
        }
        propertyToCheckbox.forEach { (property, checkbox) ->
            property.set(checkbox.isSelected)
        }
        return try {
            action()
        } finally {
            previousValues.forEach { (property, value) ->
                property.set(value)
            }
        }
    }

    private fun updatePreviewFromUi() {
        if (!previewInitialized) {
            return
        }
        refreshPreview()
    }

    private fun previewProject(): Project {
        return ProjectManager.getInstance().openProjects.firstOrNull() ?: ProjectManager.getInstance().defaultProject
    }

    private fun disposePreviewEditors() {
        val factory = EditorFactory.getInstance()
        beforeEditor?.let(factory::releaseEditor)
        afterEditor?.let(factory::releaseEditor)
        beforeEditor = null
        afterEditor = null
        previewDocument = null
        previewInitialized = false
    }

    private fun EditorEx.foldedText(): String {
        val doc = document
        val regions = foldingModel.allFoldRegions
            .filter { it.isValid && !it.isExpanded }
            .sortedBy { it.startOffset }
        if (regions.isEmpty()) {
            return doc.text
        }
        var offset = 0
        val builder = StringBuilder(doc.textLength)
        regions.forEach { region ->
            val start = region.startOffset
            if (start >= offset) {
                builder.append(doc.charsSequence.subSequence(offset, start))
                builder.append(region.placeholderText)
                offset = region.endOffset
            }
        }
        if (offset < doc.textLength) {
            builder.append(doc.charsSequence.subSequence(offset, doc.textLength))
        }
        return builder.toString()
    }

    override fun disposeUIResources() {
        disposePreviewEditors()
        snippetSelector = null
    }
}
