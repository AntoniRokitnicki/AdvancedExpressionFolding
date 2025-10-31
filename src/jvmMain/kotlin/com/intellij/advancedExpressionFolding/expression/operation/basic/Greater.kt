package com.intellij.advancedExpressionFolding.expression.operation.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Greater(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>
) : Operation(element, textRange, ">", 18, operands)
