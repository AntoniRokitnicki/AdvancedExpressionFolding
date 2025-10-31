package com.intellij.advancedExpressionFolding.expression.semantic.logging

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.AbstractMultiExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class LoggerBracketExpression(
    element: PsiElement, textRange: TextRange, text: String, child: Expression?
) : AbstractMultiExpression(element, textRange, listOf(child), text = text)
