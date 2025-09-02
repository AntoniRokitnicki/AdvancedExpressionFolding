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
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.Arrays
import java.util.Collections
import java.util.Optional
import java.util.function.BiPredicate
import java.util.function.Predicate
import java.util.stream.Stream
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression

object PrefixExpressionExt {
    @JvmStatic
    @Nullable
    fun getPrefixExpression(@NotNull element: PsiPrefixExpression, @NotNull document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (element.getOperand() != null) {
            if (element.getOperationSign().getText() == "!") {
                if (settings.getState().getComparingLocalDatesCollapse()) {
                    val operand = element.getOperand()
                    if (operand is PsiMethodCallExpression) {
                        val methodCallInformationOptional = MethodCallInformation.tryGet(operand, document, "isBefore", "isAfter", "before", "after")
                        if (methodCallInformationOptional.isPresent) {
                            val callInformation = methodCallInformationOptional.get()
                            val methodName = callInformation.getMethodName()
                            if (methodName == "isBefore" || methodName == "before") {
                                return GreaterEqual(element, element.getTextRange(), Arrays.asList(callInformation.getQualifierExpression(), callInformation.getFoldedArgument(0)))
                            }
                            if (methodName == "isAfter" || methodName == "after") {
                                return Append.LessEqual(element, element.getTextRange(), Arrays.asList(callInformation.getQualifierExpression(), callInformation.getFoldedArgument(0)))
                            }
                        }
                    }
                }
                if (settings.getState().getComparingExpressionsCollapse()) {
                    val operand = getAnyExpression(element.getOperand(), document)
                    if (operand is Equal) {
                        return NotEqual(element, element.getTextRange(), operand.getOperands())
                    }
                } else if (element.getOperationSign().getText() == "-") {
                    val operand = getAnyExpression(element.getOperand(), document)
                    return Negate(element, element.getTextRange(), Collections.singletonList(operand))
                }
            } else if (element.getOperationSign().getText() == "-") {
                val operand = getAnyExpression(element.getOperand(), document)
                return Negate(element, element.getTextRange(), Collections.singletonList(operand))
            }
        }
        return null
    }

    private class MethodCallInformation(
        private var element: PsiMethodCallExpression,
        private var qualifierExpression: Expression,
        private var methodName: String,
        private var className: String,
        private var psiClass: PsiClass,
        private var document: Document
    ) {
        private var arguments: Array<PsiExpression> = element.getArgumentList().getExpressions()

        fun getQualifierExpression(): Expression { return qualifierExpression }
        fun getMethodName(): String { return methodName }
        fun getFoldedArgument(index: Int): Expression {
            return getAnyExpression(element.getArgumentList().getExpressions()[index], document)
        }

        companion object {
            fun tryGet(element: PsiMethodCallExpression, @NotNull document: Document, vararg methodNames: String): Optional<MethodCallInformation> {
                return tryGet(element, document, Predicate { m: String -> Arrays.asList(*methodNames).contains(m) }) { _, _ -> true }
            }

            fun tryGet(element: PsiMethodCallExpression, @NotNull document: Document, isMethodNameSupported: Predicate<String>, isMethodSupported: BiPredicate<String, String>): Optional<MethodCallInformation> {
                val referenceExpression = element.getMethodExpression()
                val identifier = Stream.of(referenceExpression.getChildren()).filter { c -> c is PsiIdentifier }.findAny()
                val qualifier = element.getMethodExpression().getQualifierExpression()
                if (identifier.isPresent && isMethodNameSupported.test(identifier.get().getText())) {
                    val method = referenceExpression.resolve() as PsiMethod?
                    if (method != null) {
                        val psiClass = method.getContainingClass()
                        if (psiClass != null && psiClass.getQualifiedName() != null) {
                            val className = Helper.eraseGenerics(psiClass.getQualifiedName())
                            val methodName = identifier.get().getText()
                            if (isMethodSupported.test(className, methodName) && qualifier != null) {
                                val qualifierExpression = getAnyExpression(qualifier, document)
                                return Optional.of(MethodCallInformation(element, qualifierExpression, methodName, className, psiClass, document))
                            }
                        }
                    }
                }
                return Optional.empty()
            }
        }
    }
}
