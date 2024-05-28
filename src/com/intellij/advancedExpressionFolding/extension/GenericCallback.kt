package com.intellij.advancedExpressionFolding.extension

import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement

interface GenericCallback<CallbackOnType: PsiElement, CallbackReturn> {
    val callbackKey: Key<() -> CallbackReturn>

    var CallbackOnType.callback: (() -> CallbackReturn)?
        get() = getUserData(callbackKey)
        set(value) = putUserData(callbackKey, value)

}
