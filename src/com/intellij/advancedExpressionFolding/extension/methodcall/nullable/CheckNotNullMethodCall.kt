package com.intellij.advancedExpressionFolding.extension.methodcall.nullable

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.INameable
import com.intellij.advancedExpressionFolding.expression.StringLiteral
import com.intellij.advancedExpressionFolding.expression.Variable
import com.intellij.advancedExpressionFolding.expression.custom.CheckNotNullExpression
import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.advancedExpressionFolding.extension.end
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.start
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

class CheckNotNullMethodCall : AbstractMethodCall() {

    override fun permission() = nullable

    override val methodNames by lazy { listOf("checkNotNull") }

    override fun onTwoArguments(
        element: PsiMethodCallExpression,
        context: Context,
        a1: PsiExpression,
        a2: PsiExpression,
        a1Expression: Expression,
        a2Expression: Expression
    ): Expression? {
        if (a1Expression is INameable && a2Expression is StringLiteral) {
            return wrapToNotNull(element, a1Expression)
        }
        return null
    }

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        return argumentExpression.asInstance<INameable>()?.let {
            wrapToNotNull(element, argumentExpression)
        }
    }

    private fun wrapToNotNull(
        element: PsiMethodCallExpression,
        argumentExpression: Expression
    ): CheckNotNullExpression {
        val bodyStatement = element.parent.asInstance<PsiExpressionStatement>()
        val isMethodParameterWrappable = bodyStatement?.takeIf {
            bodyStatement.isDirectChildOfMethod()
        }?.takeIf {
            argumentExpression is Variable
        } != null
        val preFirstArgumentRange = TextRange(element.start(), element.argumentList.expressions.first().start())
        val postFirstArgumentRange = TextRange(element.argumentList.expressions.first().end(), element.end())
        val postFirstArgumentSuffix = CheckNotNullExpression(element, postFirstArgumentRange, "!!", argumentExpression)
        return CheckNotNullExpression(
            element,
            preFirstArgumentRange,
            "",
            postFirstArgumentSuffix,
            isMethodParameterWrappable,
            argumentExpression
        )
    }

}

private fun PsiExpressionStatement.isDirectChildOfMethod() =
    parent.asInstance<PsiCodeBlock>()?.parent.asInstance<PsiMethod>() != null
