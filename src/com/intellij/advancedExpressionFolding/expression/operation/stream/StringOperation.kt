package com.intellij.advancedExpressionFolding.expression.operation.stream

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.CollapsedOperation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

open class StringOperation(
    element: PsiElement,
    text: String,
    operands: List<Expression> = emptyList(),
    textRange: TextRange = element.textRange,
) : CollapsedOperation(element, text, operands, textRange)
