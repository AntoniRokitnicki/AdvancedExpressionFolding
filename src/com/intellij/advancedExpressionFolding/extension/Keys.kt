package com.intellij.advancedExpressionFolding.extension

import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod

object Keys {
    private const val PREFIX = "AEF-"
    val BUILDER = Key<Boolean>("${PREFIX}builder")
    val CLASS_TYPE_KEY = Key<PsiClassExt.ClassType>("${PREFIX}classType")

    val GETTER_KEY = Key<PsiMethod>("${PREFIX}getter")
    val SETTER_KEY = Key<PsiMethod>("${PREFIX}setter")

    val IGNORED = Key<Boolean>("${PREFIX}ignored")


    fun Key<Boolean>.isOn(element: PsiElement) : Boolean = element.getUserData(this) ?: false
    fun Key<Boolean>.turnOn(element: PsiElement)  = element.putUserData(this, true)
}