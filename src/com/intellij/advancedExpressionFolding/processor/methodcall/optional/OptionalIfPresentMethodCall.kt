package com.intellij.advancedExpressionFolding.processor.methodcall.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalIfPresentLet
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiLambdaExpression
import com.intellij.psi.PsiMethodCallExpression

class OptionalIfPresentMethodCall : AbstractOptionalMethodCall(), NeedsQualifier {
    override val methodNames = listOf("ifPresent")

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        @Suppress("UNUSED_PARAMETER")
        argumentExpression: Expression,
    ): Expression? {
        val lambda = argument as? PsiLambdaExpression ?: return null
        if (lambda.parameterList.parametersCount != 1) return null
        if (lambda.body !is PsiCodeBlock) return null

        return OptionalIfPresentLet(
            element,
            element.textRange,
            context.qualifierExpr,
        )
    }
}

