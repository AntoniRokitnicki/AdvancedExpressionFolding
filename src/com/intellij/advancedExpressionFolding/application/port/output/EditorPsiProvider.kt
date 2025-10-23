package com.intellij.advancedExpressionFolding.application.port.output

import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

interface EditorPsiProvider {
    fun getPsiFile(editor: Editor): PsiFile?
}
