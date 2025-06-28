package com.intellij.advancedExpressionFolding.extension.methodcall.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.optional.OptionalOf
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class OptionalOfMethodCall : AbstractOptionalMethodCall() {
    override val methodNames by lazy { listOf("of") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = OptionalOf(element, element.textRange, context.getOperands())
}