package com.intellij.advancedExpressionFolding.extension


import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Variable
import com.intellij.advancedExpressionFolding.expression.custom.LoggerBrackets
import com.intellij.advancedExpressionFolding.expression.custom.WrapperExpression
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.impl.source.PsiClassReferenceType
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset

object LoggerBracketsExt : BaseExtension() {

    private val LOG_METHODS = listOf("debug", "trace", "info", "warn", "error")

    @JvmStatic
    fun createExpression(
        element: PsiMethodCallExpression,
        methodName: String,
        document: Document
    ): Expression? {
        var hasMarker = false
        val logLiteral = logFolding.takeIf {
            it
        }?.takeIf {
            LOG_METHODS.contains(methodName)
        }?.let {
            element.argumentList.expressions
        }?.takeIf {
            it.size > 1
        }?.let { array ->
            var index = 0
            if (array[0].asInstance<PsiReferenceExpression>()?.type.asInstance<PsiClassReferenceType>()?.reference?.qualifiedName?.contains("Marker") == true) {
                index = 1
                hasMarker = true
            }
            array[index].asInstance<PsiLiteralExpression>()?.takeIf {
                it.value is String
            }?.text
        }?.takeIf {
            it.contains("{}")
        } ?: return null

        val arguments = element.argumentList.expressions.toMutableList()
        if (hasMarker) {
            arguments.removeFirst()
        }
        val literal = arguments.removeFirst()

        val split = logLiteral.split("{}")

        var nextStringAddon = ""
        val hasTooManyArguments = split.size <= arguments.size
        var hasLast = false
        split.mapIndexedNotNull { index, nextStringIn ->
            val nextString = if (nextStringAddon.isNotEmpty()) {
                val s = nextStringAddon + nextStringIn
                nextStringAddon = ""
                s
            } else {
                nextStringIn
            }

            val argument = arguments.getOrNull(index)
            if (argument == null || split.size - 1 == index) {
                if (!hasLast) {
                    hasLast = true
                    val restAsString = if (index != split.size - 1 && !hasTooManyArguments) {
                        calculateMissingString(split, index, logLiteral)
                    } else {
                        nextString
                    }
                    val bracket = arguments[index - 1].nextSibling
                    LoggerBrackets(element, bracket.textRange,  restAsString + bracket.text.trim(), null)
                } else {
                    null
                }
            } else {

                val expression = BuildExpressionExt.getAnyExpression(argument, document)
                val text =if (expression is Variable) {
                    "\$"
                } else {
                    nextStringAddon += "}"
                    "\${"
                }
                if (index == 0) {
                    val countChars = literal.startOffset + nextString.length
                    val textRange = (countChars..argument.prevSibling.endOffset).toTextRange()
                    LoggerBrackets(element, textRange, text, expression)
                } else {
                    val textRange = (argument.prevSibling.prevSibling.startOffset..argument.prevSibling.endOffset).toTextRange()
                    LoggerBrackets(element, textRange, nextString + text, expression)
                }
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

