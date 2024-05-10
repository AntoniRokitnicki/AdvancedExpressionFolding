@file:Suppress("UnstableApiUsage")

package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.HideExpression
import com.intellij.advancedExpressionFolding.expression.custom.SimpleExpression
import com.intellij.advancedExpressionFolding.expression.custom.WrapAroundExpression
import com.intellij.advancedExpressionFolding.expression.custom.WrapperExpression
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.*
import com.intellij.psi.impl.source.PsiClassReferenceType

abstract class BaseExtension : AdvancedExpressionFoldingSettings.StateDelegate() {

    companion object {

        // workaround for @ScheduledForRemoval on fields of PsiType since 231.* (a new class PsiTypes is not available in 211)
        @JvmStatic
        fun PsiType?.isNull(): Boolean = (this as? PsiPrimitiveType)?.name == "null"

        @JvmStatic
        fun PsiType?.isInt(): Boolean = (this as? PsiPrimitiveType)?.name == "int"
        fun PsiType?.isVoid(): Boolean = (this as? PsiPrimitiveType)?.name == "void"
        fun PsiType?.isBoolean(): Boolean = (this as? PsiPrimitiveType)?.name == "boolean"
        fun PsiType?.isString() = asInstance<PsiClassReferenceType>()?.name == "String"
        fun PsiType?.isPrimitive() = asInstance<PsiPrimitiveType>() != null
        fun PsiType?.isObject() = this?.canonicalText == "java.lang.Object"
    }

    fun getAnyExpression(element: PsiElement, document: Document = element.containingFile.viewProvider.document): Expression =
        BuildExpressionExt.getAnyExpression(element, document)

    fun getAnyExpressions(
        expressions: Array<out PsiExpression>,
        context: Context
    ) = expressions.map { getAnyExpression(it, context.document) }


    fun PsiElement.expr(
        text: String,
        vararg children: Expression?,
        group: FoldingGroup? = null,
        foldPrevWhiteSpace: Boolean = false
    ): SimpleExpression? {
        textRange.isEmpty.takeIf {
            !it
        } ?: return null

        return SimpleExpression(
            this,
            *children,
            text = text,
            textRange = textRange,
            group = group,
            foldPrevWhiteSpace = foldPrevWhiteSpace)
    }
    fun PsiElement.exprHide(
        vararg children: Expression?,
        group: FoldingGroup? = null,
        foldPrevWhiteSpace: Boolean = false
    ): HideExpression? {
        textRange.isEmpty.takeIf {
            !it
        } ?: return null

        return HideExpression(
            this,
            textRange,
            *children,
            group = group,
            foldPrevWhiteSpace = foldPrevWhiteSpace)
    }

    fun PsiElement.exprWrapAround(
        vararg children: Expression?,
        group: FoldingGroup? = null,
        textBefore: String? = null,
        foldPrevWhiteSpace: Boolean = false,
        //TODO:
        textAfter: String? = null,
        foldNextWhiteSpace: Boolean = false
    ) =
        WrapAroundExpression(
            this,
            textRange,
            *children,
            group = group,
            textBefore = textBefore,
            foldPrevWhiteSpace = foldPrevWhiteSpace,
            textAfter = textAfter,
            foldNextWhiteSpace = foldNextWhiteSpace
        )

    fun Collection<Expression?>.exprWrap(
        field: PsiField,
    ) = WrapperExpression(field, chain = filterNotNull())

}
