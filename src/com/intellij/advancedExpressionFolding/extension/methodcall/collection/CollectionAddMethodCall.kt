package com.intellij.advancedExpressionFolding.extension.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.operation.collection.AddAssignForCollection
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiStatement

class CollectionAddMethodCall : AbstractCollectionMethodCall() {
    override fun permission() = concatenationExpressionsCollapse
    
    override val methodNames by lazy { listOf("add") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (element.parent is PsiStatement) {
            return AddAssignForCollection(
                element,
                element.textRange,
                context.getOperands()
            )
        }
        return null
    }
}
