package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter
import com.intellij.psi.PsiStatement
import com.intellij.psi.util.PsiTreeUtil
import java.util.LinkedHashSet

class LoggableAnnotationCompletionContributor : AbstractLoggingAnnotationCompletionContributor() {

    override val annotationName: String = "Loggable"

    override fun isAlreadyLogged(
        @Suppress("UNUSED_PARAMETER") method: PsiMethod,
        body: PsiCodeBlock,
        exitStatements: List<PsiStatement>,
    ): Boolean {
        val statements = body.statements
        if (statements.isEmpty()) return false
        val firstStatement = statements.first()
        if (!firstStatement.text.startsWith(printStatementPrefix(ENTERING))) return false

        val exitPrefix = printStatementPrefix(EXITING)

        if (exitStatements.isEmpty()) {
            return statements.any { it.text.startsWith(exitPrefix) }
        }

        return exitStatements.all { exitStatement ->
            val previous = PsiTreeUtil.skipWhitespacesAndCommentsBackward(exitStatement)
            previous is PsiStatement && previous.text.startsWith(exitPrefix)
        }
    }

    override fun addLogging(
        method: PsiMethod,
        body: PsiCodeBlock,
        exitStatements: List<PsiStatement>,
    ) {
        val entryStatement = createPrintStatement(method, buildEntryExpression(method))
        val exitExpression = buildExitExpression(method)

        insertEntryStatement(body, entryStatement)
        insertExitStatements(method, body, exitStatements, exitExpression)
    }

    override fun removeLogging(
        method: PsiMethod,
        body: PsiCodeBlock,
        exitStatements: List<PsiStatement>,
    ) {
        val entryPrefix = printStatementPrefix(ENTERING)
        val exitPrefix = printStatementPrefix(EXITING)
        val exitExpression = buildExitExpression(method)
        val exitStatementText = "System.out.println($exitExpression);"

        val statementsToDelete = LinkedHashSet<PsiStatement>()

        body.statements
            .filter { it.text.startsWith(entryPrefix) }
            .forEach { statementsToDelete += it }

        exitStatements.forEach { exitStatement ->
            var previous = PsiTreeUtil.skipWhitespacesAndCommentsBackward(exitStatement)
            while (previous is PsiStatement && previous.text.startsWith(exitPrefix)) {
                statementsToDelete += previous
                previous = PsiTreeUtil.skipWhitespacesAndCommentsBackward(previous)
            }
        }

        if (exitStatements.isEmpty()) {
            body.statements
                .filter { it.text.startsWith(exitPrefix) || it.text == exitStatementText }
                .forEach { statementsToDelete += it }
        }

        statementsToDelete.forEach { it.delete() }
    }

    private fun insertEntryStatement(body: PsiCodeBlock, entryStatement: PsiStatement) {
        val firstStatement = body.statements.firstOrNull()
        if (firstStatement == null) {
            body.add(entryStatement)
        } else {
            body.addBefore(entryStatement, firstStatement)
        }
    }

    private fun insertExitStatements(
        method: PsiMethod,
        body: PsiCodeBlock,
        exitStatements: List<PsiStatement>,
        exitExpression: String,
    ) {
        if (exitStatements.isEmpty()) {
            body.add(createPrintStatement(method, exitExpression))
            return
        }

        exitStatements.forEach { statement ->
            statement.parent?.addBefore(createPrintStatement(method, exitExpression), statement)
        }
    }

    private fun buildEntryExpression(method: PsiMethod): String {
        val methodLabel = methodLabel(method)
        val parameterNames = method.parameterList.parameters.mapNotNull(PsiParameter::getName)
        val base = "\"$ENTERING $methodLabel\""
        if (parameterNames.isEmpty()) {
            return base
        }

        val parameterExpressions = parameterNames.map { "\"$it=\" + $it" }
        val joinedParameters = parameterExpressions.joinToString(separator = " + \", \" + ")
        return "$base + \" with args: \" + $joinedParameters"
    }

    private fun buildExitExpression(method: PsiMethod): String {
        val methodLabel = methodLabel(method)
        return "\"$EXITING $methodLabel\""
    }

    private fun methodLabel(method: PsiMethod): String {
        val className = method.containingClass?.name ?: "Anonymous"
        return if (method.isConstructor) {
            "$className.<init>"
        } else {
            "$className.${method.name}"
        }
    }

    private fun printStatementPrefix(phase: String): String = "System.out.println(\"$phase "

    private fun createPrintStatement(method: PsiMethod, expression: String) =
        JavaPsiFacade.getElementFactory(method.project)
            .createStatementFromText("System.out.println($expression);", method)

    private companion object {
        private const val ENTERING = "Entering"
        private const val EXITING = "Exiting"
    }
}
