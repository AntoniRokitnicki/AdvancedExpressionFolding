package com.intellij.advancedExpressionFolding.processor.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.AddAssignForCollection
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class CollectionAddAllMethodCall : AbstractCollectionMethodCall() {
    override fun canExecute() = concatenationExpressionsCollapse

    override val methodNames by lazy { listOf("addAll") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? =
        AddAssignForCollection(
            element,
            element.textRange,
            context.getOperands()
        )

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? =
        AddAssignForCollection(
            element,
            element.textRange,
            listOf(a1Expression, a2Expression)
        )
}
