package com.intellij.advancedExpressionFolding.extension


import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.LoggerBraces
import com.intellij.advancedExpressionFolding.expression.custom.WrapperExpression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset

object LoggerBracesExt : BaseExtension() {

    private val LOG_METHODS = listOf("debug", "trace", "info", "warn", "error")

    @JvmStatic
    fun createExpression(
        element: PsiMethodCallExpression,
        methodName: String,
        document: Document
    ): Expression? {
        val logLiteral = logFolding.takeIf {
            it
        }?.takeIf {
            LOG_METHODS.contains(methodName)
        }?.let {
            element.argumentList.expressions
        }?.takeIf {
            it.size > 1
        }?.takeIf {
            (it[0] as? PsiLiteralExpression)?.value is String
        }?.let {
            it[0].text
        }?.takeIf {
            it.contains("{}")
        } ?: return null

        val arguments = element.argumentList.expressions.toMutableList()
        val literal = arguments.removeFirst()

        val split = logLiteral.split("{}")

        var hasLast = false
        split.mapIndexedNotNull { index, nextString ->
            val argument = arguments.getOrNull(index)
            if (argument == null) {
                if (!hasLast) {
                    hasLast = true
                    val restAsString = if (index != split.size - 1) {
                        calculateMissingString(split, index, logLiteral)
                    } else {
                        nextString
                    }
                    val bracket = arguments.last().nextSibling
                    LoggerBraces(element, bracket.textRange,  restAsString + bracket.text.trim(), null)
                } else {
                    null
                }
            } else if (index == 0) {
                val countChars = literal.startOffset + nextString.length
                val toTextRange = (countChars..argument.prevSibling.endOffset).toTextRange()
                val expression = BuildExpressionExt.getAnyExpression(argument, document)
                LoggerBraces(element, toTextRange, "\$", expression)
            } else {
                val textRange = (argument.prevSibling.prevSibling.startOffset..argument.prevSibling.endOffset).toTextRange()
                val expression = BuildExpressionExt.getAnyExpression(argument, document)
                LoggerBraces(element, textRange, nextString + "\$", expression)
            }
        }.toList().takeIf {
            it.isNotEmpty()
        }?.let {
            return WrapperExpression(element, element.textRange, it)
        }
        return null
    }

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

}

