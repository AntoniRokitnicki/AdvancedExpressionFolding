package com.intellij.advancedExpressionFolding.extension

import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiType


val IGNORED = Key<Boolean>("aef-ignored")
fun PsiElement.isIgnored(): Boolean = getUserData(IGNORED) ?: false

fun PsiElement.markIgnored() = putUserData(IGNORED, true)

fun PsiMethod.isSetter(): Boolean {
    fun isSetter(text: String) = text.startsWith("set") && text.length > 3 && Character.isUpperCase(text[3])
    return parameterList.parametersCount == 1 && returnType == PsiType.VOID && isSetter(name)
}

fun PsiMethod.isGetter(): Boolean {
    fun isGetterAux(name: String, prefix: String) =
        name.startsWith(prefix) && name.length > prefix.length && Character.isUpperCase(name[prefix.length])

    fun isGetter(name: String) = isGetterAux(name, "get") || isGetterAux(name, "is")

    return parameterList.parametersCount == 0 && returnType != PsiType.VOID && isGetter(name)
}

fun PsiMethod.isGetterOrSetter(): Boolean = isSetter() || isGetter()