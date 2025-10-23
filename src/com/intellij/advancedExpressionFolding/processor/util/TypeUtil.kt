package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField

object TypeUtil {

    fun eraseGenerics(signature: String): String {
        var result = signature
        var matcher = Consts.GENERICS_PATTERN.matcher(result)
        while (matcher.find()) {
            result = matcher.replaceAll("")
            matcher = Consts.GENERICS_PATTERN.matcher(result)
        }
        return result
    }

    fun isSupportedClass(element: PsiElement): Boolean {
        val resolved = element.reference?.resolve()
        if (resolved is PsiField) {
            val psiClass = resolved.containingClass
            if (psiClass?.qualifiedName != null) {
                return Consts.SUPPORTED_CLASSES.contains(eraseGenerics(psiClass.qualifiedName!!))
            }
        }
        return false
    }
}
