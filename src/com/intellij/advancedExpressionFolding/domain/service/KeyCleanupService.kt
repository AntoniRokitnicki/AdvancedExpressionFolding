package com.intellij.advancedExpressionFolding.domain.service

import com.intellij.advancedExpressionFolding.domain.repository.AdvancedExpressionKeyRepository
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiRecursiveElementVisitor

class KeyCleanupService(private val repository: AdvancedExpressionKeyRepository) {
    fun clear(element: PsiElement) {
        element.accept(object : PsiRecursiveElementVisitor() {
            override fun visitElement(element: PsiElement) {
                repository.clear(element)
                super.visitElement(element)
            }
        })
    }
}
