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

    override fun permission(): Boolean = getExpressionsCollapse || optional

    override val methodNames: List<String> by lazy { listOf("get", "getProperty", "getAttribute", "getValue", "orElseThrow") }


    //TODO: extract to a new class when its possible to have two AbstractMethodCall using same methodName = get
    override fun onNoArguments(element: PsiMethodCallExpression, context: Context): Expression? {
        if (optional && context.className == "java.util.Optional") {
            return OptionalNotNullAssertionGet(element, context.identifier.textRange, context.qualifierExpression)
        }

        return super.onNoArguments(element, context)
    }

    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? {
        getExpressionsCollapse.on() ?: return null
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