package com.intellij.advancedExpressionFolding.expression.semantic.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.WrapperExpression
import com.intellij.psi.PsiElement

class InterfacePropertiesExpression(element: PsiElement, child : Expression) : WrapperExpression(element, chain = listOf(child))
