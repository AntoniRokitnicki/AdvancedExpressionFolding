package com.intellij.advancedExpressionFolding.extension.methodcall.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.optional.OptionalMapCall
import com.intellij.advancedExpressionFolding.expression.optional.OptionalMapSafeCall
import com.intellij.advancedExpressionFolding.expression.optional.OptionalMapSafeCallParam
import com.intellij.advancedExpressionFolding.expression.optional.OptionalOf
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class OptionalMapMethodCall : AbstractOptionalMethodCall() {
    override val methodNames by lazy { listOf("map", "flatMap") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (argumentExpression is OptionalMapSafeCallParam) {
            val flatMap = context.methodName == "flatMap"
            val qualifier = context.qualifierExpr
            
            return if (qualifier is OptionalOf) {
                OptionalMapCall(element, element.textRange, context.getOperands(), flatMap)
            } else {
                OptionalMapSafeCall(element, element.textRange, context.getOperands(), flatMap)
            }
        }
        return null
    }
}
