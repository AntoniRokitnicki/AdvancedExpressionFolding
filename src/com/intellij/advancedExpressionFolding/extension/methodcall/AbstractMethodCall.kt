package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.BaseExtension
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

abstract class AbstractMethodCall : BaseExtension() {
    open fun permission(): Boolean = true

    //TODO: support priority

    open fun execute(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? {
        methodNames.firstOrNull {
            it == context.methodName
        } ?: return null

        if (classNames.isNotEmpty() && !classNames.contains(context.className)) {
            return null
        }
        val expressions = element.argumentList.expressions
        return onAnyArguments(element, context, expressions)
    }

    open fun onAnyArguments(
        element: PsiMethodCallExpression,
        context: Context,
        expressions: Array<PsiExpression>,
    ): Expression? {
        return when (expressions.size) {
            0 -> onNoArguments(element, context)
            1 -> {
                val (argument) = expressions
                val (argumentExpression) = context.argumentExpressions
                onSingleArgument(element, context, argument, argumentExpression)
            }
            2 -> {
                //TODO: rename vars after extraction
                val (a1, a2) = expressions
                val (a1Expression, a2Expression) = context.argumentExpressions
                onTwoArguments(element, context, a1, a2, a1Expression, a2Expression)
            }
            else -> onManyArguments(element, context, expressions)
        }
    }

    open fun onNoArguments(element: PsiMethodCallExpression, context: Context): Expression? = null
    open fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = null

    open fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? = null

    open fun onManyArguments(
        element: PsiMethodCallExpression,
        context: Context,
        expressions: Array<PsiExpression>
    ): Expression? = null

    open val methodNames: List<MethodName> by lazy { emptyList() }
    open val classNames: List<ClassName> by lazy { emptyList() }
}