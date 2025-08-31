package com.intellij.advancedExpressionFolding.expression.math.bitwise

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Or(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
    Operation(element, textRange, "|", 49, operands), ArithmeticExpression

