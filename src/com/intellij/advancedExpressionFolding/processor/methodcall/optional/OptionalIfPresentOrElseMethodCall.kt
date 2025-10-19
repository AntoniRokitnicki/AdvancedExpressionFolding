package com.intellij.advancedExpressionFolding.processor.methodcall.optional

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalIfPresentOrElseRun
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiLambdaExpression
import com.intellij.psi.PsiMethodCallExpression

class OptionalIfPresentOrElseMethodCall : AbstractOptionalMethodCall(), NeedsQualifier {
    override val methodNames = listOf("ifPresentOrElse")

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        @Suppress("UNUSED_PARAMETER")
        a1Expression: Expression,
        @Suppress("UNUSED_PARAMETER")
        a2Expression: Expression,
    ): Expression? {
        val presentLambda = a1 as? PsiLambdaExpression ?: return null
        val elseLambda = a2 as? PsiLambdaExpression ?: return null

        if (presentLambda.parameterList.parametersCount != 1) return null
        if (elseLambda.parameterList.parametersCount != 0) return null
        if (presentLambda.body !is PsiCodeBlock) return null
        if (elseLambda.body !is PsiCodeBlock) return null

        return OptionalIfPresentOrElseRun(
            element,
            element.textRange,
            context.qualifierExpr,
        )
    }
}

