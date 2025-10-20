package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.StateDelegate
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

abstract class BaseExtension : StateDelegate() {

    fun getAnyExpression(element: PsiElement, document: Document = element.containingFile.viewProvider.document): Expression =
        BuildExpressionExt.getAnyExpression(element, document)

    fun getNonSyntheticExpression(element: PsiElement, document: Document = element.containingFile.viewProvider.document): Expression? =
        BuildExpressionExt.getNonSyntheticExpression(element, document)

    fun <T : PsiElement?> getAnyExpressions(
        expressions: Array<T?>?,
        document: Document? = expressions?.firstOrNull()?.containingFile?.viewProvider?.document,
    ): List<Expression> {
        val filtered = expressions?.filterNotNull() ?: return emptyList()
        if (filtered.isEmpty()) {
            return emptyList()
        }
        val resolvedDocument = document ?: filtered.first().containingFile.viewProvider.document
        return filtered.map { getAnyExpression(it, resolvedDocument) }
    }

}
