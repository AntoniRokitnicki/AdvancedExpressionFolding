package com.intellij.advancedExpressionFolding.infrastructure.editor

import com.intellij.advancedExpressionFolding.application.port.output.EditorPsiProvider
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile

class IntellijEditorPsiProvider : EditorPsiProvider {
    override fun getPsiFile(editor: Editor): PsiFile? {
        val project = editor.project ?: return null
        return PsiDocumentManager.getInstance(project).getPsiFile(editor.document)
    }
}
