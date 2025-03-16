package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.extension.clazz.LombokExt.getNonSyntheticExpression
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement

interface GenericCallback<CallbackOnType: PsiElement, CallbackReturn> {
    val callbackKey: Key<() -> CallbackReturn>

    var CallbackOnType.callback: (() -> CallbackReturn)?
        get() = getUserData(callbackKey)
        set(value) = putUserData(callbackKey, value)

    fun initCallback(field: CallbackOnType, annotations: CallbackReturn) {
        try {
            field.callback = {
                annotations
            }
            getNonSyntheticExpression(field)
        } finally {
            field.callback = null
        }
    }

}
