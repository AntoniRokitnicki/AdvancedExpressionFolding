package com.intellij.advancedExpressionFolding.infrastructure.psi

import com.intellij.advancedExpressionFolding.application.port.out.KeyAwareElementProvider
import com.intellij.advancedExpressionFolding.domain.model.KeyAwareElement
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.advancedExpressionFolding.processor.cache.Keys

class PsiKeyAwareElementProvider : KeyAwareElementProvider {
    override fun provide(editor: Editor): KeyAwareElement? {
        val project = editor.project ?: return null
        val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document) ?: return null
        return PsiKeyAwareElement(psiFile)
    }

    private class PsiKeyAwareElement(
        private val element: PsiElement
    ) : KeyAwareElement {
        override fun clearKeys() {
            Keys.clearAll(element)
        }

        override fun children(): Sequence<KeyAwareElement> =
            element.children.asSequence().map(::PsiKeyAwareElement)
    }
}
