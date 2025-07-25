package com.intellij.advancedExpressionFolding.extension.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Get
import com.intellij.advancedExpressionFolding.expression.Get.Style
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral
import com.intellij.advancedExpressionFolding.extension.Helper.getSlicePosition
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class CollectionGetMethodCall : AbstractMethodCall(), NeedsQualifier {

    override fun permission() = getExpressionsCollapse

    override val methodNames by lazy { listOf("get", "getProperty", "getAttribute", "getValue") }

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        val qualifier = context.qualifierExpr
        if (argumentExpression is NumberLiteral && argumentExpression.number == 0) {
            return Get(element, element.textRange, qualifier, argumentExpression, Style.FIRST)
        } else if (argument is PsiBinaryExpression) {
            val position = getSlicePosition(element, qualifier, argument, context.document)
            if (position != null && position.number == -1) {
                return Get(element, element.textRange, qualifier, argumentExpression, Style.LAST)
            }
        }
        return Get(element, element.textRange, qualifier, argumentExpression, Style.NORMAL)
    }

}
