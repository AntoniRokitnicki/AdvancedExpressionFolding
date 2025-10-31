package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.controlflow.SemicolonExpression
import com.intellij.advancedExpressionFolding.processor.token.PsiJavaTokenExt
import com.intellij.advancedExpressionFolding.processor.token.PsiKeywordExt
import com.intellij.openapi.editor.Document
import com.intellij.psi.JavaTokenType
import com.intellij.psi.PsiJavaToken
import com.intellij.psi.PsiKeyword

class SemicolonBuilder : BuildExpression<PsiJavaToken>(PsiJavaToken::class.java) {
    override fun checkConditions(element: PsiJavaToken) =
        element.tokenType == JavaTokenType.SEMICOLON &&
                semicolonsCollapse &&
                !element.isWritable

    override fun buildExpression(element: PsiJavaToken, document: Document, synthetic: Boolean) =
        SemicolonExpression(
            element,
            element.textRange
        )
}

class TokenBuilder : BuildExpression<PsiJavaToken>(PsiJavaToken::class.java) {
    override fun checkConditions(element: PsiJavaToken) =
        element.tokenType != JavaTokenType.SEMICOLON || element.isWritable

    override fun buildExpression(element: PsiJavaToken, document: Document, synthetic: Boolean) =
        PsiJavaTokenExt.createExpression(element)
}

class KeywordBuilder : BuildExpression<PsiKeyword>(PsiKeyword::class.java) {
    override fun buildExpression(element: PsiKeyword, document: Document, synthetic: Boolean) =
        PsiKeywordExt.createExpression(element)
}
