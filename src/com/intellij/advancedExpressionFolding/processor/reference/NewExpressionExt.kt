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
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.math.BigDecimal
import java.math.BigInteger
import java.util.ArrayList
import java.util.Arrays
import java.util.Collections
import java.util.stream.Collectors

object NewExpressionExt {
    @JvmStatic
    @Nullable
    fun getNewExpression(element: PsiNewExpression, @NotNull document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val type = element.getType()
        val erasedType = if (type != null) Helper.eraseGenerics(type.getCanonicalText()) else null
        if (type != null && Consts.SUPPORTED_CLASSES.contains(erasedType)) {
            val argumentList = element.getArgumentList()
            if (argumentList != null && argumentList.getExpressions().size == 1) {
                val arg = argumentList.getExpressions()[0]
                if (arg is PsiLiteralExpression) {
                    return getConstructorExpression(element, arg, erasedType!!)
                } else if (arg is PsiReferenceExpression) {
                    return ReferenceExpressionExt.getReferenceExpression(arg, true)
                } else if ("java.util.ArrayList" == erasedType && arg is PsiMethodCallExpression) {
                    val methodCallExpression = MethodCallExpressionExt.getMethodCallExpression(arg, document)
                    if (methodCallExpression is ListLiteral && settings.getState().getGetExpressionsCollapse()) {
                        return ListLiteral(element, element.getTextRange(), methodCallExpression.getItems())
                    }
                }
            } else if (argumentList != null && argumentList.getExpressions().size == 0) {
                when (erasedType) {
                    "java.lang.String", "java.lang.StringBuilder" -> return StringLiteral(element, element.getTextRange(), "")
                    "java.util.ArrayList" -> if (settings.getState().getGetExpressionsCollapse()) {
                        return ListLiteral(element, element.getTextRange(), Collections.emptyList())
                    } else {
                        return null
                    }
                }
            }
        }
        val arrayInitializer = element.getArrayInitializer()
        if (type != null && arrayInitializer != null && settings.getState().getGetExpressionsCollapse()) {
            return ArrayLiteral(
                element,
                element.getTextRange(),
                Arrays.stream(arrayInitializer.getInitializers()).map { i -> BuildExpressionExt.getAnyExpression(i, document) }.collect(Collectors.toList())
            )
        }
        val anonymousClass = element.getAnonymousClass()
        if (type != null && anonymousClass != null && anonymousClass.getLBrace() != null && anonymousClass.getRBrace() != null) {
            if ("java.util.HashSet" == erasedType) {
                if (anonymousClass.getInitializers().size == 1) {
                    val statements = anonymousClass.getInitializers()[0].getBody().getStatements()
                    if (statements.size > 0) {
                        var flag = true
                        val arguments = ArrayList<PsiElement>()
                        for (statement in statements) {
                            if (statement is PsiExpressionStatement && statement.getExpression() is PsiMethodCallExpression) {
                                val methodCall = statement.getExpression() as PsiMethodCallExpression
                                if (methodCall.getMethodExpression().getText() == "add" && methodCall.getArgumentList().getExpressions().size == 1) {
                                    val method = methodCall.getMethodExpression().resolve() as PsiMethod?
                                    if (method != null && method.getContainingClass() != null && method.getContainingClass()!!.getQualifiedName() != null && method.getContainingClass()!!.getQualifiedName() == "java.util.HashSet") {
                                        arguments.add(methodCall.getArgumentList().getExpressions()[0])
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
                            if (settings.getState().getGetExpressionsCollapse())
                                return SetLiteral(
                                    element,
                                    element.getTextRange(),
                                    TextRange.create(anonymousClass.getLBrace().getTextRange().getStartOffset(), anonymousClass.getRBrace().getTextRange().getEndOffset()),
                                    anonymousClass.getInitializers()[0].getTextRange(),
                                    arguments.stream().map { a -> BuildExpressionExt.getAnyExpression(a, document) }.collect(Collectors.toList())
                                )
                        }
                    }
                }
            }
        }
        return null
    }

    @JvmStatic
    @Nullable
    fun getConstructorExpression(@NotNull parent: PsiElement, @NotNull argument: PsiLiteralExpression, @NotNull classQualifiedNameNoGenerics: String): Expression? {
        val literalExpression = LiteralExpressionExt.getLiteralExpression(argument)
        if (literalExpression is NumberLiteral) {
            return NumberLiteral(parent, parent.getTextRange(), literalExpression.getTextRange(), literalExpression.getNumber(), false)
        } else {
            try {
                var value = argument.getText()
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length - 1)
                }
                when (classQualifiedNameNoGenerics) {
                    "java.lang.Long" -> return NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), java.lang.Long.valueOf(value), argument.getValue() !is Number)
                    "java.lang.Integer" -> return NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), Integer.valueOf(value), argument.getValue() !is Number)
                    "java.lang.Float" -> return NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), java.lang.Float.valueOf(value), argument.getValue() !is Number)
                    "java.lang.Double" -> return NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), java.lang.Double.valueOf(value), argument.getValue() !is Number)
                    "java.math.BigDecimal" -> return NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), BigDecimal(value), argument.getValue() !is Number)
                    "java.math.BigInteger" -> return NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), BigInteger(value), argument.getValue() !is Number)
                    "java.lang.StringBuilder", "java.lang.String" -> return StringLiteral(parent, parent.getTextRange(), value)
                }
            } catch (ignore: Exception) {
            }
        }
        return null
    }
}
