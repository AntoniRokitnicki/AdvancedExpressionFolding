package com.intellij.advancedExpressionFolding.extension.methodcall.extracted

import com.intellij.advancedExpressionFolding.expression.ArrayStream
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression

class StreamMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = concatenationExpressionsCollapse

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
            
            return ArrayStream(element, TextRange.create(
                element.textRange.startOffset, element.textRange.endOffset), argumentExpression)
        }
        return null
    }
}