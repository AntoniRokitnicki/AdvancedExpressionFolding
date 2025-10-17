package com.intellij.advancedExpressionFolding.processor.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDoWhileStatement
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiWhileStatement

object LoopExt {

    fun getForEachStatementExpression(element: PsiForeachStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val lParenth = element.lParenth ?: return null
        val rParenth = element.rParenth ?: return null
        return if (element.iteratedValue != null && settings.state.compactControlFlowSyntaxCollapse) {
            CompactControlFlowExpression(
                element,
                TextRange.create(lParenth.textRange.startOffset, rParenth.textRange.endOffset)
            )
        } else {
            null
        }
    }

    fun getWhileStatement(element: PsiWhileStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val lParenth = element.lParenth ?: return null
        val rParenth = element.rParenth ?: return null
        return if (element.condition != null && settings.state.compactControlFlowSyntaxCollapse) {
            CompactControlFlowExpression(
                element,
                TextRange.create(lParenth.textRange.startOffset, rParenth.textRange.endOffset)
            )
        } else {
            null
        }
    }

    fun getDoWhileStatement(element: PsiDoWhileStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val lParenth = element.lParenth ?: return null
        val rParenth = element.rParenth ?: return null
        return if (element.condition != null && settings.state.compactControlFlowSyntaxCollapse) {
            CompactControlFlowExpression(
                element,
                TextRange.create(lParenth.textRange.startOffset, rParenth.textRange.endOffset)
            )
        } else {
            null
        }
    }
}
