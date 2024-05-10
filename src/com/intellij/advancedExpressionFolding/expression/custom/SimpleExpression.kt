package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class SimpleExpression(
    element: PsiElement,
    vararg val children: Expression?,
    textRange: TextRange = element.textRange,
    val text: String = "",
    var group: FoldingGroup? = null,
    val foldPrevWhiteSpace: Boolean = false,
) :
    AbstractMultiExpression(
        element,
        textRange,
        *children,
        text = text,
        group = group,
        foldPrevWhiteSpace = foldPrevWhiteSpace
    )
