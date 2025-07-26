package com.intellij.advancedExpressionFolding.processor.methodcall.date

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.LocalDateLiteral
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression

class CreateDateFactoryMethodCall : AbstractMethodCall() {
    override fun canExecute() = localDateLiteralCollapse || localDateLiteralPostfixCollapse

    override val methodNames by lazy { listOf("of") }

    override val classNames by lazy { listOf("java.time.LocalDate") }

    override fun onManyArguments(
        element: PsiMethodCallExpression,
        context: Context,
        expressions: Array<PsiExpression>
    ): Expression? {
        val literals = element.argumentList.expressions.takeIf {
            it.size == 3
        }?.let { array ->
            array.mapNotNull {
                it as? PsiLiteralExpression
            }
        }?.takeIf {
            it.size == 3
        } ?: return null

        val (year, month, day) = literals
        return LocalDateLiteral(
            element,
            element.textRange,
            year,
            month,
            day
        )
    }
}
