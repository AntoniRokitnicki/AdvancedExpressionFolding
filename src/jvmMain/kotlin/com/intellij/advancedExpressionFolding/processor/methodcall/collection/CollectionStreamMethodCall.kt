package com.intellij.advancedExpressionFolding.processor.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.stream.StreamExpression
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression

class CollectionStreamMethodCall : AbstractCollectionMethodCall() {
    override fun canExecute() = concatenationExpressionsCollapse

    override val methodNames by lazy { listOf("stream") }
    
    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? {
        if (context.className != "java.util.Optional" &&
            element.parent is PsiReferenceExpression &&
            (element.parent as PsiReferenceExpression).qualifierExpression == element) {
            
            return StreamExpression(element, TextRange.create(context.identifier.textRange.startOffset,
                element.textRange.endOffset))
        }
        return null
    }
}
