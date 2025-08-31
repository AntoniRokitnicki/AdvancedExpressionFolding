package com.intellij.advancedExpressionFolding.expression.math.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Function
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Negate(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
    Function(element, textRange, "negate", operands), ArithmeticExpression {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = false
}

