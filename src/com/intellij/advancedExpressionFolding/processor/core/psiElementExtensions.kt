package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

fun getAnyExpression(
    element: PsiElement,
    document: Document = element.containingFile.viewProvider.document,
): Expression = BuildExpressionExt.getAnyExpression(element, document)

fun getNonSyntheticExpression(
    element: PsiElement,
    document: Document = element.containingFile.viewProvider.document,
): Expression? = BuildExpressionExt.getNonSyntheticExpression(element, document)

fun <T : PsiElement?> getAnyExpressions(
    expressions: Array<T?>?,
    document: Document? = expressions?.firstOrNull()?.containingFile?.viewProvider?.document,
): List<Expression> = expressions
    ?.filterNotNull()
    ?.map { psiElement ->
        val resolvedDocument = document ?: psiElement.containingFile.viewProvider.document
        getAnyExpression(psiElement, resolvedDocument)
    }
    ?: emptyList()
