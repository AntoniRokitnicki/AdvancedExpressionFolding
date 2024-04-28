package com.intellij.advancedExpressionFolding.extension.methodcall.date

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.LocalDateLiteral
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression

class CreateDateFactoryMethodCall : AbstractDateMethodCall() {
    override fun permission(): Boolean = localDateLiteralCollapse || localDateLiteralPostfixCollapse

    override fun methodName() = "of"

    override val classNames: List<String> by lazy { listOf("java.time.LocalDate") }

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
        return LocalDateLiteral(element, element.textRange, year, month, day)
    }
}