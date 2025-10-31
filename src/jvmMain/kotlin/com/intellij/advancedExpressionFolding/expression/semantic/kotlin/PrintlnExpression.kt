package com.intellij.advancedExpressionFolding.expression.semantic.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.AbstractMultiExpression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class PrintlnExpression(
    element: PsiElement, textRange: TextRange, child: Expression?
) : AbstractMultiExpression(element, textRange, listOf(child))
