package com.intellij.advancedExpressionFolding.extension.methodcall.date

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Less
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class BeforeDateMethodCall : AbstractDateMethodCall() {
    override fun permission() = comparingLocalDatesCollapse

    override val methodNames by lazy { listOf("isBefore", "before") }

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ) = Less(element, element.textRange, context.getOperands())
}