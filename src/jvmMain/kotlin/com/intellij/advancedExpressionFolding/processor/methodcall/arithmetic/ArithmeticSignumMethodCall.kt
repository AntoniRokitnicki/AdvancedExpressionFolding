package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Signum
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
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
