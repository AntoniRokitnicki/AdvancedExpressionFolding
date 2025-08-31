package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Negate
import com.intellij.advancedExpressionFolding.expression.math.basic.NotEqual
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append
import com.intellij.advancedExpressionFolding.expression.operation.basic.Equal
import com.intellij.advancedExpressionFolding.expression.operation.basic.GreaterEqual
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.psi.*
import java.util.*
import java.util.function.BiPredicate
import java.util.function.Predicate
import java.util.stream.Stream

object PrefixExpressionExt {
    fun getPrefixExpression(element: PsiPrefixExpression, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (element.operand != null) {
            if (element.operationSign.text == "!") {
                if (settings.state.comparingLocalDatesCollapse) {
                    if (element.operand is PsiMethodCallExpression) {
                        val methodCallInformationOptional = MethodCallInformation.tryGet(
                            element.operand as PsiMethodCallExpression,
                            document,
                            "isBefore",
                            "isAfter",
                            "before",
                            "after"
                        )
                        if (methodCallInformationOptional.isPresent) {
                            val callInformation = methodCallInformationOptional.get()
                            val methodName = callInformation.methodName
                            if (methodName == "isBefore" || methodName == "before") {
                                return GreaterEqual(
                                    element,
                                    element.textRange,
                                    listOf(callInformation.qualifierExpression, callInformation.getFoldedArgument(0))
                                )
                            }
                            if (methodName == "isAfter" || methodName == "after") {
                                return Append.LessEqual(
                                    element,
                                    element.textRange,
                                    listOf(callInformation.qualifierExpression, callInformation.getFoldedArgument(0))
                                )
                            }
                        }
                    }
                }
                if (settings.state.comparingExpressionsCollapse) {
                    val operand = BuildExpressionExt.getAnyExpression(element.operand!!, document)
                    if (operand is Equal) {
                        return NotEqual(element, element.textRange, operand.operands)
                    }
                } else if (element.operationSign.text == "-") {
                    val operand = BuildExpressionExt.getAnyExpression(element.operand!!, document)
                    return Negate(element, element.textRange, listOf(operand))
                }
            } else if (element.operationSign.text == "-") {
                val operand = BuildExpressionExt.getAnyExpression(element.operand!!, document)
                return Negate(element, element.textRange, listOf(operand))
            }
        }
        return null
    }

    private class MethodCallInformation(
        private val element: PsiMethodCallExpression,
        val qualifierExpression: Expression,
        val methodName: String,
        val className: String,
        val psiClass: PsiClass,
        private val document: Document
    ) {
        val arguments: Array<PsiExpression> = element.argumentList.expressions

        fun getFoldedArgument(index: Int): Expression {
            return BuildExpressionExt.getAnyExpression(element.argumentList.expressions[index], document)
        }

        companion object {
            fun tryGet(
                element: PsiMethodCallExpression,
                document: Document,
                vararg methodNames: String
            ): Optional<MethodCallInformation> {
                return tryGet(
                    element,
                    document,
                    { name: String -> listOf(*methodNames).contains(name) },
                    { _: String, _: String -> true }
                )
            }

            fun tryGet(
                element: PsiMethodCallExpression,
                document: Document,
                isMethodNameSupported: Predicate<String>,
                isMethodSupported: BiPredicate<String, String>
            ): Optional<MethodCallInformation> {
                val referenceExpression = element.methodExpression
                val identifier = Stream.of(*referenceExpression.children)
                    .filter { c: PsiElement? -> c is PsiIdentifier }
                    .findAny()
                val qualifier = element.methodExpression.qualifierExpression
                if (identifier.isPresent && isMethodNameSupported.test(identifier.get().text)) {
                    val method = referenceExpression.resolve() as PsiMethod?
                    if (method != null) {
                        val psiClass = method.containingClass
                        if (psiClass != null && psiClass.qualifiedName != null) {
                            val className = Helper.eraseGenerics(psiClass.qualifiedName!!)
                            val methodName = identifier.get().text
                            if (isMethodSupported.test(className, methodName) && qualifier != null) {
                                val qualifierExpression = BuildExpressionExt.getAnyExpression(qualifier, document)
                                return Optional.of(
                                    MethodCallInformation(
                                        element,
                                        qualifierExpression,
                                        methodName,
                                        className,
                                        psiClass,
                                        document
                                    )
                                )
                            }
                        }
                    }
                }
                return Optional.empty()
            }
        }
    }
}

