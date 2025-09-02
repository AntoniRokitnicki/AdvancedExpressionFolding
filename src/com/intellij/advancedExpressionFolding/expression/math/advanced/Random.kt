package com.intellij.advancedExpressionFolding.expression.math.advanced

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement


class Random(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
    Function(element, textRange, "random", operands), ArithmeticExpression {
    override fun equals(o: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return (Math.random() * Integer.MAX_VALUE).toInt()
    }
}
