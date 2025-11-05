@file:Suppress("DEPRECATION")

package com.intellij.advancedExpressionFolding.processor.declaration

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.ControlFlowMultiStatementCodeBlockExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.ControlFlowSingleStatementCodeBlockExpression
import com.intellij.advancedExpressionFolding.expression.controlflow.IfExpression
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IControlFlowState
import com.intellij.psi.PsiBlockStatement
import com.intellij.psi.PsiCatchSection
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.PsiLoopStatement
import com.intellij.psi.PsiSwitchStatement
import com.intellij.psi.PsiTryStatement

object PsiCodeBlockExt : IControlFlowState by State()() {

    fun getCodeBlockExpression(element: PsiCodeBlock): Expression? {
        val parent = element.parent
        if (!isSupportedParent(parent, element)) {
            return null
        }
        return if (element.statements.size == 1 || parent is PsiSwitchStatement) {
            if (shouldCollapseSingleStatement(element, parent)) {
                ControlFlowSingleStatementCodeBlockExpression(element, element.textRange)
            } else {
                null
            }
        } else {
            if (controlFlowMultiStatementCodeBlockCollapse && !element.isWritable) {
                @Suppress("DEPRECATION")
                ControlFlowMultiStatementCodeBlockExpression(element, element.textRange)
            } else {
                null
            }
        }
    }

    private fun isSupportedParent(parent: PsiElement?, element: PsiCodeBlock): Boolean {
        if (parent is PsiBlockStatement) {
            val grandParent = parent.parent
            val hasBraces = element.rBrace != null && element.lBrace != null
            if ((grandParent is PsiIfStatement || grandParent is PsiLoopStatement) && hasBraces) {
                return true
            }
        }
        return parent is PsiSwitchStatement || parent is PsiTryStatement || parent is PsiCatchSection
    }

    private fun shouldCollapseSingleStatement(
        element: PsiCodeBlock,
        parent: PsiElement?
    ): Boolean {
        if (!controlFlowSingleStatementCodeBlockCollapse || element.isWritable) {
            return false
        }
        val grandParent = parent?.parent
        return grandParent !is PsiIfStatement ||
            !IfExpression.isAssertExpression(grandParent)
    }
}
