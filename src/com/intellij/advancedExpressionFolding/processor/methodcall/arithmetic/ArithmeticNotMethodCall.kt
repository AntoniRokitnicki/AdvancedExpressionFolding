package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Not
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticNotMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { methodNames("not") }
    
    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? = Not(
        element,
        element.textRange,
        context.getOperands()
    )
}
