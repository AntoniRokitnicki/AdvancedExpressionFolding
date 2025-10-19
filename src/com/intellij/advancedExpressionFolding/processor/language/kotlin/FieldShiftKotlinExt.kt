package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.operation.FieldShiftMethod
import com.intellij.advancedExpressionFolding.expression.semantic.BuilderShiftExpression
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.util.PropertyUtil
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtBinaryExpression
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtParenthesizedExpression
import org.jetbrains.kotlin.psi.KtPsiUtil
import org.jetbrains.kotlin.psi.KtQualifiedExpression
import org.jetbrains.kotlin.psi.KtThisExpression
import org.jetbrains.kotlin.psi.psiUtil.getQualifiedExpressionForSelector

object FieldShiftKotlinExt : BaseExtension() {

    private const val FIELD_SHIFT = "<<"

    fun createExpression(element: KtBinaryExpression, document: Document?): Expression? {
        fieldShift.takeIf { it } ?: return null
        if (element.operationToken != KtTokens.EQ) {
            return null
        }

        val left = element.left ?: return null
        val right = element.right ?: return null

        val leftTarget = left.selectorTarget() ?: return null
        val rightTarget = right.selectorTarget() ?: return null

        if (leftTarget.name != rightTarget.name) {
            return null
        }

        return BuilderShiftExpression(rightTarget.element, rightTarget.element.textRange, FIELD_SHIFT)
    }

    fun createExpression(element: KtCallExpression, document: Document): Expression? {
        fieldShift.takeIf { it } ?: return null

        val qualified = element.getQualifiedExpressionForSelector() ?: return null
        val argument = element.valueArguments.singleOrNull()?.getArgumentExpression() ?: return null

        val calleeName = (element.calleeExpression as? KtNameReferenceExpression)?.getReferencedName()
            ?: return null
        val propertyName = PropertyUtil.guessPropertyName(calleeName)

        val argumentTarget = argument.selectorTarget() ?: return null
        if (argumentTarget.name != propertyName) {
            return null
        }

        val qualifierExpression = BuildExpressionExt.getAnyExpression(qualified.receiverExpression, document)
        return FieldShiftMethod(
            element,
            element.textRange,
            listOf(qualifierExpression),
            FIELD_SHIFT
        )
    }

    private fun KtExpression.selectorTarget(): SelectorTarget? {
        val expression = KtPsiUtil.safeDeparenthesize(this)
        return when (expression) {
            is KtQualifiedExpression -> expression.selectorExpression?.selectorTarget()
            is KtNameReferenceExpression -> SelectorTarget(expression, expression.getReferencedName())
            is KtCallExpression -> {
                val callee = expression.calleeExpression as? KtNameReferenceExpression ?: return null
                SelectorTarget(callee, PropertyUtil.guessPropertyName(callee.getReferencedName()))
            }
            is KtParenthesizedExpression -> expression.expression?.selectorTarget()
            is KtThisExpression -> null
            else -> null
        }
    }

    private data class SelectorTarget(val element: PsiElement, val name: String)
}
