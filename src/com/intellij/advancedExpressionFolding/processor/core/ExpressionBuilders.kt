package com.intellij.advancedExpressionFolding.processor.core

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
import com.intellij.psi.PsiArrayAccessExpression
import com.intellij.psi.PsiAssignmentExpression
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiConditionalExpression
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiNewExpression
import com.intellij.psi.PsiParenthesizedExpression
import com.intellij.psi.PsiPolyadicExpression
import com.intellij.psi.PsiPrefixExpression
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.PsiTypeCastExpression

internal val commonExpressionBuilders = listOf(
    registerBuilder<PsiArrayAccessExpression>(predicate = { getExpressionsCollapse }) { element, document, _ ->
        PsiArrayAccessExpressionExt.getArrayAccessExpression(element, document)
    },
    registerBuilder<PsiMethodCallExpression> { element, document, _ ->
        MethodCallExpressionExt.getMethodCallExpression(element, document)
    },
    registerBuilder<PsiReferenceExpression> { element, _, _ ->
        ReferenceExpressionExt.getReferenceExpression(element)
    },
    registerBuilder<PsiNewExpression> { element, document, _ ->
        NewExpressionExt.getNewExpression(element, document)
    },
    registerBuilder<PsiLiteralExpression> { element, _, _ ->
        LiteralExpressionExt.getLiteralExpression(element)
    },
    registerBuilder<PsiAssignmentExpression> { element, document, _ ->
        AssignmentExpressionExt.getAssignmentExpression(element, document)
    },
    registerBuilder<PsiPolyadicExpression> { element, document, _ ->
        IfExt.getPolyadicExpression(element, document)
    },
    registerBuilder<PsiBinaryExpression> { element, document, _ ->
        BinaryExpressionExt.getBinaryExpression(element, document)
    },
    registerBuilder<PsiConditionalExpression> { element, document, _ ->
        IfExt.getConditionalExpression(element, document)
    },
    registerBuilder<PsiPrefixExpression> { element, document, _ ->
        PrefixExpressionExt.getPrefixExpression(element, document)
    },
    registerBuilder<PsiParenthesizedExpression>(predicate = { castExpressionsCollapse }) { element, document, synthetic ->
        val expression = element.expression
        if (expression is PsiTypeCastExpression) {
            val typeCast = PsiTypeCastExpressionExt.getTypeCastExpression(expression, document)
            if (typeCast != null) {
                return@registerBuilder TypeCast(
                    element,
                    element.textRange,
                    typeCast.getObject()
                )
            }
        }
        expression?.let { CacheExt.getExpression(it, document, synthetic) }
    },
    registerBuilder<PsiTypeCastExpression>(predicate = { castExpressionsCollapse }) { element, document, _ ->
        PsiTypeCastExpressionExt.getTypeCastExpression(element, document)
    }
)
