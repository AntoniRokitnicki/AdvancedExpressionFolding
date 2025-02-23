package com.intellij.advancedExpressionFolding.expression.custom


import com.intellij.psi.PsiElement

class FieldAnnotationExpression(
    element: PsiElement,
    customClassAnnotations: List<CustomClassAnnotation>,
    elementsToFold: List<PsiElement?>,
) : ClassAnnotationExpression(element, customClassAnnotations, elementsToFold, null)