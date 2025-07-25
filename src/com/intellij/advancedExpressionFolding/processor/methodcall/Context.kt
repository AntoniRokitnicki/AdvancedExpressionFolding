package com.intellij.advancedExpressionFolding.processor.methodcall

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod

data class Context(
    val methodName: String,
    val className: String,
    val qualifierExprNullable: Expression?,
    val method: PsiMethod,
    val document: Document,
    val identifier: PsiElement,
    var argumentExpressions: List<Expression>,
) {
    fun getOperands(): List<Expression> =
        qualifierExprNullable?.let {
            mutableListOf(it) + argumentExpressions
        } ?: argumentExpressions

}

