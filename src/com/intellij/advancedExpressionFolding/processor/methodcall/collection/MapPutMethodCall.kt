package com.intellij.advancedExpressionFolding.processor.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.Put
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.advancedExpressionFolding.processor.on
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiStatement

class MapPutMethodCall : AbstractMethodCall(), NeedsQualifier {

    override fun canExecute() = getExpressionsCollapse

    override val methodNames by lazy { methodNames("set", "put", "setProperty", "setAttribute", "setValue") }

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? {
        return (element.parent is PsiStatement).on()?.let {
            Put(
                element,
                element.textRange,
                context.qualifierExpr,
                a1Expression,
                a2Expression
            )
        }
    }

}
