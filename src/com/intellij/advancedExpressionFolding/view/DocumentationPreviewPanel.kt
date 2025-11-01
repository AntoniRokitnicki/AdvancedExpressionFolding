package com.intellij.advancedExpressionFolding.view

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.EditorTextField
import com.intellij.util.ui.JBUI

class DocumentationPreviewPanel(@Suppress("UNUSED_PARAMETER") project: Project) : SimpleToolWindowPanel(true, true) {

    private val editorField = EditorTextField()

    init {
        setContent(editorField)
        toolbar = null
        border = JBUI.Borders.empty(8)
    }

    fun setPreviewText(text: String) {
        editorField.text = text
    }
}
