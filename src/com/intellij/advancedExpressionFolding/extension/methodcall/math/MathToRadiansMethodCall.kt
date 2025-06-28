package com.intellij.advancedExpressionFolding.extension.methodcall.math

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.ToRadians
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import java.util.*

class MathToRadiansMethodCall : AbstractMathMethodCall() {
    override val methodNames by lazy { listOf("toRadians") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = ToRadians(element, element.textRange, Collections.singletonList(argumentExpression))
}
