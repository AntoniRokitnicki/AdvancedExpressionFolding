package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.StateDelegate
import com.intellij.advancedExpressionFolding.util.withDocument
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

abstract class BaseExtension : StateDelegate() {

    fun getAnyExpression(element: PsiElement, document: Document = element.containingFile.viewProvider.document): Expression =
        withDocument(document) {
            getAnyExpression(element)
        }

    context(editorDocument: Document)
    fun getAnyExpression(element: PsiElement): Expression =
        BuildExpressionExt.getAnyExpression(element, editorDocument)

    fun getNonSyntheticExpression(
        element: PsiElement,
        document: Document = element.containingFile.viewProvider.document,
    ): Expression? =
        withDocument(document) {
            getNonSyntheticExpression(element)
        }

    context(editorDocument: Document)
    fun getNonSyntheticExpression(element: PsiElement): Expression? =
        BuildExpressionExt.getNonSyntheticExpression(element, editorDocument)

    fun <T : PsiElement?> getAnyExpressions(
        expressions: Array<T?>?,
        document: Document? = expressions?.firstOrNull()?.containingFile?.viewProvider?.document,
    ): List<Expression> = document?.let { resolved ->
        withDocument(resolved) {
            getAnyExpressions(expressions)
        }
    } ?: emptyList()

    context(editorDocument: Document)
    fun <T : PsiElement?> getAnyExpressions(expressions: Array<T?>?): List<Expression> =
        expressions?.filterNotNull()?.map { getAnyExpression(it) } ?: emptyList()

}
