package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

open class ConfiguredArithmeticMethodCall(
    override val methodNames: List<String>,
    private val argumentArity: ArgumentArity,
    private val expressionBuilder: (PsiMethodCallExpression, Context) -> Expression?
) : AbstractArithmeticMethodCall() {

    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? = buildIfMatches(ArgumentArity.NO_ARGUMENTS, element, context)

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = buildIfMatches(ArgumentArity.SINGLE_ARGUMENT, element, context)

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? = buildIfMatches(ArgumentArity.TWO_ARGUMENTS, element, context)

    private fun buildIfMatches(
        expected: ArgumentArity,
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? =
        if (argumentArity == expected) {
            expressionBuilder(element, context)
        } else {
            null
        }

    enum class ArgumentArity {
        NO_ARGUMENTS,
        SINGLE_ARGUMENT,
        TWO_ARGUMENTS,
    }
}
