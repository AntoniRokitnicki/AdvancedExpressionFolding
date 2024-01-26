package com.intellij.advancedExpressionFolding.extension

import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod

data class JavaProperty(val field: PsiField, val methods: List<PsiMethod>) {
    fun hasGetterOrSetter(): Boolean = methods.count() == 2 //TODO: extend check
}
