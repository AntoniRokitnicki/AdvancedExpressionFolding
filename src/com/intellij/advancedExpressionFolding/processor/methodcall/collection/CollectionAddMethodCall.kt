package com.intellij.advancedExpressionFolding.processor.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.AddAssignForCollection
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiStatement

class CollectionAddMethodCall : AbstractCollectionMethodCall() {
    override fun canExecute() = concatenationExpressionsCollapse
    
    override val methodNames by lazy { methodNames("add") }
    
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
