package com.intellij.advancedExpressionFolding.processor.lombok

import com.intellij.advancedExpressionFolding.processor.lombok.MethodBodyInspector.asDirtyNoReference
import com.intellij.advancedExpressionFolding.processor.lombok.MethodBodyInspector.asLazyGetter
import com.intellij.advancedExpressionFolding.processor.lombok.MethodBodyInspector.asNewInstanceWrapperGetter
import com.intellij.advancedExpressionFolding.processor.lombok.MethodBodyInspector.asWrapperGetter
import com.intellij.advancedExpressionFolding.processor.lombok.MethodBodyInspector.isDirtyGetter
import com.intellij.advancedExpressionFolding.processor.lombok.MethodBodyInspector.isDirtySetter
import com.intellij.advancedExpressionFolding.processor.lombok.MethodBodyInspector.hasSetterNullCheck
import com.intellij.advancedExpressionFolding.processor.lombok.MethodBodyInspector.setterHasPlainAssignmentWithNullCheck
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod

enum class MethodType {
    GETTER {
        override fun isDirty(method: PsiMethod) = method.isDirtyGetter()

        override fun createFieldArgument(dirty: Boolean, field: PsiField, method: PsiMethod): String? = if (dirty) {
            method.asWrapperGetter(field) ?:
            method.asNewInstanceWrapperGetter(field) ?:
            method.asLazyGetter(field) ?:
            method.asDirtyNoReference(field) ?:
            "dirty"
        } else {
            null
        }

    },
    SETTER {
        override fun isDirty(method: PsiMethod) = method.isDirtySetter()

        override fun createFieldArgument(dirty: Boolean, field: PsiField, method: PsiMethod): String? = if (dirty) {
            if (method.hasSetterNullCheck()) {
                if (method.setterHasPlainAssignmentWithNullCheck(field)) {
                    "onParam_ = @NonNull"
                } else {
                    val dirtyArgument = method.asDirtyNoReference(field) ?: "dirty"
                    "$dirtyArgument, onParam_ = @NonNull"
                }
            } else {
                method.asDirtyNoReference(field) ?:
                "dirty"
            }
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
    open fun createFieldArgument(dirty: Boolean, method: PsiField, method1: PsiMethod): String? = null
}


