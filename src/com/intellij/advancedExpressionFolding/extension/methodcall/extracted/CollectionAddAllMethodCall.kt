package com.intellij.advancedExpressionFolding.extension.methodcall.extracted

import com.intellij.advancedExpressionFolding.expression.AddAssignForCollection
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class CollectionAddAllMethodCall : AbstractCollectionMethodCall() {
    override fun permission() = concatenationExpressionsCollapse

    override val methodNames by lazy { listOf("addAll") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = AddAssignForCollection(element, element.textRange, context.getOperands())

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? = AddAssignForCollection(element, element.textRange, listOf(a1Expression, a2Expression))
}
