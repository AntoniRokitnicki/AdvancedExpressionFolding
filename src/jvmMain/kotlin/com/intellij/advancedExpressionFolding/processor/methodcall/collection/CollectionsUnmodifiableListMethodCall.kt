package com.intellij.advancedExpressionFolding.processor.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.ListLiteral
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class CollectionsUnmodifiableListMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun canExecute() = getExpressionsCollapse

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
