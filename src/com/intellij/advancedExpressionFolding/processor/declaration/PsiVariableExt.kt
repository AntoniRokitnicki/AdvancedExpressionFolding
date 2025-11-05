package com.intellij.advancedExpressionFolding.processor.declaration

import com.intellij.advancedExpressionFolding.expression.VariableDeclarationImpl
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IExpressionCollapseState
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiVariable

object PsiVariableExt : IExpressionCollapseState by State()() {

    fun getVariableDeclaration(element: PsiVariable): VariableDeclarationImpl? {
        if (!shouldCollapseVariableDeclaration(element)) {
            return null
        }
        val isFinal = Helper.calculateIfFinal(element)
        val endOffset = element.typeElement?.textRange?.endOffset ?: return null
        return VariableDeclarationImpl(
            element,
            TextRange.create(element.textRange.startOffset, endOffset),
            element.modifierList != null && isFinal
        )
    }

    private fun shouldCollapseVariableDeclaration(
        element: PsiVariable
    ): Boolean {
        val typeElement = element.typeElement ?: return false
        return varExpressionsCollapse &&
            element.name != null &&
            (element.initializer != null || element.parent is PsiForeachStatement) &&
            element.textRange.startOffset < typeElement.textRange.endOffset
    }
}
