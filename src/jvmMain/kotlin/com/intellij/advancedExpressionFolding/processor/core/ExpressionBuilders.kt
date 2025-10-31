package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.basic.TypeCast
import com.intellij.advancedExpressionFolding.processor.cache.CacheExt
import com.intellij.advancedExpressionFolding.processor.expression.AssignmentExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.BinaryExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.LiteralExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.PrefixExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.PsiArrayAccessExpressionExt
import com.intellij.advancedExpressionFolding.processor.reference.ReferenceExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.PsiTypeCastExpressionExt
import com.intellij.advancedExpressionFolding.processor.controlflow.IfExt
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallExpressionExt
import com.intellij.advancedExpressionFolding.processor.reference.NewExpressionExt
import com.intellij.openapi.editor.Document
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

class ArrayAccessExpressionBuilder : BuildExpression<PsiArrayAccessExpression>(PsiArrayAccessExpression::class.java) {
    override fun checkConditions(element: PsiArrayAccessExpression) = getExpressionsCollapse

    override fun buildExpression(element: PsiArrayAccessExpression, document: Document, synthetic: Boolean) =
        PsiArrayAccessExpressionExt.getArrayAccessExpression(element, document)
}

class MethodCallExpressionBuilder : BuildExpression<PsiMethodCallExpression>(PsiMethodCallExpression::class.java) {
    override fun buildExpression(element: PsiMethodCallExpression, document: Document, synthetic: Boolean) =
        MethodCallExpressionExt.getMethodCallExpression(element, document)
}

class ReferenceExpressionBuilder : BuildExpression<PsiReferenceExpression>(PsiReferenceExpression::class.java) {
    override fun buildExpression(element: PsiReferenceExpression, document: Document, synthetic: Boolean) =
        ReferenceExpressionExt.getReferenceExpression(element)
}

class NewExpressionBuilder : BuildExpression<PsiNewExpression>(PsiNewExpression::class.java) {
    override fun buildExpression(element: PsiNewExpression, document: Document, synthetic: Boolean) =
        NewExpressionExt.getNewExpression(element, document)
}

class LiteralExpressionBuilder : BuildExpression<PsiLiteralExpression>(PsiLiteralExpression::class.java) {
    override fun buildExpression(element: PsiLiteralExpression, document: Document, synthetic: Boolean) =
        LiteralExpressionExt.getLiteralExpression(element)
}

class AssignmentExpressionBuilder : BuildExpression<PsiAssignmentExpression>(PsiAssignmentExpression::class.java) {
    override fun buildExpression(element: PsiAssignmentExpression, document: Document, synthetic: Boolean) =
        AssignmentExpressionExt.getAssignmentExpression(element, document)
}

class PolyadicExpressionBuilder : BuildExpression<PsiPolyadicExpression>(PsiPolyadicExpression::class.java) {
    override fun buildExpression(element: PsiPolyadicExpression, document: Document, synthetic: Boolean) =
        IfExt.getPolyadicExpression(element, document)
}

class BinaryExpressionBuilder : BuildExpression<PsiBinaryExpression>(PsiBinaryExpression::class.java) {
    override fun buildExpression(element: PsiBinaryExpression, document: Document, synthetic: Boolean) =
        BinaryExpressionExt.getBinaryExpression(element, document)
}

class ConditionalExpressionBuilder : BuildExpression<PsiConditionalExpression>(PsiConditionalExpression::class.java) {
    override fun buildExpression(element: PsiConditionalExpression, document: Document, synthetic: Boolean) =
        IfExt.getConditionalExpression(element, document)
}

class PrefixExpressionBuilder : BuildExpression<PsiPrefixExpression>(PsiPrefixExpression::class.java) {
    override fun buildExpression(element: PsiPrefixExpression, document: Document, synthetic: Boolean) =
        PrefixExpressionExt.getPrefixExpression(element, document)
}

class ParenthesizedExpressionBuilder :
    BuildExpression<PsiParenthesizedExpression>(PsiParenthesizedExpression::class.java) {

    override fun checkConditions(element: PsiParenthesizedExpression) = castExpressionsCollapse

    override fun buildExpression(
        element: PsiParenthesizedExpression,
        document: Document,
        synthetic: Boolean,
    ): Expression? {
        val expression = element.expression
        if (expression is PsiTypeCastExpression) {
            val typeCast = PsiTypeCastExpressionExt.getTypeCastExpression(expression, document)
            if (typeCast != null) {
                return TypeCast(
                    element,
                    element.textRange,
                    typeCast.getObject()
                )
            }
        }
        if (expression != null) {
            return CacheExt.getExpression(expression, document, synthetic)
        }
        return null
    }
}

class TypeCastExpressionBuilder : BuildExpression<PsiTypeCastExpression>(PsiTypeCastExpression::class.java) {
    override fun checkConditions(element: PsiTypeCastExpression) = castExpressionsCollapse

    override fun buildExpression(element: PsiTypeCastExpression, document: Document, synthetic: Boolean) =
        PsiTypeCastExpressionExt.getTypeCastExpression(element, document)
}
