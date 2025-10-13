package com.intellij.advancedExpressionFolding.expression.math.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class DivideAssign(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Operation(element, textRange, "/=", 300, operands), ArithmeticExpression
