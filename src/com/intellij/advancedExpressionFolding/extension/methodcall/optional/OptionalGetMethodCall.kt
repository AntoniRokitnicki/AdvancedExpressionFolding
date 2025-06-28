package com.intellij.advancedExpressionFolding.extension.methodcall.optional

import com.intellij.advancedExpressionFolding.expression.optional.OptionalNotNullAssertionGet
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.advancedExpressionFolding.extension.methodcall.NeedsQualifier
import com.intellij.psi.PsiMethodCallExpression

class OptionalGetMethodCall : AbstractOptionalMethodCall(), NeedsQualifier {
    override val methodNames = listOf("get", "orElseThrow")

    override fun onNoArguments(element: PsiMethodCallExpression, context: Context): OptionalNotNullAssertionGet? {
        return OptionalNotNullAssertionGet(element, context.identifier.textRange, context.qualifierExpr)
    }
}
