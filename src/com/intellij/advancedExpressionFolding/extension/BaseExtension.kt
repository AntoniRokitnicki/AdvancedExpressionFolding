@file:Suppress("UnstableApiUsage")

package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiPrimitiveType
import com.intellij.psi.PsiType

abstract class BaseExtension : AdvancedExpressionFoldingSettings.StateDelegate() {

    companion object {

        // workaround for @ScheduledForRemoval on fields of PsiType since 231.* (a new class PsiTypes is not available in 211)
        @JvmStatic
        fun PsiType?.isNull(): Boolean = (this as? PsiPrimitiveType)?.name == "null"
        @JvmStatic
        fun PsiType?.isInt(): Boolean = (this as? PsiPrimitiveType)?.name == "int"
        fun PsiType?.isVoid(): Boolean = (this as? PsiPrimitiveType)?.name == "void"
    }

    fun getAnyExpression(element: PsiExpression, document: Document): Expression = BuildExpressionExt.getAnyExpression(element, document)

}
