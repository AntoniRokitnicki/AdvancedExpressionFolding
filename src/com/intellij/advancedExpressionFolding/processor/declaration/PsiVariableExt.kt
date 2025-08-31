package com.intellij.advancedExpressionFolding.processor.declaration

import com.intellij.advancedExpressionFolding.expression.VariableDeclarationImpl
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiVariable

object PsiVariableExt : BaseExtension() {
    fun getVariableDeclaration(element: PsiVariable): VariableDeclarationImpl? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (settings.state.varExpressionsCollapse && element.name != null && element.typeElement != null &&
            (element.initializer != null || element.parent is PsiForeachStatement) &&
            element.textRange.startOffset < element.typeElement!!.textRange.endOffset
        ) {
            val isFinal = Helper.calculateIfFinal(element)
            return VariableDeclarationImpl(
                element,
                TextRange.create(element.textRange.startOffset, element.typeElement!!.textRange.endOffset),
                element.modifierList != null && isFinal
            )
        }
        return null
    }
}

