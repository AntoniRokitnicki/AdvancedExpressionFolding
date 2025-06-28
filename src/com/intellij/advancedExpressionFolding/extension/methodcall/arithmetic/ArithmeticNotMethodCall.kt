package com.intellij.advancedExpressionFolding.extension.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Not
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticNotMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { listOf("not") }
    
    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? = Not(element, element.textRange, context.getOperands())
}
