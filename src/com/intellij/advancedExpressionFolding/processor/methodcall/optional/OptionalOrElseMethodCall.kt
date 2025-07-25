package com.intellij.advancedExpressionFolding.processor.methodcall.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalOrElseElvis
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiExpressionList
import com.intellij.psi.PsiMethodCallExpression

class OptionalOrElseMethodCall : AbstractOptionalMethodCall() {
    override val methodNames by lazy { listOf("orElseGet", "orElse") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        if (Helper.findChildByTypeHierarchy(element, PsiExpressionList::class.java, PsiExpressionList::class.java).isPresent) {
            return OptionalOrElseElvis(
                element,
                element.textRange,
                context.getOperands()
            )
        }
        return null
    }
}
