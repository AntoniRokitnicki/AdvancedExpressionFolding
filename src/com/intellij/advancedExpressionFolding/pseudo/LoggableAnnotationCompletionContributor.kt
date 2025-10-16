package com.intellij.advancedExpressionFolding.pseudo

import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiCodeBlock
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter
import com.intellij.psi.PsiStatement
import com.intellij.psi.PsiReturnStatement
import com.intellij.psi.PsiThrowStatement

class LoggableAnnotationCompletionContributor : AbstractLoggingAnnotationCompletionContributor() {

    override val annotationName: String = "Loggable"

    override fun isAlreadyLogged(body: PsiCodeBlock): Boolean {
        val statements = body.statements
        if (statements.isEmpty()) return false
        val firstStatement = statements.first()
        if (!firstStatement.text.startsWith("System.out.println(\"Entering ")) return false
        return statements.any { it.text.startsWith("System.out.println(\"Exiting ") }
    }

    override fun addLogging(method: PsiMethod, body: PsiCodeBlock) {
        val project = method.project
        val factory = JavaPsiFacade.getElementFactory(project)
        val entryExpression = buildEntryExpression(method)
        val exitExpression = buildExitExpression(method)

        val entryStatement = factory.createStatementFromText("System.out.println($entryExpression);", method)
        val exitStatement = factory.createStatementFromText("System.out.println($exitExpression);", method)

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
        val base = "\"Entering $methodLabel\""
        if (parameterNames.isEmpty()) {
            return base
        }

        val parameterExpressions = parameterNames.map { "\"$it=\" + $it" }
        val joinedParameters = parameterExpressions.joinToString(separator = " + \", \" + ")
        return base + " + \" with args: \" + " + joinedParameters
    }

    private fun buildExitExpression(method: PsiMethod): String {
        val methodLabel = methodLabel(method)
        return "\"Exiting $methodLabel\""
    }

    private fun methodLabel(method: PsiMethod): String {
        val className = method.containingClass?.name ?: "Anonymous"
        return if (method.isConstructor) {
            "$className.<init>"
        } else {
            "$className.${method.name}"
        }
    }
}
