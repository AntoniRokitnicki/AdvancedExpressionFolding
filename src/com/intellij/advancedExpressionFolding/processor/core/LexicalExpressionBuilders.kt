package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.SemicolonExpression
import com.intellij.advancedExpressionFolding.processor.token.PsiJavaTokenExt
import com.intellij.advancedExpressionFolding.processor.token.PsiKeywordExt
import com.intellij.openapi.editor.Document
import com.intellij.psi.JavaTokenType
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaToken
import com.intellij.psi.PsiKeyword

internal val lexicalExpressionBuilderDefinitions:
    Map<Class<out BuildExpression<*>>, FunctionalExpressionBuilderDefinition<out PsiElement>> = mapOf(
    SemicolonBuilder::class.java to builderDefinition<PsiJavaToken>(checkConditions = { element ->
        element.tokenType == JavaTokenType.SEMICOLON &&
            semicolonsCollapse &&
            !element.isWritable
    }) { element, _, _ ->
        SemicolonExpression(
            element,
            element.textRange
        )
    },
    TokenBuilder::class.java to builderDefinition<PsiJavaToken>(checkConditions = { element ->
        element.tokenType != JavaTokenType.SEMICOLON || element.isWritable
    }) { element, _, _ ->
        PsiJavaTokenExt.createExpression(element)
    },
    KeywordBuilder::class.java to builderDefinition<PsiKeyword> { element, _, _ ->
        PsiKeywordExt.createExpression(element)
    },
)

class SemicolonBuilder :
    FunctionalExpressionBuilder<PsiJavaToken>(
        ExpressionBuilderRegistry.definition(SemicolonBuilder::class.java)
    )

class TokenBuilder :
    FunctionalExpressionBuilder<PsiJavaToken>(
        ExpressionBuilderRegistry.definition(TokenBuilder::class.java)
    )

class KeywordBuilder :
    FunctionalExpressionBuilder<PsiKeyword>(
        ExpressionBuilderRegistry.definition(KeywordBuilder::class.java)
    )

private inline fun <reified T : PsiElement> builderDefinition(
    noinline checkConditions: (FunctionalExpressionBuilder<T>.(T) -> Boolean)? = null,
    noinline build: FunctionalExpressionBuilder<T>.(T, Document, Boolean) -> Expression?,
): FunctionalExpressionBuilderDefinition<T> =
    FunctionalExpressionBuilderDefinition(T::class.java, checkConditions, build)
