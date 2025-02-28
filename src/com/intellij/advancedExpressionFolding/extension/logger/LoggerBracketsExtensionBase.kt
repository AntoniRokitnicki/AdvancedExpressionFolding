package com.intellij.advancedExpressionFolding.extension.logger

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Variable
import com.intellij.advancedExpressionFolding.expression.custom.LoggerBracketExpression
import com.intellij.advancedExpressionFolding.expression.custom.LoggerBracketParentExpression
import com.intellij.advancedExpressionFolding.extension.BuildExpressionExt.getAnyExpression
import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.advancedExpressionFolding.extension.end
import com.intellij.advancedExpressionFolding.extension.start
import com.intellij.advancedExpressionFolding.extension.toTextRange
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression


open class LoggerBracketsExtensionBase(
    protected val element: PsiMethodCallExpression,
    protected val document: Document
) {

    fun processExpression(): Expression? {
        val logLiteral = element.argumentList.expressions.takeIf {
            it.hasEnoughElements()
        }?.extractLiteral() ?: return null
        val logText = logLiteral.text ?: return null
        val split = logText.splitTextPattern() ?: return null

        val arguments = element.argumentList.expressions.toMutableList().prepareArguments()
        var nextStringAddon = ""
        val hasTooManyArguments = split.size <= arguments.size
        var hasLast = false
        return split.mapIndexedNotNull { index, nextStringIn ->
            val nextString = if (nextStringAddon.isNotEmpty()) {
                val joined = nextStringAddon + nextStringIn
                nextStringAddon = ""
                joined
            } else {
                nextStringIn
            }

            val argument = arguments.getOrNull(index)
            val lastIndex = index == split.size - 1
            if (argument == null || lastIndex) {
                if (!hasLast) {
                    hasLast = true
                    val restAsString = if (!lastIndex && !hasTooManyArguments) {
                        calculateMissingString(split, index, logText)
                    } else {
                        nextString
                    }
                    val bracket = arguments[index - 1].nextSibling
                    val lastString = bracket.createLastString(hasTooManyArguments)
                    LoggerBracketExpression(element, bracket.textRange, restAsString + lastString, null)
                } else {
                    null
                }
            } else {
                val expression = getAnyExpression(argument, document)
                val text = if (expression is Variable) {
                    "\$"
                } else {
                    nextStringAddon += "}"
                    "\${"
                }
                val prevSibling = argument.prevSibling
                if (index == 0) {
                    val countChars = logLiteral.start() + nextString.length
                    val textRange = (countChars..prevSibling.end()).toTextRange()
                    LoggerBracketExpression(element, textRange, text, expression)
                } else {
                    val textRange = (prevSibling.prevSibling.start()..prevSibling.end()).toTextRange()
                    LoggerBracketExpression(element, textRange, nextString + text, expression)
                }
            }
        }.takeIf {
            it.isNotEmpty()
        }?.createParentExpression(arguments, split, hasTooManyArguments)
    }

    private fun List<LoggerBracketExpression>.createParentExpression(
        arguments: MutableList<PsiExpression>,
        split: List<String>,
        hasTooManyArguments: Boolean
    ): LoggerBracketParentExpression {
        val finalExpressions = if (hasTooManyArguments) {
            this + arguments.subList(split.size - 1, arguments.size).map { expr ->
                getAnyExpression(expr, document)
            }
        } else {
            this
        }
        return LoggerBracketParentExpression(element, element.textRange, finalExpressions)
    }

    private fun String.splitTextPattern(): List<String>? {
        val splitBrackets = this.split("{}")
        return if (splitBrackets.size == 1) {
            this.split(PRINTF_PATTERN)
        } else {
            splitBrackets
        }.takeIf {
            it.size > 1
        }
    }

    open fun Array<PsiExpression>.hasEnoughElements() = size > 1

    open fun Array<PsiExpression>.extractLiteral(): PsiLiteralExpression? = firstOrNull().asStringLiteral()

    open fun MutableList<PsiExpression>.prepareArguments(): MutableList<PsiExpression> {
        removeFirst() // remove logLiteral
        return this
    }

    open fun PsiElement.createLastString(hasTooManyArguments: Boolean) = text.trim()


    private fun calculateMissingString(
        split: List<String>,
        index: Int,
        logLiteral: String
    ): String {
        val startIndex = split.subList(0, index).map {
            it.length + 2
        }.reduce(Int::plus)
        return logLiteral.substring(startIndex)
    }


    internal fun PsiExpression?.asStringLiteral() =
        asInstance<PsiLiteralExpression>()?.takeIf {
            it.value is String
        }

}

private val PRINTF_PATTERN = "%(?!n)\\w".toRegex()