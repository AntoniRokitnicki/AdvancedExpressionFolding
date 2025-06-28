package com.intellij.advancedExpressionFolding.extension.methodcall.math

import com.intellij.advancedExpressionFolding.expression.Exp
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import java.util.*

class MathExpMethodCall : AbstractMathMethodCall() {
    override val methodNames by lazy { listOf("exp") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = Exp(element, element.textRange, Collections.singletonList(argumentExpression))
}
