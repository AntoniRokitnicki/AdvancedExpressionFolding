package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.IfNullSafePrintlnExpression
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.processor.util.PropertyUtil
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.IKotlinLanguageState
import com.intellij.psi.JavaTokenType
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiBlockStatement
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiExpressionStatement
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiPolyadicExpression
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.PsiStatement

object IfNullSafePrintlnExt : IKotlinLanguageState by AdvancedExpressionFoldingSettings.State()() {

    fun createExpression(element: PsiIfStatement): Expression? {
        if (!ifNullSafe) {
            return null
        }
        if (element.elseBranch != null) {
            return null
        }

        val condition = element.condition ?: return null
        val nullChecks = collectNullChecks(condition) ?: return null
        if (nullChecks.size < 2) {
            return null
        }

        val singleStatement = element.thenBranch.extractSingleStatement() ?: return null
        val expressionStatement = singleStatement as? PsiExpressionStatement ?: return null
        val methodCall = expressionStatement.expression as? PsiMethodCallExpression ?: return null
        if (!isPrintlnCall(methodCall)) {
            return null
        }

        val argument = methodCall.argumentList.expressions.singleOrNull() ?: return null
        val accessChain = collectAccessChain(argument) ?: return null
        if (accessChain.size != nullChecks.size) {
            return null
        }
        if (!nullChecks.zip(accessChain).all { (expected, actual) ->
                expressionsEquivalent(expected, actual)
            }
        ) {
            return null
        }

        val safeCall = buildSafeCall(accessChain) ?: return null
        val placeholder = if (println) {
            "println($safeCall);"
        } else {
            "System.out.println($safeCall);"
        }
        return IfNullSafePrintlnExpression(element, element.textRange, placeholder)
    }

    private fun PsiStatement?.extractSingleStatement(): PsiStatement? {
        val statement = this ?: return null
        return when (statement) {
            is PsiBlockStatement -> {
                val statements = statement.codeBlock.statements
                if (statements.size == 1) statements[0] else null
            }
            else -> statement
        }
    }

    private fun collectNullChecks(condition: PsiExpression): List<PsiExpression>? {
        return when (condition) {
            is PsiPolyadicExpression -> {
                if (condition.operationTokenType != JavaTokenType.ANDAND) {
                    return null
                }
                condition.operands.mapNotNull { operand ->
                    (operand as? PsiBinaryExpression)?.extractNotNullOperand()
                }.takeIf { operands -> operands.size == condition.operands.size }
            }
            is PsiBinaryExpression -> listOfNotNull(condition.extractNotNullOperand())
            else -> null
        }
    }

    private fun PsiBinaryExpression.extractNotNullOperand(): PsiExpression? {
        if (operationTokenType != JavaTokenType.NE) {
            return null
        }
        if (rOperand.isNullLiteral()) {
            return lOperand
        }
        if (lOperand.isNullLiteral()) {
            return rOperand
        }
        return null
    }

    private fun PsiExpression?.isNullLiteral(): Boolean {
        val literal = this as? PsiLiteralExpression ?: return false
        return literal.value == null
    }

    private fun collectAccessChain(expression: PsiExpression): List<PsiExpression>? {
        val elements = mutableListOf<PsiExpression>()
        var current: PsiExpression? = expression
        while (current != null) {
            when (current) {
                is PsiMethodCallExpression -> {
                    elements.add(current)
                    current = current.methodExpression.qualifierExpression
                }
                is PsiReferenceExpression -> {
                    elements.add(current)
                    current = current.qualifierExpression
                }
                else -> return null
            }
        }
        return elements.asReversed()
    }

    private fun buildSafeCall(chain: List<PsiExpression>): String? {
        val base = chain.firstOrNull() as? PsiReferenceExpression ?: return null
        val builder = StringBuilder(base.text)
        chain.drop(1).forEach { expression ->
            builder.append("?.")
            val name = when (expression) {
                is PsiMethodCallExpression -> expression.methodExpression.referenceName?.let(PropertyUtil::guessPropertyName)
                is PsiReferenceExpression -> expression.referenceName
                else -> null
            }
            if (name.isNullOrBlank()) {
                return null
            }
            builder.append(name)
        }
        return builder.toString()
    }

    private fun isPrintlnCall(methodCall: PsiMethodCallExpression): Boolean {
        if (methodCall.methodExpression.referenceName != "println") {
            return false
        }
        val resolved = methodCall.resolveMethod() ?: return false
        val containingClass = resolved.containingClass?.qualifiedName ?: return false
        return containingClass == "java.io.PrintStream"
    }

    private fun expressionsEquivalent(first: PsiExpression, second: PsiExpression): Boolean {
        if (first == second) {
            return true
        }
        if (first is PsiReferenceExpression && second is PsiReferenceExpression) {
            return first.text == second.text && first.resolve() == second.resolve()
        }
        if (first is PsiMethodCallExpression && second is PsiMethodCallExpression) {
            return Helper.equal(first, second)
        }
        return first.text == second.text
    }
}
