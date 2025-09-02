package com.intellij.advancedExpressionFolding.expression.math.trig

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement


class Atan2(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Function(element, textRange, "atan2", operands), ArithmeticExpression

