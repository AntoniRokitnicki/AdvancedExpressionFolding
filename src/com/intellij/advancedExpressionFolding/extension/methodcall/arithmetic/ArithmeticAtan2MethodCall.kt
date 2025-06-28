package com.intellij.advancedExpressionFolding.extension.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Atan2
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticAtan2MethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { listOf("atan2") }

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? = Atan2(element, element.textRange, context.getOperands())
}