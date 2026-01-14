package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.controlflow.SemicolonExpression
import com.intellij.advancedExpressionFolding.processor.token.PsiJavaTokenExt
import com.intellij.advancedExpressionFolding.processor.token.PsiKeywordExt
import com.intellij.psi.JavaTokenType
import com.intellij.psi.PsiJavaToken
import com.intellij.psi.PsiKeyword

internal val lexicalExpressionBuilders = listOf(
    registerBuilder<PsiJavaToken>(predicate = {
        it.tokenType == JavaTokenType.SEMICOLON &&
            semicolonsCollapse &&
            !it.isWritable
    }) { element, _, _ ->
        SemicolonExpression(
            element,
            element.textRange
        )
    },
    registerBuilder<PsiJavaToken>(predicate = {
        it.tokenType != JavaTokenType.SEMICOLON || it.isWritable
    }) { element, _, _ ->
        PsiJavaTokenExt.createExpression(element)
    },
    registerBuilder<PsiKeyword> { element, _, _ ->
        PsiKeywordExt.createExpression(element)
    }
)
