@file:Suppress("UnstableApiUsage")

package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings
import com.intellij.lang.jvm.types.JvmPrimitiveTypeKind
import com.intellij.psi.PsiPrimitiveType
import com.intellij.psi.PsiType

abstract class BaseExtension : AdvancedExpressionFoldingSettings.StateDelegate() {

    companion object {

        // workaround for @ScheduledForRemoval on PsiType since 231.*
        @JvmStatic
        fun PsiType?.isNull() : Boolean = (this as PsiPrimitiveType).name == "null"

        // workaround for @ScheduledForRemoval on PsiType since 231.*
        @JvmStatic
        fun PsiType?.isInt() : Boolean = (this as? PsiPrimitiveType)?.kind == JvmPrimitiveTypeKind.INT
    }

    // workaround for @ScheduledForRemoval on PsiType since 231.*
    fun PsiType?.isVoid() : Boolean = (this as? PsiPrimitiveType)?.kind == JvmPrimitiveTypeKind.VOID


}
