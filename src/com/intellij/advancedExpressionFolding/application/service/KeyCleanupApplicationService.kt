package com.intellij.advancedExpressionFolding.application.service

import com.intellij.advancedExpressionFolding.application.port.out.KeyAwareElementProvider
import com.intellij.advancedExpressionFolding.application.port.out.ProjectEditorsProvider
import com.intellij.advancedExpressionFolding.domain.service.KeyClearanceDomainService
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project

class KeyCleanupApplicationService(
    private val projectEditorsProvider: ProjectEditorsProvider,
    private val keyAwareElementProvider: KeyAwareElementProvider,
    private val domainService: KeyClearanceDomainService
) {
    fun clear(editor: Editor) {
        if (editor.isDisposed) {
            return
        }
        val element = keyAwareElementProvider.provide(editor) ?: return
        domainService.clear(element)
    }

    fun clear(project: Project) {
        projectEditorsProvider.getEditors(project).forEach { editor ->
            clear(editor)
        }
    }
}
