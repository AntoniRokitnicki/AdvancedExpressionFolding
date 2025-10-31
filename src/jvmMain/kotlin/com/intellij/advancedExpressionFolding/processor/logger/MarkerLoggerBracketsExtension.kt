package com.intellij.advancedExpressionFolding.processor.logger

import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression

class MarkerLoggerBracketsExtension(
        element: PsiMethodCallExpression,
        document: Document
    ) : LoggerBracketsExtensionBase(element, document) {

        override fun Array<PsiExpression>.extractLiteral(): PsiLiteralExpression? = this[1].asStringLiteral()

        override fun MutableList<PsiExpression>.prepareArguments(): MutableList<PsiExpression> {
            removeAt(0)  // remove marker
            removeAt(0)  // remove logLiteral
            return this
        }
    }
