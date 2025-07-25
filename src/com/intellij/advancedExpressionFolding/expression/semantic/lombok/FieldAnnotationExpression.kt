package com.intellij.advancedExpressionFolding.expression.semantic.lombok


import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.PsiElement

class FieldAnnotationExpression(
    element: PsiElement,
    customClassAnnotations: List<CustomClassAnnotation>,
    elementsToFold: List<PsiElement?>,
    group: FoldingGroup? = null,
) : ClassAnnotationExpression(element, customClassAnnotations, elementsToFold, null, group = group)
