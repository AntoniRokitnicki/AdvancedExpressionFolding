package com.intellij.advancedExpressionFolding.processor.reference

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.literal.*
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.expression.LiteralExpressionExt
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallExpressionExt
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import java.util.stream.Collectors

object NewExpressionExt {
    fun getNewExpression(element: PsiNewExpression, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val type = element.type
        val erasedType = type?.let { Helper.eraseGenerics(it.canonicalText) }
        if (type != null && Consts.SUPPORTED_CLASSES.contains(erasedType)) {
            val argumentList = element.argumentList
            if (argumentList != null && argumentList.expressions.size == 1) {
                val arg = argumentList.expressions[0]
                return when (arg) {
                    is PsiLiteralExpression -> getConstructorExpression(element, arg, erasedType!!)
                    is PsiReferenceExpression -> ReferenceExpressionExt.getReferenceExpression(arg, true)
                    is PsiMethodCallExpression -> if (erasedType == "java.util.ArrayList") {
                        val methodCallExpression = MethodCallExpressionExt.getMethodCallExpression(arg, document)
                        if (methodCallExpression is ListLiteral && settings.state.getExpressionsCollapse) {
                            ListLiteral(element, element.textRange, methodCallExpression.items)
                        } else {
                            null
                        }
                    } else null
                    else -> null
                }
            } else if (argumentList != null && argumentList.expressions.isEmpty()) {
                return when (erasedType) {
                    "java.lang.String", "java.lang.StringBuilder" -> StringLiteral(element, element.textRange, "")
                    "java.util.ArrayList" -> if (settings.state.getExpressionsCollapse) ListLiteral(element, element.textRange, emptyList()) else null
                    else -> null
                }
            }
        }
        val arrayInitializer = element.arrayInitializer
        if (type != null && arrayInitializer != null && settings.state.getExpressionsCollapse) {
            return ArrayLiteral(
                element,
                element.textRange,
                arrayInitializer.initializers.map { BuildExpressionExt.getAnyExpression(it, document) }
            )
        }
        val anonymousClass = element.anonymousClass
        if (type != null && anonymousClass != null && anonymousClass.lBrace != null && anonymousClass.rBrace != null) {
            if (erasedType == "java.util.HashSet") {
                if (anonymousClass.initializers.size == 1) {
                    val statements = anonymousClass.initializers[0].body.statements
                    if (statements.isNotEmpty()) {
                        var flag = true
                        val arguments = ArrayList<PsiElement>()
                        for (statement in statements) {
                            if (statement is PsiExpressionStatement && statement.expression is PsiMethodCallExpression) {
                                val methodCall = statement.expression as PsiMethodCallExpression
                                if (methodCall.methodExpression.text == "add" && methodCall.argumentList.expressions.size == 1) {
                                    val method = methodCall.methodExpression.resolve() as PsiMethod?
                                    if (method != null && method.containingClass != null && method.containingClass!!.qualifiedName != null && method.containingClass!!.qualifiedName == "java.util.HashSet") {
                                        arguments.add(methodCall.argumentList.expressions[0])
                                    } else {
                                        flag = false
                                        break
                                    }
                                } else {
                                    flag = false
                                    break
                                }
                            } else {
                                flag = false
                                break
                            }
                        }
                        if (flag) {
                            if (settings.state.getExpressionsCollapse) {
                                return SetLiteral(
                                    element,
                                    element.textRange,
                                    TextRange.create(anonymousClass.lBrace.textRange.startOffset, anonymousClass.rBrace.textRange.endOffset),
                                    anonymousClass.initializers[0].textRange,
                                    arguments.stream().map { BuildExpressionExt.getAnyExpression(it, document) }.collect(Collectors.toList())
                                )
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    fun getConstructorExpression(parent: PsiElement, argument: PsiLiteralExpression, classQualifiedNameNoGenerics: String): Expression? {
        val literalExpression = LiteralExpressionExt.getLiteralExpression(argument)
        if (literalExpression is NumberLiteral) {
            return NumberLiteral(parent, parent.textRange, literalExpression.textRange, literalExpression.number, false)
        } else {
            try {
                var value = argument.text
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length - 1)
                }
                return when (classQualifiedNameNoGenerics) {
                    "java.lang.Long" -> NumberLiteral(parent, parent.textRange, argument.textRange, value.toLong(), argument.value !is Number)
                    "java.lang.Integer" -> NumberLiteral(parent, parent.textRange, argument.textRange, value.toInt(), argument.value !is Number)
                    "java.lang.Float" -> NumberLiteral(parent, parent.textRange, argument.textRange, value.toFloat(), argument.value !is Number)
                    "java.lang.Double" -> NumberLiteral(parent, parent.textRange, argument.textRange, value.toDouble(), argument.value !is Number)
                    "java.math.BigDecimal" -> NumberLiteral(parent, parent.textRange, argument.textRange, BigDecimal(value), argument.value !is Number)
                    "java.math.BigInteger" -> NumberLiteral(parent, parent.textRange, argument.textRange, BigInteger(value), argument.value !is Number)
                    "java.lang.StringBuilder", "java.lang.String" -> StringLiteral(parent, parent.textRange, value)
                    else -> null
                }
            } catch (ignore: Exception) {
            }
        }
        return null
    }
}

