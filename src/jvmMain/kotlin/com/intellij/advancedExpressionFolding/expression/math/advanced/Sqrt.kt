@file:Suppress("DEPRECATION")

package com.intellij.advancedExpressionFolding.expression.math.advanced

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Sqrt(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Function(element, textRange, "sqrt", operands), ArithmeticExpression
