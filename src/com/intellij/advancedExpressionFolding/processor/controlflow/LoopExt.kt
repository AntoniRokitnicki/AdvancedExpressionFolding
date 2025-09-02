package com.intellij.advancedExpressionFolding.processor.controlflow

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDoWhileStatement
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiWhileStatement

object LoopExt {
    @JvmStatic
    fun getForEachStatementExpression(element: PsiForeachStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        return if (element.getIteratedValue() != null && element.getRParenth() != null &&
            settings.getState().getCompactControlFlowSyntaxCollapse()
        ) {
            CompactControlFlowExpression(
                element,
                TextRange.create(
                    element.getLParenth().getTextRange().getStartOffset(),
                    element.getRParenth().getTextRange().getEndOffset()
                )
            )
        } else {
            null
        }
    }

    @JvmStatic
    fun getWhileStatement(element: PsiWhileStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        return if (element.getCondition() != null
            && element.getLParenth() != null && element.getRParenth() != null
            && settings.getState().getCompactControlFlowSyntaxCollapse()
        ) {
            CompactControlFlowExpression(
                element,
                TextRange.create(
                    element.getLParenth().getTextRange().getStartOffset(),
                    element.getRParenth().getTextRange().getEndOffset()
                )
            )
        } else {
            null
        }
    }

    @JvmStatic
    fun getDoWhileStatement(element: PsiDoWhileStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        return if (element.getCondition() != null
            && element.getLParenth() != null && element.getRParenth() != null
            && settings.getState().getCompactControlFlowSyntaxCollapse()
        ) {
            CompactControlFlowExpression(
                element,
                TextRange.create(
                    element.getLParenth().getTextRange().getStartOffset(),
                    element.getRParenth().getTextRange().getEndOffset()
                )
            )
        } else {
            null
        }
    }
}
