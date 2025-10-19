package com.intellij.advancedExpressionFolding.processor.methodcall.nullable

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.advancedExpressionFolding.expression.property.INameable
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.CheckNotNullExpression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.argumentExpressions
import com.intellij.advancedExpressionFolding.processor.end
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.start
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

class CheckNotNullMethodCall : AbstractMethodCall() {

    override fun canExecute() = nullable

    override val methodNames by lazy { methodNames("checkNotNull") }

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
        val preFirstArgumentRange = TextRange(element.start(), element.argumentExpressions.first().start())
        val postFirstArgumentRange = TextRange(element.argumentExpressions.first().end(), element.end())
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
