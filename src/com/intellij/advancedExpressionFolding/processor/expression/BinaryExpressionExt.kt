package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.*
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append
import com.intellij.advancedExpressionFolding.expression.operation.basic.Equal
import com.intellij.advancedExpressionFolding.expression.operation.basic.Greater
import com.intellij.advancedExpressionFolding.expression.operation.basic.GreaterEqual
import com.intellij.advancedExpressionFolding.expression.operation.collection.Range
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import java.util.*
import java.util.stream.Stream

object BinaryExpressionExt {
    fun getBinaryExpression(element: PsiBinaryExpression, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if ((element.lOperand is PsiMethodCallExpression && isLiteralOrNegatedLiteral(element.rOperand) ||
                element.rOperand is PsiMethodCallExpression && isLiteralOrNegatedLiteral(element.lOperand)) &&
            settings.state.comparingExpressionsCollapse
        ) {
            val methodCallExpression = (if (element.lOperand is PsiMethodCallExpression) element.lOperand else element.rOperand) as PsiMethodCallExpression
            val literalExpression = if (isLiteralOrNegatedLiteral(element.lOperand)) element.lOperand else element.rOperand
            if (literalExpression != null &&
                (literalExpression.text == "0" || literalExpression.text == "-1" || literalExpression.text == "1")
            ) {
                val identifier = Stream.of(*methodCallExpression.methodExpression.children)
                    .filter { c: PsiElement? -> c is PsiIdentifier }
                    .findAny()
                if (identifier.isPresent && identifier.get().text == "compareTo" && methodCallExpression.argumentList.expressions.size == 1) {
                    val method = methodCallExpression.methodExpression.resolve() as PsiMethod?
                    if (method != null) {
                        val psiClass = method.containingClass
                        if (psiClass != null && (psiClass.qualifiedName != null && Consts.SUPPORTED_CLASSES.contains(Helper.eraseGenerics(psiClass.qualifiedName!!)) || Consts.UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS.contains(method.name))) {
                            val qualifier = if (methodCallExpression.methodExpression.qualifierExpression != null) BuildExpressionExt.getAnyExpression(methodCallExpression.methodExpression.qualifierExpression!!, document) else null
                            if (qualifier != null) {
                                val argument = BuildExpressionExt.getAnyExpression(methodCallExpression.argumentList.expressions[0], document)
                                val operationSign = element.operationSign.text
                                val expression = literalExpression.text.toInt()
                                var lessOperation = "<"
                                var greaterOperation = ">"
                                if (literalExpression === element.lOperand) {
                                    lessOperation = ">"
                                    greaterOperation = "<"
                                }
                                when {
                                    operationSign == "==" -> {
                                        return when (expression) {
                                            -1 -> Append.Less(element, element.textRange, listOf(qualifier, argument))
                                            0 -> Equal(element, element.textRange, listOf(qualifier, argument))
                                            1 -> Greater(element, element.textRange, listOf(qualifier, argument))
                                            else -> null
                                        }
                                    }
                                    operationSign == "!=" -> {
                                        return when (expression) {
                                            1 -> Append.LessEqual(element, element.textRange, listOf(qualifier, argument))
                                            0 -> NotEqual(element, element.textRange, listOf(qualifier, argument))
                                            -1 -> GreaterEqual(element, element.textRange, listOf(qualifier, argument))
                                            else -> null
                                        }
                                    }
                                    operationSign == lessOperation -> {
                                        return when (expression) {
                                            1 -> Append.LessEqual(element, element.textRange, listOf(qualifier, argument))
                                            0 -> Append.Less(element, element.textRange, listOf(qualifier, argument))
                                            else -> null
                                        }
                                    }
                                    operationSign == greaterOperation -> {
                                        return when (expression) {
                                            -1 -> GreaterEqual(element, element.textRange, listOf(qualifier, argument))
                                            0 -> Greater(element, element.textRange, listOf(qualifier, argument))
                                            else -> null
                                        }
                                    }
                                    operationSign == lessOperation + "=" -> {
                                        return when (expression) {
                                            -1 -> Append.Less(element, element.textRange, listOf(qualifier, argument))
                                            0 -> Append.LessEqual(element, element.textRange, listOf(qualifier, argument))
                                            else -> null
                                        }
                                    }
                                    operationSign == greaterOperation + "=" -> {
                                        return when (expression) {
                                            1 -> Greater(element, element.textRange, listOf(qualifier, argument))
                                            0 -> GreaterEqual(element, element.textRange, listOf(qualifier, argument))
                                            else -> null
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (Consts.SUPPORTED_BINARY_OPERATORS.contains(element.operationSign.text) && element.rOperand != null) {
            val leftExpression = BuildExpressionExt.getAnyExpression(element.lOperand, document)
            val rightExpression = BuildExpressionExt.getAnyExpression(element.rOperand!!, document)
            return when (element.operationSign.text) {
                "+" -> Add(element, element.textRange, listOf(leftExpression, rightExpression))
                "-" -> Subtract(element, element.textRange, listOf(leftExpression, rightExpression))
                "*" -> Multiply(element, element.textRange, listOf(leftExpression, rightExpression))
                "/" -> Divide(element, element.textRange, listOf(leftExpression, rightExpression))
                else -> null
            }
        }
        if ("&&" == element.operationSign.text && element.lOperand is PsiBinaryExpression && element.rOperand is PsiBinaryExpression) {
            return getAndTwoBinaryExpressions(element, element.lOperand as PsiBinaryExpression, element.rOperand as PsiBinaryExpression, document)
        }
        return null
    }

    fun isLiteralOrNegatedLiteral(element: PsiElement?): Boolean {
        return element is PsiLiteralExpression || element is PsiPrefixExpression && element.operand is PsiLiteralExpression && "-" == element.operationSign.text
    }

    fun getAndTwoBinaryExpressions(parent: PsiElement, a: PsiBinaryExpression, b: PsiBinaryExpression, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (settings.state.rangeExpressionsCollapse) {
            if ((a.operationSign.text == "<" || a.operationSign.text == "<=") &&
                (b.operationSign.text == ">" || b.operationSign.text == ">=") &&
                a.rOperand != null && b.rOperand != null
            ) {
                val e1 = BuildExpressionExt.getAnyExpression(a.lOperand, document)
                val e2 = BuildExpressionExt.getAnyExpression(a.rOperand!!, document)
                val e3 = BuildExpressionExt.getAnyExpression(b.lOperand, document)
                val e4 = BuildExpressionExt.getAnyExpression(b.rOperand!!, document)
                if (e1 == e3) {
                    return Range(parent, TextRange.create(a.textRange.startOffset, b.textRange.endOffset), e1, e4, b.operationSign.text == ">=", e2, a.operationSign.text == "<=")
                }
            }
            if ((a.operationSign.text == ">" || a.operationSign.text == ">=") &&
                (b.operationSign.text == "<" || b.operationSign.text == "<=") &&
                a.rOperand != null && b.rOperand != null
            ) {
                val e1 = BuildExpressionExt.getAnyExpression(a.lOperand, document)
                val e2 = BuildExpressionExt.getAnyExpression(a.rOperand!!, document)
                val e3 = BuildExpressionExt.getAnyExpression(b.lOperand, document)
                val e4 = BuildExpressionExt.getAnyExpression(b.rOperand!!, document)
                if (e1 == e3) {
                    return Range(parent, TextRange.create(a.textRange.startOffset, b.textRange.endOffset), e1, e2, a.operationSign.text == ">=", e4, b.operationSign.text == "<=")
                }
            }
        }
        return null
    }
}

