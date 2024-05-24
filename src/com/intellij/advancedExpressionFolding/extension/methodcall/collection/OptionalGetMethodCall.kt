package com.intellij.advancedExpressionFolding.extension.methodcall.collection

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.optional.OptionalNotNullAssertionGet
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiMethodCallExpression

class OptionalGetMethodCall : AbstractOptionalMethodCall() {
    override val methodNames = listOf("get", "orElseThrow")

    override fun onNoArguments(element: PsiMethodCallExpression, context: Context): Expression =
        OptionalNotNullAssertionGet(element, context.identifier.textRange, context.qualifierExpression)
}
