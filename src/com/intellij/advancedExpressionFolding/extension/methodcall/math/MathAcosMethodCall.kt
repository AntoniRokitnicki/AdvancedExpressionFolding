package com.intellij.advancedExpressionFolding.extension.methodcall.math

import com.intellij.advancedExpressionFolding.expression.Acos
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import java.util.*

class MathAcosMethodCall : AbstractMathMethodCall() {
    override val methodNames by lazy { listOf("acos") }
    
    override fun onSingleArgument(
        element: PsiMethodCallExpression,
        context: Context,
        argument: PsiExpression,
        argumentExpression: Expression
    ): Expression? = Acos(element, element.textRange, Collections.singletonList(argumentExpression))
}
