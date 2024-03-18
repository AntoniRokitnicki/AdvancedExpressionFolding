package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.toTextRange
import com.intellij.psi.PsiElement

class DestructuringExpression(
    element: PsiElement, textRange: IntRange, text: String, child: Expression?, private val subName: String
) : AbstractSingleChildExpression(element, textRange.toTextRange(), text, child) {

    override fun groupName(): String  = "${DestructuringExpression::class.java.name}:$subName"

}
