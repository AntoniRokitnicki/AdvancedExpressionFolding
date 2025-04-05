package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class HideExpression(
    element: PsiElement,
    textRange: TextRange = element.textRange,
    group: FoldingGroup? = null,
    foldPrevWhiteSpace: Boolean = false,
) :
AbstractMultiExpression(element, textRange, null, group = group, foldPrevWhiteSpace = foldPrevWhiteSpace)
