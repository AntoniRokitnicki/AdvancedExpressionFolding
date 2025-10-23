package com.intellij.advancedExpressionFolding.application.port.out

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

interface ProjectEditorsProvider {
    fun getEditors(project: Project): List<Editor>
}
