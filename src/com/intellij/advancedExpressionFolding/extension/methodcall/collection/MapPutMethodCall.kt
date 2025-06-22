package com.intellij.advancedExpressionFolding.extension.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Put
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier
import com.intellij.advancedExpressionFolding.extension.on
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiStatement

class MapPutMethodCall : AbstractMethodCall(), NeedsQualifier {

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
        return (element.parent is PsiStatement).on()?.let {
            Put(element, element.textRange, context.qualifierExpr, a1Expression, a2Expression)
        }
    }

}
