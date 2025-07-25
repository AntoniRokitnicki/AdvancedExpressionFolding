package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.extension.lombok.LombokExt.getNonSyntheticExpression
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement

interface GenericCallback<CallbackOnType: PsiElement, CallbackReturn> {
    val callbackKey: Key<() -> CallbackReturn>

    var CallbackOnType.callback: (() -> CallbackReturn)?
        get() = getUserData(callbackKey)
        set(value) = putUserData(callbackKey, value)

    fun initCallback(psiElement: CallbackOnType, annotations: CallbackReturn) {
        try {
            psiElement.callback = {
                annotations
            }
            getNonSyntheticExpression(psiElement)
        } finally {
            psiElement.callback = null
        }
    }

}
