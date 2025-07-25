package com.intellij.advancedExpressionFolding.processor.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.ListLiteral
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.psi.PsiArrayType
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class ArraysListMethodCall : AbstractMethodCall(), NeedsQualifier {
    override fun permission() = getExpressionsCollapse

    override val methodNames by lazy { listOf("asList", "singletonList") }

    override val classNames by lazy { listOf("java.util.Arrays", "java.util.Collections") }

    override fun onAnyArguments(
        element: PsiMethodCallExpression,
        context: Context,
        expressions: Array<PsiExpression>
    ): Expression? {
        if (context.methodName != "asList" ||
            expressions.size != 1 ||
            expressions[0].type !is PsiArrayType
        ) {
            return ListLiteral(
                element,
                element.textRange,
                context.argumentExpressions.toList()
            )
        }
        return null

    }
}
