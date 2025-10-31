package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.Negate
import com.intellij.advancedExpressionFolding.expression.math.basic.NotEqual
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append
import com.intellij.advancedExpressionFolding.expression.operation.basic.Equal
import com.intellij.advancedExpressionFolding.expression.operation.basic.GreaterEqual
import com.intellij.advancedExpressionFolding.processor.argumentExpressions
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.IDateOperationsState
import com.intellij.advancedExpressionFolding.settings.IExpressionCollapseState
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiPrefixExpression

object PrefixExpressionExt :
    IDateOperationsState by AdvancedExpressionFoldingSettings.State()(),
    IExpressionCollapseState by AdvancedExpressionFoldingSettings.State()() {

    fun getPrefixExpression(element: PsiPrefixExpression, document: Document): Expression? {
        val operand = element.operand ?: return null
        val sign = element.operationSign.text
        return when (sign) {
            "!" -> handleNegation(element, operand, document)
            "-" -> {
                val expression = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(operand, document)
                Negate(element, element.textRange, listOf(expression))
            }
            else -> null
        }
    }

    private fun handleNegation(
        element: PsiPrefixExpression,
        operand: PsiExpression,
        document: Document
    ): Expression? {
        if (comparingLocalDatesCollapse && operand is PsiMethodCallExpression) {
            val info = MethodCallInformation.tryGet(operand, document, "isBefore", "isAfter", "before", "after")
            if (info != null) {
                return when (info.methodName) {
                    "isBefore", "before" -> GreaterEqual(
                        element,
                        element.textRange,
                        listOf(info.qualifierExpression, info.getFoldedArgument(0))
                    )
                    "isAfter", "after" -> Append.LessEqual(
                        element,
                        element.textRange,
                        listOf(info.qualifierExpression, info.getFoldedArgument(0))
                    )
                    else -> null
                }
            }
        }
        if (comparingExpressionsCollapse) {
            val foldedOperand = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(operand, document)
            if (foldedOperand is Equal) {
                return NotEqual(element, element.textRange, foldedOperand.operands)
            }
        }
        return null
    }

    private data class MethodCallInformation(
        val element: PsiMethodCallExpression,
        val qualifierExpression: Expression,
        val methodName: String,
        val className: String,
        val psiClass: PsiClass,
        val document: Document
    ) {
        fun getFoldedArgument(index: Int): Expression {
            return com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(
                element.argumentExpressions[index],
                document
            )
        }

        companion object {
            fun tryGet(
                element: PsiMethodCallExpression,
                document: Document,
                vararg methodNames: String
            ): MethodCallInformation? {
                return tryGet(element, document, methodNames::contains) { _, _ -> true }
            }

            private inline fun tryGet(
                element: PsiMethodCallExpression,
                document: Document,
                isMethodNameSupported: (String) -> Boolean,
                isMethodSupported: (String, String) -> Boolean
            ): MethodCallInformation? {
                val referenceExpression = element.methodExpression
                val identifier = referenceExpression.children.firstOrNull { it is PsiIdentifier } as? PsiIdentifier ?: return null
                val qualifier = referenceExpression.qualifierExpression ?: return null
                if (!isMethodNameSupported(identifier.text)) {
                    return null
                }
                val method = referenceExpression.resolve() as? PsiMethod ?: return null
                val psiClass = method.containingClass ?: return null
                val qualifiedName = psiClass.qualifiedName ?: return null
                val className = Helper.eraseGenerics(qualifiedName)
                val methodName = identifier.text
                if (!isMethodSupported(className, methodName)) {
                    return null
                }
                val qualifierExpression = com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression(qualifier, document)
                return MethodCallInformation(element, qualifierExpression, methodName, className, psiClass, document)
            }
        }
    }
}
