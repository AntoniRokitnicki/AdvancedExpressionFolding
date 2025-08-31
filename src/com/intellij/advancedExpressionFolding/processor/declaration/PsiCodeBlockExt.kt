package com.intellij.advancedExpressionFolding.processor.declaration

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.ControlFlowMultiStatementCodeBlockExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ControlFlowSingleStatementCodeBlockExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.IfExpression
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.psi.*

object PsiCodeBlockExt : BaseExtension() {
    fun getCodeBlockExpression(element: PsiCodeBlock): Expression? {
        val parent = element.parent
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (parent is PsiBlockStatement &&
            ((parent.parent is PsiIfStatement || parent.parent is PsiLoopStatement) &&
                element.rBrace != null && element.lBrace != null) ||
            parent is PsiSwitchStatement || parent is PsiTryStatement || parent is PsiCatchSection
        ) {
            if (element.statements.size == 1 || parent is PsiSwitchStatement) {
                if (settings.state.controlFlowSingleStatementCodeBlockCollapse && !element.isWritable &&
                    (!(parent.parent is PsiIfStatement) || !IfExpression.isAssertExpression(settings.state, parent.parent as PsiIfStatement))
                ) {
                    return ControlFlowSingleStatementCodeBlockExpression(element, element.textRange)
                }
            } else {
                if (settings.state.controlFlowMultiStatementCodeBlockCollapse && !element.isWritable) {
                    return ControlFlowMultiStatementCodeBlockExpression(element, element.textRange)
                }
            }
        }
        return null
    }
}

