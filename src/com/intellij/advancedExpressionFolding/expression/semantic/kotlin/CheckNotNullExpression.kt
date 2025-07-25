package com.intellij.advancedExpressionFolding.expression.semantic.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.AbstractSingleChildExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class CheckNotNullExpression(
    element: PsiElement,
    textRange: TextRange,
    text: String,
    child: Expression?,
    val isMethodParameterWrappable: Boolean = false,
    val argumentExpression: Expression? = null
) : AbstractSingleChildExpression(element, textRange, text, child)
