package com.intellij.advancedExpressionFolding.processor.declaration

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.ControlFlowMultiStatementCodeBlockExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ControlFlowSingleStatementCodeBlockExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.IfExpression
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.psi.*
import org.jetbrains.annotations.Nullable

object PsiCodeBlockExt : BaseExtension() {
    @JvmStatic
    @Nullable
    fun getCodeBlockExpression(element: PsiCodeBlock): Expression? {
        val parent = element.getParent()
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (parent is PsiBlockStatement && ((parent.getParent() is PsiIfStatement || parent.getParent() is PsiLoopStatement)
                    && element.getRBrace() != null
                    && element.getLBrace() != null
                    || parent is PsiSwitchStatement
                    || parent is PsiTryStatement
                    || parent is PsiCatchSection)
        ) {
            if (element.getStatements().size == 1 || parent is PsiSwitchStatement) {
                if (settings.getState().getControlFlowSingleStatementCodeBlockCollapse()
                    && !element.isWritable()
                    && (!(parent.getParent() is PsiIfStatement) || !IfExpression.isAssertExpression(settings.getState(), parent.getParent() as PsiIfStatement))
                ) {
                    return ControlFlowSingleStatementCodeBlockExpression(element, element.getTextRange())
                }
            } else {
                if (settings.getState().getControlFlowMultiStatementCodeBlockCollapse()
                    && !element.isWritable()
                ) {
                    return ControlFlowMultiStatementCodeBlockExpression(element, element.getTextRange())
                }
            }
        }
        return null
    }
}
