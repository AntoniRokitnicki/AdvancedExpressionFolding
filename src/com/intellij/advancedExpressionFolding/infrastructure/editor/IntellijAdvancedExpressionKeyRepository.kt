package com.intellij.advancedExpressionFolding.infrastructure.editor

import com.intellij.advancedExpressionFolding.domain.repository.AdvancedExpressionKeyRepository
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.psi.PsiElement

class IntellijAdvancedExpressionKeyRepository : AdvancedExpressionKeyRepository {
    override fun clear(element: PsiElement) {
        Keys.clearAll(element)
    }
}
