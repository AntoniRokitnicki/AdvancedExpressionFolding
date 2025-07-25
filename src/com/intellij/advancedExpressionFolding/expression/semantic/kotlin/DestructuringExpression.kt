package com.intellij.advancedExpressionFolding.expression.semantic.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.AbstractMultiExpression
import com.intellij.advancedExpressionFolding.processor.toTextRange
import com.intellij.psi.PsiElement

class DestructuringExpression(
    element: PsiElement, textRange: IntRange, text: String, child: Expression?, private val subName: String
) : AbstractMultiExpression(element, textRange.toTextRange(), listOf(child), text = text)
