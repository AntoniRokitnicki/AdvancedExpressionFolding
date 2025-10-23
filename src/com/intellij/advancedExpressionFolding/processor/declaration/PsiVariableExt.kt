package com.intellij.advancedExpressionFolding.processor.declaration

import com.intellij.advancedExpressionFolding.expression.VariableDeclarationImpl
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.util.PsiVariableUtil
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiVariable

object PsiVariableExt : BaseExtension() {

    fun getVariableDeclaration(element: PsiVariable): VariableDeclarationImpl? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (!shouldCollapseVariableDeclaration(element, settings)) {
            return null
        }
        val isFinal = PsiVariableUtil.calculateIfFinal(element)
        val endOffset = element.typeElement?.textRange?.endOffset ?: return null
        return VariableDeclarationImpl(
            element,
            TextRange.create(element.textRange.startOffset, endOffset),
            element.modifierList != null && isFinal
        )
    }

    private fun shouldCollapseVariableDeclaration(
        element: PsiVariable,
        settings: AdvancedExpressionFoldingSettings
    ): Boolean {
        val typeElement = element.typeElement ?: return false
        return settings.state.varExpressionsCollapse &&
            element.name != null &&
            (element.initializer != null || element.parent is PsiForeachStatement) &&
            element.textRange.startOffset < typeElement.textRange.endOffset
    }
}
