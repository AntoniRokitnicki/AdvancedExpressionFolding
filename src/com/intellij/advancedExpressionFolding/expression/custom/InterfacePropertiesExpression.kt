package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.psi.PsiElement

class InterfacePropertiesExpression(element: PsiElement, child : Expression) : WrapperExpression(element, chain = listOf(child))