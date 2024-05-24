package com.intellij.advancedExpressionFolding.extension.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Get
import com.intellij.advancedExpressionFolding.expression.Get.Style
import com.intellij.advancedExpressionFolding.expression.NumberLiteral
import com.intellij.advancedExpressionFolding.expression.optional.OptionalNotNullAssertionGet
import com.intellij.advancedExpressionFolding.extension.Helper.getSlicePosition
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.on
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class CollectionGetMethodCall : AbstractMethodCall() {

    override fun permission() = getExpressionsCollapse

    override val methodNames by lazy { listOf("get", "getProperty", "getAttribute", "getValue") }

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression {
        val qualifierExpression = context.qualifierExpression
        if (argumentExpression is NumberLiteral && argumentExpression.number == 0) {
            return Get(element, element.textRange, qualifierExpression, argumentExpression, Style.FIRST)
        } else if (argument is PsiBinaryExpression) {
            val position = getSlicePosition(element, qualifierExpression, argument, context.document)
            if (position != null && position.number == -1) {
                return Get(element, element.textRange, qualifierExpression, argumentExpression, Style.LAST)
            }
        }
        return Get(element, element.textRange, qualifierExpression, argumentExpression, Style.NORMAL)
    }

}