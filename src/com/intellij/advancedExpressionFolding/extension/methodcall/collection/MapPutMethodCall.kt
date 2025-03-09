package com.intellij.advancedExpressionFolding.extension.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Put
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.on
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiStatement

class MapPutMethodCall : AbstractMethodCall() {

    override fun permission() = getExpressionsCollapse

    override val methodNames by lazy { listOf("set", "put", "setProperty", "setAttribute", "setValue") }

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? {
        context.qualifierExpression ?: return null
        return (element.parent is PsiStatement).on()?.let {
            Put(element, element.textRange, context.qualifierExpression, a1Expression, a2Expression)
        }
    }

}