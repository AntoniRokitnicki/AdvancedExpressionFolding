package com.intellij.advancedExpressionFolding.extension.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Min
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticMinMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { listOf("min") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (context.className == "java.util.stream.Stream") {
            return null
        }
        return Min(
            element,
            element.textRange,
            context.getOperands()
        )
    }
}
