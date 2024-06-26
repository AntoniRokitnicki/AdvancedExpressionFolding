package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

open class SimpleExpression(
    element: PsiElement,
    open vararg val children: Expression?,
    textRange: TextRange = element.textRange,
    text: String = "",
    group: FoldingGroup? = null,
    foldPrevWhiteSpace: Boolean = false,
) :
    AbstractMultiExpression(
        element,
        textRange,
        *children,
        text = text,
        group = group,
        foldPrevWhiteSpace = foldPrevWhiteSpace
    )
