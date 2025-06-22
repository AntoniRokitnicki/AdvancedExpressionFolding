package com.intellij.advancedExpressionFolding.extension.methodcall.extracted

import com.intellij.advancedExpressionFolding.expression.Ceil
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import java.util.*

class MathCeilMethodCall : AbstractMathMethodCall() {
    override val methodNames by lazy { listOf("ceil") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = Ceil(element, element.textRange, Collections.singletonList(argumentExpression))
}
