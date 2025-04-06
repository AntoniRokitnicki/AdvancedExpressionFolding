package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

open class SimpleExpression(
    element: PsiElement,
    textRange: TextRange = element.textRange,
    text: String,
    group: FoldingGroup? = null,
) :
    FastExpression(
        element,
        textRange,
        text = text,
        group = group,
    )
