package com.intellij.advancedExpressionFolding.extension.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Signum
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticSignumMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { listOf("signum") }
    
    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? = Signum(
        element,
        element.textRange,
        context.getOperands()
    )
}
