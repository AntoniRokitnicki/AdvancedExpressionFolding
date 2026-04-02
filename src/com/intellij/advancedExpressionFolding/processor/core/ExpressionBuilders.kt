package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.basic.TypeCast
import com.intellij.advancedExpressionFolding.processor.cache.CacheExt
import com.intellij.advancedExpressionFolding.processor.controlflow.IfExt
import com.intellij.advancedExpressionFolding.processor.expression.AssignmentExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.BinaryExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.LiteralExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.PrefixExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.PsiArrayAccessExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.PsiTypeCastExpressionExt
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallExpressionExt
import com.intellij.advancedExpressionFolding.processor.reference.NewExpressionExt
import com.intellij.advancedExpressionFolding.processor.reference.ReferenceExpressionExt
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiArrayAccessExpression
import com.intellij.psi.PsiAssignmentExpression
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiConditionalExpression
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiNewExpression
import com.intellij.psi.PsiParenthesizedExpression
import com.intellij.psi.PsiPolyadicExpression
import com.intellij.psi.PsiPrefixExpression
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.PsiTypeCastExpression

internal val expressionBuilderDefinitions:
    Map<Class<out BuildExpression<*>>, FunctionalExpressionBuilderDefinition<out PsiElement>> = mapOf(
    ArrayAccessExpressionBuilder::class.java to builderDefinition<PsiArrayAccessExpression>(
        checkConditions = { getExpressionsCollapse }
    ) { element, document, _ ->
        PsiArrayAccessExpressionExt.getArrayAccessExpression(element, document)
    },
    MethodCallExpressionBuilder::class.java to builderDefinition<PsiMethodCallExpression> { element, document, _ ->
        MethodCallExpressionExt.getMethodCallExpression(element, document)
    },
    ReferenceExpressionBuilder::class.java to builderDefinition<PsiReferenceExpression> { element, _, _ ->
        ReferenceExpressionExt.getReferenceExpression(element)
    },
    NewExpressionBuilder::class.java to builderDefinition<PsiNewExpression> { element, document, _ ->
        NewExpressionExt.getNewExpression(element, document)
    },
    LiteralExpressionBuilder::class.java to builderDefinition<PsiLiteralExpression> { element, _, _ ->
        LiteralExpressionExt.getLiteralExpression(element)
    },
    AssignmentExpressionBuilder::class.java to builderDefinition<PsiAssignmentExpression> { element, document, _ ->
        AssignmentExpressionExt.getAssignmentExpression(element, document)
    },
    PolyadicExpressionBuilder::class.java to builderDefinition<PsiPolyadicExpression> { element, document, _ ->
        IfExt.getPolyadicExpression(element, document)
    },
    BinaryExpressionBuilder::class.java to builderDefinition<PsiBinaryExpression> { element, document, _ ->
        BinaryExpressionExt.getBinaryExpression(element, document)
    },
    ConditionalExpressionBuilder::class.java to builderDefinition<PsiConditionalExpression> { element, document, _ ->
        IfExt.getConditionalExpression(element, document)
    },
    PrefixExpressionBuilder::class.java to builderDefinition<PsiPrefixExpression> { element, document, _ ->
        PrefixExpressionExt.getPrefixExpression(element, document)
    },
    ParenthesizedExpressionBuilder::class.java to builderDefinition<PsiParenthesizedExpression>(
        checkConditions = { castExpressionsCollapse }
    ) { element, document, synthetic ->
        val expression = element.expression
        if (expression is PsiTypeCastExpression) {
            val typeCast = PsiTypeCastExpressionExt.getTypeCastExpression(expression, document)
            if (typeCast != null) {
                return@builderDefinition TypeCast(
                    element,
                    element.textRange,
                    typeCast.getObject()
                )
            }
        }
        expression?.let { CacheExt.getExpression(it, document, synthetic) }
    },
    TypeCastExpressionBuilder::class.java to builderDefinition<PsiTypeCastExpression>(
        checkConditions = { castExpressionsCollapse }
    ) { element, document, _ ->
        PsiTypeCastExpressionExt.getTypeCastExpression(element, document)
    },
)

class ArrayAccessExpressionBuilder :
    FunctionalExpressionBuilder<PsiArrayAccessExpression>(
        ExpressionBuilderRegistry.definition(ArrayAccessExpressionBuilder::class.java)
    )

class MethodCallExpressionBuilder :
    FunctionalExpressionBuilder<PsiMethodCallExpression>(
        ExpressionBuilderRegistry.definition(MethodCallExpressionBuilder::class.java)
    )

class ReferenceExpressionBuilder :
    FunctionalExpressionBuilder<PsiReferenceExpression>(
        ExpressionBuilderRegistry.definition(ReferenceExpressionBuilder::class.java)
    )

class NewExpressionBuilder :
    FunctionalExpressionBuilder<PsiNewExpression>(
        ExpressionBuilderRegistry.definition(NewExpressionBuilder::class.java)
    )

class LiteralExpressionBuilder :
    FunctionalExpressionBuilder<PsiLiteralExpression>(
        ExpressionBuilderRegistry.definition(LiteralExpressionBuilder::class.java)
    )

class AssignmentExpressionBuilder :
    FunctionalExpressionBuilder<PsiAssignmentExpression>(
        ExpressionBuilderRegistry.definition(AssignmentExpressionBuilder::class.java)
    )

class PolyadicExpressionBuilder :
    FunctionalExpressionBuilder<PsiPolyadicExpression>(
        ExpressionBuilderRegistry.definition(PolyadicExpressionBuilder::class.java)
    )

class BinaryExpressionBuilder :
    FunctionalExpressionBuilder<PsiBinaryExpression>(
        ExpressionBuilderRegistry.definition(BinaryExpressionBuilder::class.java)
    )

class ConditionalExpressionBuilder :
    FunctionalExpressionBuilder<PsiConditionalExpression>(
        ExpressionBuilderRegistry.definition(ConditionalExpressionBuilder::class.java)
    )

class PrefixExpressionBuilder :
    FunctionalExpressionBuilder<PsiPrefixExpression>(
        ExpressionBuilderRegistry.definition(PrefixExpressionBuilder::class.java)
    )

class ParenthesizedExpressionBuilder :
    FunctionalExpressionBuilder<PsiParenthesizedExpression>(
        ExpressionBuilderRegistry.definition(ParenthesizedExpressionBuilder::class.java)
    )

class TypeCastExpressionBuilder :
    FunctionalExpressionBuilder<PsiTypeCastExpression>(
        ExpressionBuilderRegistry.definition(TypeCastExpressionBuilder::class.java)
    )

private inline fun <reified T : PsiElement> builderDefinition(
    noinline checkConditions: (FunctionalExpressionBuilder<T>.(T) -> Boolean)? = null,
    noinline build: FunctionalExpressionBuilder<T>.(T, Document, Boolean) -> Expression?,
): FunctionalExpressionBuilderDefinition<T> =
    FunctionalExpressionBuilderDefinition(T::class.java, checkConditions, build)
