package com.intellij.advancedExpressionFolding.extension.methodcall.extracted

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.optional.OptionalOfNullable
import com.intellij.advancedExpressionFolding.extension.Helper
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
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
            return OptionalOfNullable(element, element.textRange, context.getOperands())
        }
        return null
    }
}