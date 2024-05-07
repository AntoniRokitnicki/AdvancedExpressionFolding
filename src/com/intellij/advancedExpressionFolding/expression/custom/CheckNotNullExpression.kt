package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class CheckNotNullExpression(
    element: PsiElement, textRange: TextRange, text: String, child: Expression?, val isMethodParameterWrappable: Boolean = false
) : AbstractSingleChildExpression(element, textRange, text, child) {

    override fun groupName(): String  = CheckNotNullExpression::class.java.name



}
