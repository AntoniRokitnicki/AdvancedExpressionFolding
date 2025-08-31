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
        return if (element.iteratedValue != null && element.rParenth != null &&
            settings.state.compactControlFlowSyntaxCollapse) {
            CompactControlFlowExpression(
                element,
                TextRange.create(
                    element.lParenth!!.textRange.startOffset,
                    element.rParenth!!.textRange.endOffset
                )
            )
        } else null
    }

    fun getWhileStatement(element: PsiWhileStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        return if (element.condition != null &&
            element.lParenth != null && element.rParenth != null &&
            settings.state.compactControlFlowSyntaxCollapse
        ) {
            CompactControlFlowExpression(
                element,
                TextRange.create(
                    element.lParenth!!.textRange.startOffset,
                    element.rParenth!!.textRange.endOffset
                )
            )
        } else null
    }

    fun getDoWhileStatement(element: PsiDoWhileStatement): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        return if (element.condition != null &&
            element.lParenth != null && element.rParenth != null &&
            settings.state.compactControlFlowSyntaxCollapse
        ) {
            CompactControlFlowExpression(
                element,
                TextRange.create(
                    element.lParenth!!.textRange.startOffset,
                    element.rParenth!!.textRange.endOffset
                )
            )
        } else null
    }
}

