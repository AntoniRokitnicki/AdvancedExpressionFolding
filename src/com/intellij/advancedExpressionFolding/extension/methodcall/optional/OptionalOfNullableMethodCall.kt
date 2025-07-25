package com.intellij.advancedExpressionFolding.extension.methodcall.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalOfNullable
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.util.Helper
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class OptionalOfNullableMethodCall : AbstractOptionalMethodCall() {
    override val methodNames by lazy { listOf("ofNullable") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (Helper.hasOptionalChainOperations(element)) {
            return OptionalOfNullable(
                element,
                element.textRange,
                context.getOperands()
            )
        }
        return null
    }
}
