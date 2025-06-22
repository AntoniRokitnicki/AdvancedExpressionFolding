package com.intellij.advancedExpressionFolding.extension.methodcall.extracted

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.RemoveAssignForCollection
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiStatement

class CollectionRemoveMethodCall : AbstractCollectionMethodCall() {
    override fun permission() = concatenationExpressionsCollapse
    
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
            return RemoveAssignForCollection(element, element.textRange, context.getOperands())
        }
        return null
    }
}