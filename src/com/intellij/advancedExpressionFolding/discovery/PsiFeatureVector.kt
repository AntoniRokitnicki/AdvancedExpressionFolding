package com.intellij.advancedExpressionFolding.discovery

import com.intellij.psi.PsiElement
import com.intellij.psi.SmartPointerManager
import com.intellij.psi.SmartPsiElementPointer

data class PsiFeatureVector(
    val pointer: SmartPsiElementPointer<PsiElement>,
    val vector: DoubleArray,
    val metadata: PsiVectorMetadata
) {
    val element: PsiElement?
        get() = pointer.element

    companion object {
        fun fromElement(element: PsiElement, vector: DoubleArray, metadata: PsiVectorMetadata): PsiFeatureVector {
            val manager = SmartPointerManager.getInstance(element.project)
            val pointer = manager.createSmartPsiElementPointer(element)
            return PsiFeatureVector(pointer, vector, metadata)
        }
    }
}
