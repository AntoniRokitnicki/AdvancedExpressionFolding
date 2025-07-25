package com.intellij.advancedExpressionFolding.processor.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.SetLiteral
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class CollectionsUnmodifiableSetMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = getExpressionsCollapse

    override val methodNames by lazy { listOf("unmodifiableSet") }
    
    override val classNames by lazy { listOf("java.util.Collections") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (argumentExpression is SetLiteral) {
            return SetLiteral(
                element,
                element.textRange,
                argumentExpression.firstBracesRange,
                argumentExpression.secondBracesRange,
                argumentExpression.operands
            )
        }
        return null
    }
}
