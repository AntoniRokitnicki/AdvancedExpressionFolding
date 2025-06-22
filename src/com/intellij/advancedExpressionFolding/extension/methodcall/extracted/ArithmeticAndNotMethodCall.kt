package com.intellij.advancedExpressionFolding.extension.methodcall.extracted

import com.intellij.advancedExpressionFolding.expression.And
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Not
import com.intellij.advancedExpressionFolding.extension.methodcall.Context

import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import java.util.*

class ArithmeticAndNotMethodCall : AbstractArithmeticMethodCall() {
    override val methodNames by lazy { listOf("andNot") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        val qualifier = context.qualifierExpr
        val notExpr = Not(element, argumentExpression.textRange, Collections.singletonList(argumentExpression))
        return And(element, element.textRange, listOf(qualifier, notExpr))
    }
}
