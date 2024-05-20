package com.intellij.advancedExpressionFolding.extension.methodcall.date

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Less
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class IsBeforeDateMethodCall : AbstractDateMethodCall() {
    override fun permission(): Boolean = comparingLocalDatesCollapse

    override val methodNames: List<String> by lazy { listOf("isBefore", "before") }

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression =
        Less(element, element.textRange, context.getOperands())
}