package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Abs
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticAbsMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { methodNames("abs") }
    
    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? = Abs(
        element,
        element.textRange,
        context.getOperands()
    )
}
