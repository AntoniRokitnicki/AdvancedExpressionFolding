package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.extension.CustomClassAnnotation
import com.intellij.psi.PsiElement

class FieldAnnotationExpression(
    element: PsiElement,
    private val customClassAnnotations: List<CustomClassAnnotation>,
    private val elementsToFold: List<PsiElement?>,
) : ClassAnnotationExpression(element, customClassAnnotations, elementsToFold) {

}