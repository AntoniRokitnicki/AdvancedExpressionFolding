package com.intellij.advancedExpressionFolding.extension.lombok

import com.intellij.advancedExpressionFolding.extension.MethodBodyInspector.isDirtyGetter
import com.intellij.advancedExpressionFolding.extension.MethodBodyInspector.isDirtySetter
import com.intellij.psi.PsiMethod

enum class MethodType {
    GETTER {
        override fun isDirty(method: PsiMethod) = method.isDirtyGetter()

        override fun createFieldArgument(dirty: Boolean): String? = if (dirty) {
            "dirty"
        } else {
            null
        }

    },
    SETTER {
        override fun isDirty(method: PsiMethod) = method.isDirtySetter()

        override fun createFieldArgument(dirty: Boolean): String? = if (dirty) {
            "dirty"
        } else {
            null
        }
    },
    TO_STRING,
    EQUALS,
    HASHCODE,

    FIND_BY,
    OTHER,

    ;

    open fun isDirty(method: PsiMethod): Boolean = false
    open fun createFieldArgument(dirty: Boolean): String? = null
}


