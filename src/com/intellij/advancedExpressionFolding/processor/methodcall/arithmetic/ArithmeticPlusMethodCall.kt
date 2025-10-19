package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticPlusMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { methodNames("plus") }
    
    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? = context.qualifierExprNullable

}
