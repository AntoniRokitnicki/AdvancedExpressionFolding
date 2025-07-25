package com.intellij.advancedExpressionFolding.extension.methodcall.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.Collect
import com.intellij.advancedExpressionFolding.extension.Helper
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression
import java.util.*

class StreamCollectMethodCall : AbstractStreamMethodCall() {
    override fun permission() = concatenationExpressionsCollapse

    override val methodNames by lazy { listOf("collect") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (argument is PsiMethodCallExpression
            && Helper.startsWith((argument.methodExpression).referenceName, "to")) {
            val q = argument.methodExpression.qualifierExpression
            if (q is PsiReferenceExpression && Objects.equals(q.referenceName, "Collectors")) {
                val i = Arrays.stream(argument.methodExpression.children)
                    .filter { c -> c is PsiIdentifier && c.text.startsWith("to") }
                    .findAny()
                if (i.isPresent) {
                    // This is a special case that takes a TextRange parameter
                    return Collect(
                        element,
                        TextRange.create(context.identifier.textRange.startOffset, element.textRange.endOffset),
                        context.qualifierExpr,
                        TextRange.create(i.get().textRange.startOffset, argument.textRange.endOffset)
                    )
                }
            }
        }
        return null
    }
}
