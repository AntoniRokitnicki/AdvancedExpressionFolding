package com.intellij.advancedExpressionFolding.processor.logger

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.firstArgument
import com.intellij.advancedExpressionFolding.processor.takeIfTrue
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
        logFolding.takeIfTrue() ?: return null

        val extensionConstructor: (PsiMethodCallExpression, Document) -> LoggerBracketsExtensionBase = when {
            methodName == "formatted" -> ::StringFormattedLoggerBracketsExtension
            element.firstArgument?.isLogMarker() == true -> ::MarkerLoggerBracketsExtension
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
