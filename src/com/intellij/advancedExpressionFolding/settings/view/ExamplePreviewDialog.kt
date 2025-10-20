package com.intellij.advancedExpressionFolding.settings.view

import com.intellij.openapi.fileTypes.FileTypeRegistry
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.fileTypes.PlainTextLanguage
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.util.text.HtmlBuilder
import com.intellij.ui.JBSplitter
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.components.panels.VerticalLayout
import com.intellij.ui.EditorTextField
import com.intellij.ui.LanguageTextField
import java.awt.BorderLayout
import javax.swing.Action
import javax.swing.JComponent
import javax.swing.JPanel

class ExamplePreviewDialog(
    project: Project,
    private val fileName: ExampleFile,
    private val description: Description?,
    private val exampleContent: String,
    private val onCheckout: () -> Unit,
    private val onDownloadToTemp: () -> Unit
) : DialogWrapper(project, true) {

    private val codeViewer: EditorTextField

    init {
        title = "Preview $fileName"
        setResizable(true)
        val fileType = FileTypeRegistry.getInstance().getFileTypeByFileName(fileName)
        val language = (fileType as? LanguageFileType)?.language ?: PlainTextLanguage.INSTANCE
        codeViewer = LanguageTextField(language, project, exampleContent, false).apply {
            setOneLineMode(false)
            setViewer(true)
        }

        init()
        setSize(900, 600)
    }

    override fun createCenterPanel(): JComponent {
        val splitter = JBSplitter(false, 0.62f)
        splitter.splitterProportionKey = "$DIMENSION_SERVICE_KEY.Splitter"
        splitter.firstComponent = codeViewer
        splitter.secondComponent = createDetailsPanel()
        return splitter
    }

    override fun getPreferredFocusedComponent(): JComponent = codeViewer

    private fun createDetailsPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        val contentPanel = JPanel(VerticalLayout(8)).apply {
            isOpaque = false
        }

        val titleLabel = JBLabel(HtmlBuilder().append("<b>").append(fileName).append("</b>").toString())
        titleLabel.setCopyable(true)
        contentPanel.add(titleLabel)

        description?.let {
            val descriptionArea = JBTextArea(it).apply {
                lineWrap = true
                wrapStyleWord = true
                isEditable = false
                isOpaque = false
            }
            contentPanel.add(descriptionArea)
        }

        val hintArea = JBTextArea(
            "Use the actions below to download this example without modifying your project, or checkout the file when you're ready."
        ).apply {
            lineWrap = true
            wrapStyleWord = true
            isEditable = false
            isOpaque = false
        }
        contentPanel.add(hintArea)

        panel.add(contentPanel, BorderLayout.NORTH)
        return panel
    }

    override fun createActions(): Array<Action> {
        val downloadAction = object : DialogWrapperAction("Download to Temp Directory") {
            override fun doAction(e: java.awt.event.ActionEvent?) {
                onDownloadToTemp()
            }
        }

        val checkoutAction = object : DialogWrapperAction("Checkout to Project") {
            override fun doAction(e: java.awt.event.ActionEvent?) {
                onCheckout()
                close(OK_EXIT_CODE)
            }
        }

        return arrayOf(downloadAction, checkoutAction, cancelAction)
    }

    companion object {
        private const val DIMENSION_SERVICE_KEY = "AdvancedExpressionFolding.ExamplePreviewDialog"
    }
}
