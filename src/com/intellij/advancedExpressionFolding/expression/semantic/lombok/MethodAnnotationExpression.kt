package com.intellij.advancedExpressionFolding.expression.semantic.lombok

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.PsiElement

class MethodAnnotationExpression(
    element: PsiElement,
    customClassAnnotations: List<CustomClassAnnotation>,
    elementsToFold: List<PsiElement?>,
    additionalExpression: Expression? = null,
    group: FoldingGroup? = null,
) : ClassAnnotationExpression(element, customClassAnnotations, elementsToFold, additionalExpression, group)
