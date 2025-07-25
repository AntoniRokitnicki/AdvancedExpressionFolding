package com.intellij.advancedExpressionFolding.processor.methodcall.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.Get
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class CharAtMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = getExpressionsCollapse

    override val methodNames by lazy { listOf("charAt") }
    
    override val classNames by lazy { listOf("java.lang.String") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        return Get(
            element,
            element.textRange,
            context.qualifierExpr,
            argumentExpression,
            Get.Style.NORMAL
        )
    }
}
