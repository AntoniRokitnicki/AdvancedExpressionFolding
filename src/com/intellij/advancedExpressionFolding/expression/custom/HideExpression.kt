package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class HideExpression(
    element: PsiElement,
    textRange: TextRange = element.textRange,
    vararg children: Expression?,
    group: FoldingGroup? = null,
    foldPrevWhiteSpace: Boolean = false,
) :
AbstractMultiExpression(element, textRange, *children, group = group, foldPrevWhiteSpace = foldPrevWhiteSpace)
