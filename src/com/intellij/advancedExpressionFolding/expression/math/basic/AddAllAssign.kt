package com.intellij.advancedExpressionFolding.expression.math.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class AddAllAssign(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
    Operation(element, textRange, "++=", 300, operands)

