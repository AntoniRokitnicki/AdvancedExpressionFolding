package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.InterpolatedString
import com.intellij.advancedExpressionFolding.expression.semantic.kotlin.PreconditionExpression
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression
import com.intellij.advancedExpressionFolding.processor.end
import com.intellij.advancedExpressionFolding.processor.start
import com.intellij.advancedExpressionFolding.processor.toTextRange
import com.intellij.advancedExpressionFolding.settings.State
import com.intellij.advancedExpressionFolding.settings.state.IKotlinLanguageState
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.JavaTokenType
import com.intellij.psi.PsiBinaryExpression
import com.intellij.psi.PsiBlockStatement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiIfStatement
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiNewExpression
import com.intellij.psi.PsiParenthesizedExpression
import com.intellij.psi.PsiPrefixExpression
import com.intellij.psi.PsiThrowStatement
import com.intellij.psi.util.PsiTreeUtil

/**
 * Argument and state checks shown as Kotlin preconditions: `require`, `check`, `requireNotNull` and `error`.
 */
object PreconditionExt : IKotlinLanguageState by State()() {

    private const val ILLEGAL_ARGUMENT = "java.lang.IllegalArgumentException"
    private const val ILLEGAL_STATE = "java.lang.IllegalStateException"

    // `!(a == b)` is exactly `a != b` for every operand type; relational operators are not (NaN).
    private val NEGATED_EQUALITY = mapOf(JavaTokenType.EQEQ to "!=", JavaTokenType.NE to "==")

    private class PreconditionThrow(val exceptionType: String, val message: PsiExpression?)

    /**
     * The `throw` that is the whole body of a guard `if` without `else`, when it throws
     * `IllegalArgumentException` or `IllegalStateException` with no argument or a `String` message.
     */
    fun guardThrow(ifStatement: PsiIfStatement): PsiThrowStatement? {
        if (ifStatement.elseBranch != null || ifStatement.condition == null) {
            return null
        }
        val throwStatement = when (val thenBranch = ifStatement.thenBranch) {
            is PsiThrowStatement -> thenBranch
            is PsiBlockStatement -> thenBranch.codeBlock.statements.singleOrNull() as? PsiThrowStatement
            else -> null
        }
        return throwStatement?.takeIf { preconditionThrow(it) != null }
    }

    private fun preconditionThrow(throwStatement: PsiThrowStatement): PreconditionThrow? {
        val exception = throwStatement.exception as? PsiNewExpression ?: return null
        if (exception.anonymousClass != null) {
            return null
        }
        val exceptionType = exception.type?.canonicalText
            ?.takeIf { it == ILLEGAL_ARGUMENT || it == ILLEGAL_STATE }
            ?: return null
        val arguments = exception.argumentList?.expressions ?: return null
        return when {
            arguments.isEmpty() -> PreconditionThrow(exceptionType, null)
            arguments.size == 1 && arguments[0].isString() -> PreconditionThrow(exceptionType, arguments[0])
            else -> null
        }
    }

    fun getIfExpression(element: PsiIfStatement, document: Document): Expression? {
        if (!kotlinPreconditions) {
            return null
        }
        val throwStatement = guardThrow(element) ?: return null
        val thrown = preconditionThrow(throwStatement) ?: return null
        val condition = element.condition ?: return null
        val function = if (thrown.exceptionType == ILLEGAL_ARGUMENT) "require" else "check"

        val gaps = mutableListOf<Pair<TextRange, String>>()
        val shownCondition = mutableListOf<PsiElement>()
        val opening = (element.start() to condition.start()).toTextRange()
        var closing = ")"
        val negatedEquality = (condition as? PsiBinaryExpression)
            ?.takeIf { it.rOperand != null }
            ?.let { NEGATED_EQUALITY[it.operationTokenType] }
        val negatedOperand = (condition as? PsiPrefixExpression)
            ?.takeIf { it.operationTokenType == JavaTokenType.EXCL }
            ?.operand
        when {
            negatedOperand != null -> {
                gaps += opening to "$function("
                gaps += (condition as PsiPrefixExpression).operationSign.textRange to ""
                val parenthesized = (negatedOperand as? PsiParenthesizedExpression)
                    ?.takeIf { it.expression != null && it.lastChild.text == ")" }
                if (parenthesized != null) {
                    gaps += parenthesized.firstChild.textRange to ""
                    gaps += parenthesized.lastChild.textRange to ""
                    shownCondition += parenthesized.expression!!
                } else {
                    shownCondition += negatedOperand
                }
            }

            negatedEquality != null -> {
                val binary = condition as PsiBinaryExpression
                gaps += opening to "$function("
                gaps += binary.operationSign.textRange to negatedEquality
                shownCondition += binary.lOperand
                shownCondition += binary.rOperand!!
            }

            else -> {
                gaps += opening to "$function(!("
                closing = "))"
                shownCondition += condition
            }
        }

        val message = thrown.message
        val block = (element.thenBranch as? PsiBlockStatement)?.codeBlock
        val lBrace = block?.lBrace
        val rBrace = block?.rBrace
        when {
            message == null ->
                gaps += (condition.end() to element.end()).toTextRange() to closing

            // The braces stay real text: a gap across them would cut through the block's own `{...}` fold.
            lBrace != null && rBrace != null -> {
                gaps += (condition.end() to lBrace.start()).toTextRange() to "$closing "
                gaps += (lBrace.end() to message.start()).toTextRange() to " "
                gaps += (message.end() to rBrace.start()).toTextRange() to " "
            }

            else -> {
                gaps += (condition.end() to message.start()).toTextRange() to "$closing { "
                gaps += (message.end() to element.end()).toTextRange() to " }"
            }
        }
        val children = shownCondition.map { PreconditionExpression.Child(getAnyExpression(it, document)) } +
            listOfNotNull(message?.let { gaps.attachMessage(it, document) })
        return PreconditionExpression(element, gaps, children)
    }

