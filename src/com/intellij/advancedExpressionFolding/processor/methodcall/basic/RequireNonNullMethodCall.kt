package com.intellij.advancedExpressionFolding.processor.methodcall.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.language.kotlin.PreconditionExt
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class RequireNonNullMethodCall : AbstractMethodCall() {
    override val methodNames by lazy { listOf("requireNonNull") }

    override val classNames by lazy { listOf("java.util.Objects") }

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = PreconditionExt.getRequireNonNullExpression(element, argument, null, context.document)

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? = PreconditionExt.getRequireNonNullExpression(element, a1, a2, context.document)
}
