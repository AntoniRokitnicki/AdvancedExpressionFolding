package com.intellij.advancedExpressionFolding.processor.methodcall.math

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.trig.Tanh
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import java.util.*

class MathTanhMethodCall : AbstractMathMethodCall() {
    override val methodNames by lazy { methodNames("tanh") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = Tanh(
        element,
        element.textRange,
        Collections.singletonList(argumentExpression)
    )
}
