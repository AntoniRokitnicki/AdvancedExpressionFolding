package com.intellij.advancedExpressionFolding.processor.methodcall.math

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.trig.Asin
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import java.util.*

class MathAsinMethodCall : AbstractMathMethodCall() {
    override val methodNames by lazy { methodNames("asin") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = Asin(
        element,
        element.textRange,
        Collections.singletonList(argumentExpression)
    )
}
