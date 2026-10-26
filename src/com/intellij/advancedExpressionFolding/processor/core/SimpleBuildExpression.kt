package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

open class SimpleBuildExpression<T : PsiElement>(
    elementClass: Class<T>,
    private val predicate: SimpleBuildExpression<T>.(T) -> Boolean = { true },
    private val builder: SimpleBuildExpression<T>.(T, Document, Boolean) -> Expression?,
) : BuildExpression<T>(elementClass) {

    override fun checkConditions(element: T): Boolean = predicate(element)

    override fun buildExpression(element: T, document: Document, synthetic: Boolean): Expression? =
        builder(element, document, synthetic)
}

fun <T : PsiElement> simpleBuildExpression(
    elementClass: Class<T>,
    predicate: SimpleBuildExpression<T>.(T) -> Boolean = { true },
    builder: SimpleBuildExpression<T>.(T, Document, Boolean) -> Expression?,
): SimpleBuildExpression<T> = SimpleBuildExpression(elementClass, predicate, builder)

inline fun <reified T : PsiElement> simpleBuildExpression(
    noinline predicate: SimpleBuildExpression<T>.(T) -> Boolean = { true },
    noinline builder: SimpleBuildExpression<T>.(T, Document, Boolean) -> Expression?,
): SimpleBuildExpression<T> = simpleBuildExpression(T::class.java, predicate, builder)

inline fun <reified T : PsiElement> registerBuilder(
    noinline predicate: SimpleBuildExpression<T>.(T) -> Boolean = { true },
    noinline builder: SimpleBuildExpression<T>.(T, Document, Boolean) -> Expression?,
): SimpleBuildExpression<T> = simpleBuildExpression(predicate, builder)