    fun getThrowExpression(element: PsiThrowStatement, document: Document): Expression? {
        if (!kotlinPreconditions) {
            return null
        }
        val thrown = preconditionThrow(element)?.takeIf { it.exceptionType == ILLEGAL_STATE } ?: return null
        val message = thrown.message ?: return null
        val enclosingIf = PsiTreeUtil.getParentOfType(element, PsiIfStatement::class.java)
        if (enclosingIf != null && guardThrow(enclosingIf) == element) {
            return null
        }
        val exception = element.exception ?: return null
        val gaps = mutableListOf(
            (element.start() to message.start()).toTextRange() to "error(",
            (message.end() to exception.end()).toTextRange() to ")",
        )
        return PreconditionExpression(element, gaps, listOf(gaps.attachMessage(message, document)))
    }

    fun getRequireNonNullExpression(
        element: PsiMethodCallExpression,
        value: PsiExpression,
        message: PsiExpression?,
        document: Document,
    ): Expression? {
        if (!kotlinPreconditions || (message != null && !message.isString())) {
            return null
        }
        val gaps = mutableListOf((element.start() to value.start()).toTextRange() to "requireNotNull(")
        if (message != null) {
            gaps += (value.end() to message.start()).toTextRange() to ") { "
            gaps += (message.end() to element.end()).toTextRange() to " }"
        }
        val children = listOf(PreconditionExpression.Child(getAnyExpression(value, document))) +
            listOfNotNull(message?.let { gaps.attachMessage(it, document) })
        return PreconditionExpression(element, gaps, children)
    }

    /**
     * An interpolated message folds the character right next to it (`(` → `("$`, `)` → `")`). That character
     * is taken out of the neighbouring gap here, and the gap's text goes into the message's placeholder.
     */
    private fun MutableList<Pair<TextRange, String>>.attachMessage(
        message: PsiExpression,
        document: Document,
    ): PreconditionExpression.Child {
        val expression = getAnyExpression(message, document)
        val left = if (expression.isLeftOverflow() && overflows(document, message.start() - 1)) {
            takeCharacter(message.start() - 1, fromEnd = true)
        } else {
            null
        }
        val right = if (expression.isRightOverflow() && overflows(document, message.end())) {
            takeCharacter(message.end(), fromEnd = false)
        } else {
            null
        }
        return PreconditionExpression.Child(expression, left, right)
    }

    private fun MutableList<Pair<TextRange, String>>.takeCharacter(offset: Int, fromEnd: Boolean): String? {
        val index = indexOfFirst { (range, _) ->
            if (fromEnd) range.endOffset == offset + 1 else range.startOffset == offset
        }
        if (index < 0) {
            return null
        }
        val (range, text) = this[index]
        if (range.length == 1) {
            removeAt(index)
            return text
        }
        this[index] = if (fromEnd) {
            TextRange(range.startOffset, range.endOffset - 1) to text
        } else {
            TextRange(range.startOffset + 1, range.endOffset) to text
        }
        return ""
    }

    private fun overflows(document: Document, offset: Int): Boolean =
        offset >= 0 && offset < document.textLength &&
            InterpolatedString.OVERFLOW_CHARACTERS.contains(document.getText(TextRange(offset, offset + 1)))

    private fun PsiExpression.isString() = type?.canonicalText == "java.lang.String"
}
