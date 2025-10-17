package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.psi.JavaRecursiveElementVisitor
import com.intellij.psi.PsiBreakStatement
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiContinueStatement
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiReturnStatement
import com.intellij.psi.PsiStatement
import com.intellij.psi.PsiThrowStatement
import com.intellij.psi.controlFlow.ControlFlow
import com.intellij.psi.controlFlow.ControlFlowFactory
import com.intellij.psi.controlFlow.ControlFlowUtil
import com.intellij.psi.controlFlow.LocalsOrMyInstanceFieldsControlFlowPolicy
import it.unimi.dsi.fastutil.ints.IntArrayList

internal object PsiMethodExitUtil {

    fun findExitStatements(method: PsiMethod): List<PsiStatement> {
        val body = method.body ?: return emptyList()
        val controlFlow = buildControlFlow(body)
        val exitInstructionIndices = IntArrayList()
        val exitStatements = ControlFlowUtil.findExitPointsAndStatements(
            controlFlow,
            0,
            controlFlow.size,
            exitInstructionIndices,
            *DEFAULT_EXIT_STATEMENTS
        )

        val structuralExitStatements = mutableListOf<PsiStatement>()
        body.accept(object : JavaRecursiveElementVisitor() {
            override fun visitReturnStatement(statement: PsiReturnStatement) {
                structuralExitStatements += statement
                super.visitReturnStatement(statement)
            }

            override fun visitBreakStatement(statement: PsiBreakStatement) {
                structuralExitStatements += statement
                super.visitBreakStatement(statement)
            }

            override fun visitContinueStatement(statement: PsiContinueStatement) {
                structuralExitStatements += statement
                super.visitContinueStatement(statement)
            }

            override fun visitThrowStatement(statement: PsiThrowStatement) {
                structuralExitStatements += statement
                super.visitThrowStatement(statement)
            }
        })

        return (exitStatements.asSequence() + structuralExitStatements.asSequence())
            .sortedBy { it.textRange?.startOffset ?: Int.MAX_VALUE }
            .distinct()
            .toList()
    }

    private fun buildControlFlow(body: PsiCodeBlock): ControlFlow {
        return ControlFlowFactory.getInstance(body.project).getControlFlow(
            body,
            LocalsOrMyInstanceFieldsControlFlowPolicy.getInstance(),
            false
        )
    }

    private val DEFAULT_EXIT_STATEMENTS = arrayOf(
        PsiReturnStatement::class.java,
        PsiBreakStatement::class.java,
        PsiContinueStatement::class.java,
        PsiThrowStatement::class.java
    )
}
