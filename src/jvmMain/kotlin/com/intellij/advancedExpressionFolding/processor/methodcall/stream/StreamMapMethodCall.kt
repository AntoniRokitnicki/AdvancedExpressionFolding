package com.intellij.advancedExpressionFolding.processor.methodcall.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.stream.StreamMapCall
import com.intellij.advancedExpressionFolding.expression.operation.stream.StreamMapCallParam
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class StreamMapMethodCall : AbstractStreamMethodCall() {
    override val methodNames by lazy { listOf("map", "flatMap") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (argumentExpression is StreamMapCallParam) {
            val flatMap = context.methodName == "flatMap"
            val textRange = TextRange(context.identifier.textRange.startOffset, 
                                     element.textRange.endOffset)
            
            return StreamMapCall(element, textRange, context.getOperands(), flatMap)
        }
        return null
    }
}
