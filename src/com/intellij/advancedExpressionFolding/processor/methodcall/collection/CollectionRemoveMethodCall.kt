package com.intellij.advancedExpressionFolding.processor.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.RemoveAssignForCollection
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.isInt
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiStatement

class CollectionRemoveMethodCall : AbstractCollectionMethodCall() {
    override fun canExecute() = concatenationExpressionsCollapse
    
    override val methodNames by lazy { listOf("remove") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (context.method.parameterList.parameters.size == 1 
            && !context.method.parameterList.parameters[0].type.isInt()
            && element.parent is PsiStatement) {
            return RemoveAssignForCollection(
                element,
                element.textRange,
                context.getOperands()
            )
        }
        return null
    }
}
