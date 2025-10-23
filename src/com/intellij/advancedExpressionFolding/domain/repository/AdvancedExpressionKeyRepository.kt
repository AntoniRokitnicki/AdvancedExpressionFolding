package com.intellij.advancedExpressionFolding.domain.repository

import com.intellij.psi.PsiElement

fun interface AdvancedExpressionKeyRepository {
    fun clear(element: PsiElement)
}
