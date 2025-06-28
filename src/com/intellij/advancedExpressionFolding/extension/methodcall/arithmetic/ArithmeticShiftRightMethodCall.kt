package com.intellij.advancedExpressionFolding.extension.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.ShiftRight
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class ArithmeticShiftRightMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { listOf("shiftRight") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = ShiftRight(element, element.textRange, context.getOperands())
}