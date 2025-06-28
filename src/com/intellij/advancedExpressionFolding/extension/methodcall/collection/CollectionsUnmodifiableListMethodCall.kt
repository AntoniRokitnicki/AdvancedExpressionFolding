package com.intellij.advancedExpressionFolding.extension.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.ListLiteral
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class CollectionsUnmodifiableListMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = getExpressionsCollapse

    override val methodNames by lazy { listOf("unmodifiableList") }
    
    override val classNames by lazy { listOf("java.util.Collections") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (argumentExpression is ListLiteral) {
            return ListLiteral(
                element, 
                element.textRange,
                argumentExpression.items
            )
        }
        return null
    }
}
