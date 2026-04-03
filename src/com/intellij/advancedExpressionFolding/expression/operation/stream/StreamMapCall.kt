package com.intellij.advancedExpressionFolding.expression.operation.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.CollapsedOperation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class StreamMapCall(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>,
    flatMap: Boolean
) : CollapsedOperation(element, if (flatMap) "**." else "*.", operands, textRange)
