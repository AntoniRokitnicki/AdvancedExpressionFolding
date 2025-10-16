package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter
import com.intellij.psi.PsiReturnStatement
import com.intellij.psi.PsiStatement
import com.intellij.psi.PsiThrowStatement

class LoggableAnnotationCompletionContributor : AbstractLoggingAnnotationCompletionContributor() {

    override val annotationName: String = "Loggable"

    override fun isAlreadyLogged(body: PsiCodeBlock): Boolean {
        val statements = body.statements
        if (statements.isEmpty()) return false
        val firstStatement = statements.first()
        if (!firstStatement.text.startsWith(printStatementPrefix(ENTERING))) return false
        return statements.any { it.text.startsWith(printStatementPrefix(EXITING)) }
    }

    override fun addLogging(method: PsiMethod, body: PsiCodeBlock) {
        val entryStatement = createPrintStatement(method, buildEntryExpression(method))
        val exitStatement = createPrintStatement(method, buildExitExpression(method))

        insertLoggingStatements(body, entryStatement, exitStatement)
    }

    private fun insertLoggingStatements(body: PsiCodeBlock, entryStatement: PsiStatement, exitStatement: PsiStatement) {
        val originalStatements = body.statements
        val firstStatement = originalStatements.firstOrNull()
        if (firstStatement == null) {
            body.add(entryStatement)
            body.add(exitStatement)
            return
        }

        body.addBefore(entryStatement, firstStatement)

        val lastOriginalStatement = originalStatements.lastOrNull()
        when (lastOriginalStatement) {
            is PsiReturnStatement, is PsiThrowStatement -> body.addBefore(exitStatement, lastOriginalStatement)
            else -> body.addAfter(exitStatement, lastOriginalStatement)
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
