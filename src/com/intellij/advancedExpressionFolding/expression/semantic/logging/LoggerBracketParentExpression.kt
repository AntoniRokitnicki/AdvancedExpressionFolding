package com.intellij.advancedExpressionFolding.expression.semantic.logging

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.WrapperExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class LoggerBracketParentExpression(
    element: PsiElement,
    textRange: TextRange = element.textRange,
    chain: List<Expression> = emptyList(),
    nested: Boolean = true
) : WrapperExpression(element, textRange, chain, nested)
