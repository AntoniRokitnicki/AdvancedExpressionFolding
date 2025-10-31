package com.intellij.advancedExpressionFolding.processor.methodcall.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.collection.ArrayStream
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression

class StreamMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun canExecute() = concatenationExpressionsCollapse

    override val methodNames by lazy { listOf("stream") }
    
    override val classNames by lazy { listOf(
        "java.util.List", 
        "java.util.ArrayList", 
        "java.util.Set", 
        "java.util.HashSet", 
        "java.util.Arrays"
    ) }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (element.parent is PsiReferenceExpression &&
            (element.parent as PsiReferenceExpression).qualifierExpression == element) {
            
            return ArrayStream(
                element, TextRange.create(
                    element.textRange.startOffset, element.textRange.endOffset
                ), argumentExpression
            )
        }
        return null
    }
}
