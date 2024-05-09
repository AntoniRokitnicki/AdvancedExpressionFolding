package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.toTextRange
import com.intellij.psi.PsiElement

class DestructuringExpression(
    element: PsiElement, textRange: IntRange, text: String, child: Expression?, private val subName: String
) : AbstractMultiExpression(element, textRange.toTextRange(), child, text = text)
