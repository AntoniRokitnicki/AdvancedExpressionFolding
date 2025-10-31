package com.intellij.advancedExpressionFolding.processor.methodcall.math

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.advanced.Rint
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import java.util.*

class MathRintMethodCall : AbstractMathMethodCall() {
    override val methodNames by lazy { listOf("rint") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = Rint(
        element,
        element.textRange,
        Collections.singletonList(argumentExpression)
    )
}
