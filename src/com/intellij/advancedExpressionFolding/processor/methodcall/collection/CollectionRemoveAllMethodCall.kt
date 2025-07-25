package com.intellij.advancedExpressionFolding.processor.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.RemoveAssignForCollection
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class CollectionRemoveAllMethodCall : AbstractCollectionMethodCall() {
    override fun permission() = concatenationExpressionsCollapse

    override val methodNames by lazy { listOf("removeAll") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? =
        RemoveAssignForCollection(
            element,
            element.textRange,
            context.getOperands()
        )
}
