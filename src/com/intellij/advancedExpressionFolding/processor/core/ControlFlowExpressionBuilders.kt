package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.controlflow.CompactControlFlowExpression
import com.intellij.advancedExpressionFolding.processor.controlflow.ForStatementExpressionExt
import com.intellij.advancedExpressionFolding.processor.controlflow.IfExt
import com.intellij.advancedExpressionFolding.processor.controlflow.LoopExt
import com.intellij.advancedExpressionFolding.processor.controlflow.PsiTryStatementExt
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiCatchSection
import com.intellij.psi.PsiDoWhileStatement
import com.intellij.psi.PsiForeachStatement
import com.intellij.psi.PsiForStatement
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.PsiSwitchStatement
import com.intellij.psi.PsiTryStatement
import com.intellij.psi.PsiWhileStatement

class ForStatementBuilder : BuildExpression<PsiForStatement>(PsiForStatement::class.java) {
    override fun buildExpression(element: PsiForStatement, document: Document, synthetic: Boolean) =
        ForStatementExpressionExt.getForStatementExpression(element, document)
}

class ForEachStatementBuilder : BuildExpression<PsiForeachStatement>(PsiForeachStatement::class.java) {
    override fun buildExpression(element: PsiForeachStatement, document: Document, synthetic: Boolean): Expression? =
        LoopExt.getForEachStatementExpression(element)
}

class IfStatementBuilder : BuildExpression<PsiIfStatement>(PsiIfStatement::class.java) {
    override fun buildExpression(element: PsiIfStatement, document: Document, synthetic: Boolean) =
        IfExt.getIfExpression(element, document)
}

class WhileStatementBuilder : BuildExpression<PsiWhileStatement>(PsiWhileStatement::class.java) {
    override fun buildExpression(element: PsiWhileStatement, document: Document, synthetic: Boolean): Expression? =
        LoopExt.getWhileStatement(element)
}

class DoWhileStatementBuilder : BuildExpression<PsiDoWhileStatement>(PsiDoWhileStatement::class.java) {
    override fun buildExpression(element: PsiDoWhileStatement, document: Document, synthetic: Boolean): Expression? =
        LoopExt.getDoWhileStatement(element)
}

class SwitchStatementBuilder : BuildExpression<PsiSwitchStatement>(PsiSwitchStatement::class.java) {
    override fun buildExpression(element: PsiSwitchStatement, document: Document, synthetic: Boolean): Expression? =
        IfExt.getSwitchStatement(element)
}

class TryStatementBuilder : BuildExpression<PsiTryStatement>(PsiTryStatement::class.java) {
    override fun buildExpression(element: PsiTryStatement, document: Document, synthetic: Boolean) =
        PsiTryStatementExt.createExpression(element)
}

class CatchSectionBuilder : BuildExpression<PsiCatchSection>(PsiCatchSection::class.java) {
    override fun checkConditions(element: PsiCatchSection) =
        compactControlFlowSyntaxCollapse &&
                element.parameter != null &&
                element.lParenth != null &&
                element.rParenth != null

    override fun buildExpression(element: PsiCatchSection, document: Document, synthetic: Boolean): Expression? {
        val l = element.lParenth ?: return null
        val r = element.rParenth ?: return null
        return CompactControlFlowExpression(
            element,
            TextRange.create(l.textRange.startOffset, r.textRange.endOffset)
        )
    }
}
