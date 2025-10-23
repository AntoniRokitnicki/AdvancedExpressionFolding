package com.intellij.advancedExpressionFolding.processor.methodcall.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.Collect
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.util.MethodNameUtil
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression
import java.util.Objects

class StreamCollectMethodCall : AbstractStreamMethodCall() {
    override fun canExecute() = concatenationExpressionsCollapse

    override val methodNames by lazy { listOf("collect") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (argument is PsiMethodCallExpression
            && MethodNameUtil.startsWith((argument.methodExpression).referenceName, "to")) {
            val q = argument.methodExpression.qualifierExpression
            if (q is PsiReferenceExpression && Objects.equals(q.referenceName, "Collectors")) {
                val identifier = argument.methodExpression.children
                    .firstOrNull { it is PsiIdentifier && it.text.startsWith("to") } as? PsiIdentifier
                if (identifier != null) {
                    // This is a special case that takes a TextRange parameter
                    return Collect(
                        element,
                        TextRange.create(context.identifier.textRange.startOffset, element.textRange.endOffset),
                        context.qualifierExpr,
                        TextRange.create(identifier.textRange.startOffset, argument.textRange.endOffset)
                    )
                }
            }
        }
        return null
    }
}
