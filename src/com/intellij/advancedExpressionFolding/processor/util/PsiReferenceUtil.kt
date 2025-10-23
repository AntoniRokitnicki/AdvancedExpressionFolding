package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.PsiStatement

object PsiReferenceUtil {

    fun isReferenceToReference(referenceExpression: PsiReferenceExpression?, reference: PsiReference?): Boolean {
        val element = reference?.resolve()
        return referenceExpression != null && element != null && referenceExpression.isReferenceTo(element)
    }

    fun findSameQualifier(element: PsiElement, qualifier: PsiElement): PsiElement? {
        if (element is PsiStatement && element.firstChild != null) {
            return findSameQualifier(element.firstChild, qualifier)
        }
        if (equal(qualifier, element)) {
            return element
        }
        if (element is PsiMethodCallExpression) {
            val qualifierExpression = element.methodExpression.qualifierExpression
            if (qualifierExpression != null) {
                return findSameQualifier(qualifierExpression, qualifier)
            }
        }
        if (element is PsiReferenceExpression) {
            val qualifierExpression = element.qualifierExpression
            if (qualifierExpression != null) {
                return findSameQualifier(qualifierExpression, qualifier)
            }
        }
        return null
    }

    private fun equal(e1: PsiElement?, e2: PsiElement?): Boolean {
        return when {
            e2 is PsiReferenceExpression && e1 is PsiReferenceExpression ->
                e2.referenceName == e1.referenceName && isReferenceToReference(e2, e1)

            e2 is PsiMethodCallExpression && e1 is PsiMethodCallExpression ->
                equal(e2.methodExpression, e1.methodExpression) &&
                    equal(e2.methodExpression.qualifierExpression, e1.methodExpression.qualifierExpression)

            else -> false
        }
    }
}
