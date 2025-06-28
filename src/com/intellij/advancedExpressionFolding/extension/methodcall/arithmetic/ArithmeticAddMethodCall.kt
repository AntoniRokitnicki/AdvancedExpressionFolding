package com.intellij.advancedExpressionFolding.extension.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Add
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticAddMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { listOf("add") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = Add(element, element.textRange, context.getOperands())
}