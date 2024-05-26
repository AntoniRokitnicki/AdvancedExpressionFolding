package com.intellij.advancedExpressionFolding.extension.methodcall.date

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Greater
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class IsAfterDateMethodCall : AbstractDateMethodCall() {
    override fun permission() = comparingLocalDatesCollapse

    override val methodNames by lazy { listOf("isAfter", "after") }

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ) = Greater(element, element.textRange, context.getOperands())

}