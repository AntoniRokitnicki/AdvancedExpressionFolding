@file:Suppress("UnstableApiUsage")

package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings
import com.intellij.lang.jvm.types.JvmPrimitiveTypeKind
import com.intellij.psi.PsiPrimitiveType
import com.intellij.psi.PsiType

abstract class BaseExtension : AdvancedExpressionFoldingSettings.StateDelegate() {

    companion object {

        @JvmStatic
        @SuppressWarnings("deprecation") // since 231.*
        fun PsiType?.isNull() : Boolean = this is PsiPrimitiveType && this == PsiType.NULL

        @JvmStatic
        fun PsiType?.isInt() : Boolean = (this as? PsiPrimitiveType)?.kind == JvmPrimitiveTypeKind.INT
    }

    fun PsiType?.isVoid() : Boolean = (this as? PsiPrimitiveType)?.kind == JvmPrimitiveTypeKind.VOID


}
