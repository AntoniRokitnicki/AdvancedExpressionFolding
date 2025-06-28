package com.intellij.advancedExpressionFolding.extension.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticPlusMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { listOf("plus") }
    
    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? = context.qualifierExprNullable

}
