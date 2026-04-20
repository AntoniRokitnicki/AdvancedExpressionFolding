package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

open class FunctionalExpressionBuilder<T : PsiElement>(
    val elementClass: Class<T>,
    private val checkConditions: (FunctionalExpressionBuilder<T>.(T) -> Boolean)? = null,
    private val build: FunctionalExpressionBuilder<T>.(T, Document, Boolean) -> Expression?,
) : BuildExpression<T>(elementClass) {

    constructor(definition: FunctionalExpressionBuilderDefinition<T>) : this(
        definition.elementClass,
        definition.check,
        definition.build,
    )

    override fun checkConditions(element: T): Boolean =
        checkConditions?.invoke(this, element) ?: true

    override fun buildExpression(
        element: T,
        document: Document,
        synthetic: Boolean,
    ): Expression? = build(this, element, document, synthetic)
}

data class FunctionalExpressionBuilderDefinition<T : PsiElement>(
    val elementClass: Class<T>,
    val check: (FunctionalExpressionBuilder<T>.(T) -> Boolean)? = null,
    val build: FunctionalExpressionBuilder<T>.(T, Document, Boolean) -> Expression?,
)
