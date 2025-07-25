package com.intellij.advancedExpressionFolding.processor.logger

import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression

class StringFormattedLoggerBracketsExtension(
        element: PsiMethodCallExpression,
        document: Document
    ) : LoggerBracketsExtensionBase(element, document) {

        override fun PsiElement.createLastString(hasTooManyArguments: Boolean) = buildString {
            append(".formatted(")
            if (!hasTooManyArguments) {
                append(")")
            }
        }

        override fun MutableList<PsiExpression>.prepareArguments(): MutableList<PsiExpression> {
            // No need to remove anything
            return this
        }

        override fun Array<PsiExpression>.hasEnoughElements() = isNotEmpty()

        override fun Array<PsiExpression>.extractLiteral(): PsiLiteralExpression? =
            element.methodExpression.qualifierExpression.asStringLiteral()
    }
