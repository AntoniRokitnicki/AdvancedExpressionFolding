package com.intellij.advancedExpressionFolding.extension.methodcall.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.SyntheticExpressionImpl
import com.intellij.advancedExpressionFolding.expression.stream.StreamFilterNotNull
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class StreamFilterMethodCall : AbstractStreamMethodCall() {
    override val methodNames by lazy { listOf("filter") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (argumentExpression is SyntheticExpressionImpl && argumentExpression.text == "Objects::nonNull") {
            val qualifier = context.qualifierExpr
            val start = qualifier.element.textRange.endOffset
            val end = element.textRange.endOffset
            return StreamFilterNotNull(element, TextRange(start, end), context.getOperands())
        }
        return null
    }
}
