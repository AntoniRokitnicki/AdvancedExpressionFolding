package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingBuilder
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorEx
import com.intellij.advancedExpressionFolding.diff.FoldingTemporaryEditor
import com.intellij.advancedExpressionFolding.diff.Range
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.FoldingPreviewDefaults
import com.intellij.advancedExpressionFolding.settings.StateDelegate
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
import com.intellij.lang.java.JavaLanguage
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.ide.CopyPasteManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.util.Computable
import com.intellij.openapi.util.Disposer
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiJavaFile
import com.intellij.ui.EditorTextField
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBScrollPane
import com.intellij.util.ui.JBUI
import org.jetbrains.annotations.TestOnly
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.datatransfer.StringSelection
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.Box

class FoldingPreviewPanel(
    initialState: AdvancedExpressionFoldingSettings.State,
    initialSnippet: String,
    private val onSnippetChanged: (String) -> Unit,
    private val defaultSnippetProvider: () -> String = { FoldingPreviewDefaults.DEFAULT_SNIPPET },
    project: Project = ProjectManager.getInstance().defaultProject
) : Disposable {
    val component: JComponent get() = rootPanel
    val previewText: String get() = latestPreview

    private val project: Project = project
    private val snippetDocument = EditorFactory.getInstance().createDocument(initialSnippet)
    private val resultDocument = EditorFactory.getInstance().createDocument("")
    private val snippetField = PreviewEditorTextField(snippetDocument, project, JavaLanguage.INSTANCE.associatedFileType!!, false, false)
    private val resultField = PreviewEditorTextField(resultDocument, project, JavaLanguage.INSTANCE.associatedFileType!!, true, false).apply {
        setViewer(true)
    }
    private val rootPanel = JPanel(BorderLayout())
    private var listenersEnabled = true
    private var currentState = initialState.copy()
    private var latestPreview: String = ""
    private var lastDescriptorCount: Int = 0
    private var lastPlaceholders: List<String?> = emptyList()
    private val disposable = Disposer.newDisposable("FoldingPreviewPanel")

    init {
        Disposer.register(disposable, snippetField)
        Disposer.register(disposable, resultField)
        configureSnippetField()
        configureResultField()
        MethodCallFactory.initialize()
        rootPanel.border = JBUI.Borders.empty(8, 0, 0, 0)
        rootPanel.add(createContentPanel(), BorderLayout.CENTER)
        rootPanel.add(createActionsPanel(), BorderLayout.SOUTH)
        rebuildPreview()
    }

    fun updatePreview(state: AdvancedExpressionFoldingSettings.State) {
        currentState = state.copy()
        rebuildPreview()
    }

    fun setSnippet(snippet: String, trackModification: Boolean) {
        listenersEnabled = false
        snippetField.text = snippet
        listenersEnabled = true
        if (trackModification) {
            onSnippetChanged(snippet)
        }
        rebuildPreview()
    }

    private fun configureSnippetField() {
        snippetField.border = JBUI.Borders.empty()
        snippetField.setOneLineMode(false)
        snippetField.preferredSize = Dimension(0, 180)
        snippetField.document.addDocumentListener(object : DocumentListener {
            override fun documentChanged(event: DocumentEvent) {
                if (!listenersEnabled) {
                    return
                }
                val text = event.document.text
                onSnippetChanged(text)
                rebuildPreview(text)
            }
        })
    }

    private fun configureResultField() {
        resultField.border = JBUI.Borders.empty()
        resultField.setOneLineMode(false)
        resultField.preferredSize = Dimension(0, 180)
    }

    private fun createContentPanel(): JComponent {
        val sections = JPanel()
        sections.layout = javax.swing.BoxLayout(sections, javax.swing.BoxLayout.Y_AXIS)
        sections.border = JBUI.Borders.empty()
        sections.add(createSection("Preview snippet", snippetField))
        sections.add(Box.createVerticalStrut(JBUI.scale(8)))
        sections.add(createSection("Folded preview", resultField))
        return sections
    }

    private fun createActionsPanel(): JComponent {
        val panel = JPanel(FlowLayout(FlowLayout.LEFT, JBUI.scale(5), 0))
        val resetButton = JButton("Reset snippet")
        resetButton.addActionListener {
            setSnippet(defaultSnippetProvider(), trackModification = true)
        }
        val copyButton = JButton("Copy result")
        copyButton.addActionListener {
            CopyPasteManager.getInstance().setContents(StringSelection(previewText))
        }
        panel.add(resetButton)
        panel.add(copyButton)
        return panel
    }

    private fun createSection(title: String, field: EditorTextField): JComponent {
        val panel = JPanel(BorderLayout())
        panel.border = JBUI.Borders.empty()
        panel.add(JBLabel(title), BorderLayout.NORTH)
        panel.add(JBScrollPane(field), BorderLayout.CENTER)
        return panel
    }

    private fun rebuildPreview(snippet: String = snippetField.text) {
        val preview = if (snippet.isBlank()) {
            ""
        } else {
            runCatching { computePreview(snippet, currentState.copy()) }.getOrElse { snippet }
        }
        latestPreview = preview
        resultField.text = preview
        resultField.editor?.caretModel?.moveToOffset(0)
    }

    private fun computePreview(
        snippet: String,
        state: AdvancedExpressionFoldingSettings.State
    ): String {
        val project = this.project
        return StateDelegate.withState(state) {
            val (document, descriptors) = ApplicationManager.getApplication().runReadAction(Computable {
                val psiFile = PsiFileFactory.getInstance(project)
                    .createFileFromText(
                        FoldingPreviewDefaults.FILE_NAME,
                        JavaLanguage.INSTANCE,
                        snippet,
                        true,
                        false
                    ) as PsiJavaFile
                val documentManager = PsiDocumentManager.getInstance(project)
                val document = documentManager.getDocument(psiFile) ?: return@Computable null
                documentManager.commitDocument(document)
                val builder = AdvancedExpressionFoldingBuilder(state)
                val foldingDescriptors = builder.buildFoldRegions(psiFile, document, false)
                val foldingGroupSet = mutableSetOf<FoldingGroup>()
                val converted = foldingDescriptors.mapIndexed { index, descriptor ->
                    descriptor.group?.let { foldingGroupSet += it }
                    FoldingDescriptorEx(
                        index,
                        document.getText(descriptor.range),
                        descriptor.placeholderText,
                        Range(
                            descriptor.range.startOffset,
                            descriptor.range.endOffset,
                            descriptor.range.length
                        ),
                        descriptor.group?.toSimpleString(),
                        foldingGroupSet.size - 1
                    )
                }
                lastDescriptorCount = converted.size
                lastPlaceholders = converted.map { it.placeholder }
                document to converted
            }) ?: return@withState snippet

            ApplicationManager.getApplication().runWriteAction(Computable {
                FoldingTemporaryEditor.foldInEditor(document.text, descriptors)
            })
        }
    }

    private fun FoldingGroup?.toSimpleString(): String? {
        return this?.toString()?.let {
            val dotIndex = it.lastIndexOf('.')
            if (dotIndex >= 0 && dotIndex < it.length - 1) {
                it.substring(dotIndex + 1)
            } else {
                it
            }
        }
    }

    @TestOnly
    internal fun descriptorCountForTests(): Int = lastDescriptorCount

    @TestOnly
    internal fun placeholdersForTests(): List<String?> = lastPlaceholders

    override fun dispose() {
        Disposer.dispose(disposable)
    }
}

private class PreviewEditorTextField(
    document: Document,
    project: Project,
    fileType: FileType,
    isViewer: Boolean,
    oneLineMode: Boolean
) : EditorTextField(document, project, fileType, isViewer, oneLineMode), Disposable {

    private val createdEditors = mutableSetOf<EditorEx>()

    override fun createEditor(): EditorEx {
        return super.createEditor().also { createdEditors += it }
    }

    override fun dispose() {
        val factory = EditorFactory.getInstance()
        createdEditors.forEach { factory.releaseEditor(it) }
        createdEditors.clear()
    }
}
