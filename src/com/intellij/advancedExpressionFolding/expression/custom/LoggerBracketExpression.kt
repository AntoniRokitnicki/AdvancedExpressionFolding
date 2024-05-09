package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class LoggerBracketExpression(
    element: PsiElement, textRange: TextRange, text: String, child: Expression?
) : AbstractMultiExpression(element, textRange, child, text = text)