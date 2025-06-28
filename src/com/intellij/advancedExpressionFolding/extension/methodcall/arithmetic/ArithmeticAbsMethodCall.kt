package com.intellij.advancedExpressionFolding.extension.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Abs
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticAbsMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { listOf("abs") }
    
    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? = Abs(element, element.textRange, context.getOperands())
}
