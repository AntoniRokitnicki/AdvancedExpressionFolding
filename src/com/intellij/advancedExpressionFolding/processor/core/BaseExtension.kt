package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.settings.StateDelegate
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiPrimitiveType
import com.intellij.psi.PsiType
import com.intellij.psi.impl.source.PsiClassReferenceType

abstract class BaseExtension : StateDelegate() {

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
        fun PsiType?.isPrimitiveOrString() = isPrimitive() || isString()
        fun PsiType?.isObject() = this?.canonicalText == "java.lang.Object"
    }

    fun getAnyExpression(element: PsiElement, document: Document = element.containingFile.viewProvider.document): Expression =
        BuildExpressionExt.getAnyExpression(element, document)

    fun getNonSyntheticExpression(element: PsiElement, document: Document = element.containingFile.viewProvider.document): Expression? =
        BuildExpressionExt.getNonSyntheticExpression(element, document)

    fun <T : PsiElement?> getAnyExpressions(
        expressions: Array<T?>?,
        document: Document? = expressions?.firstOrNull()?.containingFile?.viewProvider?.document,
    ): List<Expression> = expressions?.filterNotNull()?.map {
        getAnyExpression(it, document!!)
    } ?: emptyList()

}
