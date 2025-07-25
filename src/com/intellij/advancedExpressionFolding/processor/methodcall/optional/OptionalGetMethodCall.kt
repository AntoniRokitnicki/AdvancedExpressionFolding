package com.intellij.advancedExpressionFolding.processor.methodcall.optional

import com.intellij.advancedExpressionFolding.expression.operation.optional.OptionalNotNullAssertionGet
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.advancedExpressionFolding.processor.methodcall.NeedsQualifier
import com.intellij.psi.PsiMethodCallExpression

class OptionalGetMethodCall : AbstractOptionalMethodCall(), NeedsQualifier {
    override val methodNames = listOf("get", "orElseThrow")

    override fun onNoArguments(element: PsiMethodCallExpression, context: Context): OptionalNotNullAssertionGet? {
        return OptionalNotNullAssertionGet(
            element,
            context.identifier.textRange,
            context.qualifierExpr
        )
    }
}
