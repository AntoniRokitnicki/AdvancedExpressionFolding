package com.intellij.advancedExpressionFolding.processor.methodcall.arithmetic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Not
import com.intellij.advancedExpressionFolding.expression.math.bitwise.And
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import java.util.*

class ArithmeticAndNotMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { methodNames("andNot") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        val qualifier = context.qualifierExpr
        val notExpr = Not(
            element,
            argumentExpression.textRange,
            Collections.singletonList(argumentExpression)
        )
        return And(
            element,
            element.textRange,
            listOf(qualifier, notExpr)
        )
    }
}
