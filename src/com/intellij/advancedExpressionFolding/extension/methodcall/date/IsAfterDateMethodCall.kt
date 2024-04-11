package com.intellij.advancedExpressionFolding.extension.methodcall.date

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Greater
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class IsAfterDateMethodCall : AbstractDateMethodCall() {
    override fun permission(): Boolean = comparingLocalDatesCollapse

    override fun methodName() = "isAfter"

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression =
        Greater(element, element.textRange, context.getOperands(argumentExpression))

}