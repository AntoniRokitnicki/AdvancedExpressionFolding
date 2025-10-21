package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

abstract class BuildExpression<T : PsiElement>(
    private val elementType: Class<T>,
) : BaseExtension() {

    abstract fun buildExpression(element: T, document: Document, synthetic: Boolean): Expression?

    @Suppress("UNCHECKED_CAST")
    private fun canProcess(element: PsiElement): Boolean =
        elementType.isInstance(element) && checkConditions(element as T)

    protected open fun checkConditions(element: T): Boolean = true

    @Suppress("UNCHECKED_CAST")
    fun tryBuildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? =
        if (canProcess(element)) buildExpression(element as T, document, synthetic) else null
}

fun tryBuildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? {
    return ExpressionBuilderManager.expressionBuilders
        .firstNotNullOfOrNull {
            it.tryBuildExpression(element, document, synthetic)
        }
}
