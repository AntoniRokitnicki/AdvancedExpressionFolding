package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Negate
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticNegateMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { listOf("negate") }
    
    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? = Negate(
        element,
        element.textRange,
        context.getOperands()
    )
}
