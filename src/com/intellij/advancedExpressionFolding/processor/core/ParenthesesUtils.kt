package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.psi.PsiAssignmentExpression
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiConditionalExpression
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiInstanceOfExpression
import com.intellij.psi.PsiParenthesizedExpression
import com.intellij.psi.PsiPolyadicExpression
import com.intellij.psi.PsiPostfixExpression
import com.intellij.psi.PsiPrefixExpression
import com.intellij.psi.PsiTypeCastExpression
import com.intellij.psi.PsiUnaryExpression
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.JavaTokenType
import com.intellij.psi.PsiElement

object ParenthesesUtils {
    private const val PRECEDENCE_PRIMARY = 0
    private const val PRECEDENCE_POSTFIX = 1
    private const val PRECEDENCE_UNARY = 2
    private const val PRECEDENCE_CAST = 3
    private const val PRECEDENCE_MULTIPLICATIVE = 4
    private const val PRECEDENCE_ADDITIVE = 5
    private const val PRECEDENCE_SHIFT = 6
    private const val PRECEDENCE_RELATIONAL = 7
    private const val PRECEDENCE_EQUALITY = 8
    private const val PRECEDENCE_BITWISE_AND = 9
    private const val PRECEDENCE_BITWISE_XOR = 10
    private const val PRECEDENCE_BITWISE_OR = 11
    private const val PRECEDENCE_LOGICAL_AND = 12
    private const val PRECEDENCE_LOGICAL_OR = 13
    private const val PRECEDENCE_TERNARY = 14
    private const val PRECEDENCE_ASSIGNMENT = 15

    private val comparisonTokens = setOf(
        JavaTokenType.EQEQ,
        JavaTokenType.NE,
        JavaTokenType.GT,
        JavaTokenType.GE,
        JavaTokenType.LT,
        JavaTokenType.LE,
    )

    private val arithmeticTokens = setOf(
        JavaTokenType.PLUS,
        JavaTokenType.MINUS,
        JavaTokenType.ASTERISK,
        JavaTokenType.DIV,
        JavaTokenType.PERC,
    )

    private val logicalTokens = setOf(JavaTokenType.ANDAND, JavaTokenType.OROR)

    private val bitwiseTokens = setOf(JavaTokenType.AND, JavaTokenType.OR, JavaTokenType.XOR)

    fun isParenthesesRedundant(expression: PsiExpression, context: PsiElement?): Boolean {
        if (PsiTreeUtil.findChildOfType(expression, PsiConditionalExpression::class.java, true) != null) {
            return false
        }
        val parenthesized = expression.parent as? PsiParenthesizedExpression ?: return true
        val parentExpression = context as? PsiExpression ?: return true
        if (parentExpression is PsiParenthesizedExpression) {
            return true
        }

        if (expression is PsiBinaryExpression && expression.operationTokenType in comparisonTokens) {
            val parentToken = parentOperationToken(parentExpression)
            if (parentToken != null && parentToken in arithmeticTokens + logicalTokens + bitwiseTokens + comparisonTokens) {
                return false
            }
        }

        val innerPrecedence = getExpressionPrecedence(expression)
        val parentPrecedence = getParentPrecedence(parentExpression)
        if (innerPrecedence > parentPrecedence) {
            return false
        }
        if (innerPrecedence == parentPrecedence && !isAssociativitySafe(parentExpression, parenthesized)) {
            return false
        }
        return true
    }

    private fun getExpressionPrecedence(expression: PsiExpression?): Int = when (expression) {
        null -> PRECEDENCE_PRIMARY
        is PsiParenthesizedExpression -> getExpressionPrecedence(expression.expression)
        is PsiConditionalExpression -> PRECEDENCE_TERNARY
        is PsiAssignmentExpression -> PRECEDENCE_ASSIGNMENT
        is PsiPolyadicExpression -> precedenceOfToken(expression.operationTokenType)
        is PsiBinaryExpression -> precedenceOfToken(expression.operationTokenType)
        is PsiInstanceOfExpression -> PRECEDENCE_RELATIONAL
        is PsiPrefixExpression -> PRECEDENCE_UNARY
        is PsiUnaryExpression -> PRECEDENCE_UNARY
        is PsiPostfixExpression -> PRECEDENCE_POSTFIX
        is PsiTypeCastExpression -> PRECEDENCE_CAST
        else -> PRECEDENCE_PRIMARY
    }

    private fun precedenceOfToken(tokenType: IElementType?): Int = when (tokenType) {
        JavaTokenType.ASTERISK, JavaTokenType.DIV, JavaTokenType.PERC -> PRECEDENCE_MULTIPLICATIVE
        JavaTokenType.PLUS, JavaTokenType.MINUS -> PRECEDENCE_ADDITIVE
        JavaTokenType.LTLT, JavaTokenType.GTGT, JavaTokenType.GTGTGT -> PRECEDENCE_SHIFT
        JavaTokenType.GT, JavaTokenType.GE, JavaTokenType.LT, JavaTokenType.LE, JavaTokenType.INSTANCEOF_KEYWORD -> PRECEDENCE_RELATIONAL
        JavaTokenType.EQEQ, JavaTokenType.NE -> PRECEDENCE_EQUALITY
        JavaTokenType.AND -> PRECEDENCE_BITWISE_AND
        JavaTokenType.XOR -> PRECEDENCE_BITWISE_XOR
        JavaTokenType.OR -> PRECEDENCE_BITWISE_OR
        JavaTokenType.ANDAND -> PRECEDENCE_LOGICAL_AND
        JavaTokenType.OROR -> PRECEDENCE_LOGICAL_OR
        else -> PRECEDENCE_PRIMARY
    }

    private fun getParentPrecedence(parent: PsiExpression): Int {
        return when (parent) {
            is PsiBinaryExpression, is PsiPolyadicExpression -> getExpressionPrecedence(parent)
            is PsiConditionalExpression, is PsiAssignmentExpression -> getExpressionPrecedence(parent)
            is PsiPrefixExpression, is PsiUnaryExpression, is PsiTypeCastExpression -> getExpressionPrecedence(parent)
            is PsiPostfixExpression -> getExpressionPrecedence(parent)
            else -> PRECEDENCE_PRIMARY
        }
    }

    private fun parentOperationToken(parent: PsiExpression): IElementType? = when (parent) {
        is PsiBinaryExpression -> parent.operationTokenType
        is PsiPolyadicExpression -> parent.operationTokenType
        is PsiPrefixExpression -> parent.operationTokenType
        is PsiPostfixExpression -> parent.operationTokenType
        else -> null
    }

    private fun isAssociativitySafe(parent: PsiExpression, parentheses: PsiParenthesizedExpression): Boolean {
        if (parent !is PsiBinaryExpression && parent !is PsiPolyadicExpression) {
            return true
        }
        val token = parentOperationToken(parent) ?: return true
        if (token in associativeTokens) {
            return true
        }
        val isRightOperand = when (parent) {
            is PsiBinaryExpression -> parent.rOperand == parentheses
            is PsiPolyadicExpression -> {
                val operands = parent.operands
                val index = operands.indexOf(parentheses)
                index > 0
            }
        }
        return !isRightOperand
    }

    private val associativeTokens = setOf(
        JavaTokenType.PLUS,
        JavaTokenType.ASTERISK,
        JavaTokenType.AND,
        JavaTokenType.OR,
        JavaTokenType.XOR,
        JavaTokenType.ANDAND,
        JavaTokenType.OROR,
    )
}
