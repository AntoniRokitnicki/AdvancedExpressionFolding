package com.intellij.advancedExpressionFolding.processor.declaration

import com.intellij.advancedExpressionFolding.expression.VariableDeclarationImpl
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiVariable
import org.jetbrains.annotations.Nullable

object PsiVariableExt : BaseExtension() {
    @JvmStatic
    @Nullable
    fun getVariableDeclaration(element: PsiVariable): VariableDeclarationImpl? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (settings.getState().getVarExpressionsCollapse()
            && element.getName() != null
            && element.getTypeElement() != null
            && (element.getInitializer() != null || element.getParent() is PsiForeachStatement)
            && element.getTextRange().getStartOffset() < element.getTypeElement()!!.getTextRange().getEndOffset()
        ) {
            val isFinal = Helper.calculateIfFinal(element)
            return VariableDeclarationImpl(
                element,
                TextRange.create(
                    element.getTextRange().getStartOffset(),
                    element.getTypeElement()!!.getTextRange().getEndOffset()
                ),
                element.getModifierList() != null && isFinal
            )
        }
        return null
    }
}
