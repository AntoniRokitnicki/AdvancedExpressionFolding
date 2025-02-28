package com.intellij.advancedExpressionFolding.extension.logger

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.BaseExtension
import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.advancedExpressionFolding.extension.on
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.impl.source.PsiClassReferenceType

object LoggerBracketsExt : BaseExtension() {

    @JvmStatic
    fun createExpression(
        element: PsiMethodCallExpression,
        methodName: String,
        document: Document
    ): Expression? {
        logFolding.on() ?: return null

        val extensionConstructor: (PsiMethodCallExpression, Document) -> LoggerBracketsExtensionBase = when {
            methodName == "formatted" -> ::StringFormattedLoggerBracketsExtension
            element.argumentList.expressions.firstOrNull()?.isLogMarker() == true -> ::MarkerLoggerBracketsExtension
            else -> ::LoggerBracketsExtensionBase
        }
        return extensionConstructor(element, document).processExpression()
    }

    private fun PsiExpression?.isLogMarker() =
        this.asInstance<PsiReferenceExpression>()?.type
            .asInstance<PsiClassReferenceType>()?.reference
            ?.qualifiedName
            ?.contains("Marker") == true

}