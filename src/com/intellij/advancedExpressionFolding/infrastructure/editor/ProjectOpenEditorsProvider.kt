package com.intellij.advancedExpressionFolding.infrastructure.editor

import com.intellij.advancedExpressionFolding.application.port.out.ProjectEditorsProvider
import com.intellij.advancedExpressionFolding.openTextEditors
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class ProjectOpenEditorsProvider : ProjectEditorsProvider {
    override fun getEditors(project: Project): List<Editor> = project.openTextEditors
}
