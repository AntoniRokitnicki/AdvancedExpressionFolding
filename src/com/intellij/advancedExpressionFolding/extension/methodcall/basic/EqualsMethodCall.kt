package com.intellij.advancedExpressionFolding.extension.methodcall.basic

import com.intellij.advancedExpressionFolding.expression.Equal
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class EqualsMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = comparingExpressionsCollapse

    override val methodNames by lazy { listOf("equals") }
    
    override val classNames by lazy { listOf(
        "java.lang.Object",
        "java.lang.String",
        "java.math.BigDecimal",
        "java.math.BigInteger",
        "java.lang.Long",
        "java.lang.Integer",
        "java.lang.Float",
        "java.lang.Double",
        "java.lang.Character"
    ) }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = Equal(element, element.textRange, context.getOperands())

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? = Equal(element, element.textRange, listOf(a1Expression, a2Expression))
}
