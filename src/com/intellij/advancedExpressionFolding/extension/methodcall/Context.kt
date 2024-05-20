package com.intellij.advancedExpressionFolding.extension.methodcall

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod


data class Context(
    val methodName: String,
    val className: String,
    val qualifierExpression: Expression,
    val method: PsiMethod,
    val document: Document,
    val identifier: PsiElement,
    var argumentExpressions: List<Expression> = emptyList(),
) {
    fun getOperands(vararg extraArgumentExpressions: Expression): List<Expression> {
        return mutableListOf(qualifierExpression) + argumentExpressions + extraArgumentExpressions
    }
}