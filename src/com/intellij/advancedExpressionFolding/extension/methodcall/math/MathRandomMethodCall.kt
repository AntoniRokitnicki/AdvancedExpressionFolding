package com.intellij.advancedExpressionFolding.extension.methodcall.math

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.advanced.Random
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.psi.PsiMethodCallExpression
import java.util.*

class MathRandomMethodCall : AbstractMathMethodCall() {
    override val methodNames by lazy { listOf("random") }

    override fun onNoArguments(
        element: PsiMethodCallExpression,
        context: Context
    ): Expression? = Random(
        element,
        element.textRange,
        Collections.emptyList()
    )
}
