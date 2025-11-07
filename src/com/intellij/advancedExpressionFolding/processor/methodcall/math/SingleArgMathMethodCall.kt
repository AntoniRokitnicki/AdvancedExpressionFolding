package com.intellij.advancedExpressionFolding.processor.methodcall.math

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class SingleArgMathMethodCall(
    private val names: List<String>,
    private val builder: (PsiMethodCallExpression, Context, PsiExpression, Expression) -> Expression?,
) : AbstractMathMethodCall() {

    constructor(
        vararg methodNames: String,
        builder: (PsiMethodCallExpression, Context, PsiExpression, Expression) -> Expression?,
    ) : this(methodNames.toList(), builder)

    override val methodNames: List<String> = names

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression,
    ): Expression? = builder(element, context, argument, argumentExpression)
}
