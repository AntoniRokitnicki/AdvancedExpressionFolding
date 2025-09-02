package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.math.basic.*
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append
import com.intellij.advancedExpressionFolding.expression.operation.basic.Equal
import com.intellij.advancedExpressionFolding.expression.operation.basic.Greater
import com.intellij.advancedExpressionFolding.expression.operation.basic.GreaterEqual
import com.intellij.advancedExpressionFolding.expression.operation.collection.Range
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.Arrays
import java.util.Optional
import java.util.stream.Stream
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression
import com.intellij.advancedExpressionFolding.processor.util.Helper.eraseGenerics

object BinaryExpressionExt {
    @JvmStatic
    @Nullable
    fun getBinaryExpression(@NotNull element: PsiBinaryExpression, @NotNull document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if ((element.getLOperand() is PsiMethodCallExpression && isLiteralOrNegatedLiteral(element.getROperand())
                    || element.getROperand() is PsiMethodCallExpression && isLiteralOrNegatedLiteral(element.getLOperand()))
            && settings.getState().getComparingExpressionsCollapse()
        ) {
            val methodCallExpression = if (element.getLOperand() is PsiMethodCallExpression)
                element.getLOperand() as PsiMethodCallExpression
            else
                element.getROperand() as PsiMethodCallExpression
            val literalExpression = if (isLiteralOrNegatedLiteral(element.getLOperand())) element.getLOperand() else element.getROperand()
            if (literalExpression != null && (literalExpression.getText() == "0" || literalExpression.getText() == "-1" || literalExpression.getText() == "1")) {
                val identifier: Optional<PsiElement> = Stream.of(methodCallExpression.getMethodExpression().getChildren()).filter { c -> c is PsiIdentifier }.findAny()
                if (identifier.isPresent && identifier.get().getText() == "compareTo" && methodCallExpression.getArgumentList().getExpressions().size == 1) {
                    val method = methodCallExpression.getMethodExpression().resolve() as PsiMethod?
                    if (method != null) {
                        val psiClass = method.getContainingClass()
                        if (psiClass != null && (psiClass.getQualifiedName() != null && Consts.SUPPORTED_CLASSES.contains(eraseGenerics(psiClass.getQualifiedName()))
                                    || Consts.UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS.contains(method.getName()))) {
                            val qualifier = methodCallExpression.getMethodExpression().getQualifierExpression()?.let { getAnyExpression(it, document) }
                            if (qualifier != null) {
                                val argument = getAnyExpression(methodCallExpression.getArgumentList().getExpressions()[0], document)
                                val operationSign = element.getOperationSign().getText()
                                val expression = Integer.parseInt(literalExpression.getText())
                                var lessOperation = "<"
                                var greaterOperation = ">"
                                if (literalExpression == element.getLOperand()) {
                                    lessOperation = ">"
                                    greaterOperation = "<"
                                }
                                if (operationSign == "==") {
                                    when (expression) {
                                        -1 -> return Append.Less(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                        0 -> return Equal(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                        1 -> return Greater(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                    }
                                } else if (operationSign == "!=") {
                                    when (expression) {
                                        1 -> return Append.LessEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                        0 -> return NotEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                        -1 -> return GreaterEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                    }
                                } else if (operationSign == lessOperation) {
                                    when (expression) {
                                        1 -> return Append.LessEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                        0 -> return Append.Less(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                    }
                                } else if (operationSign == greaterOperation) {
                                    when (expression) {
                                        -1 -> return GreaterEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                        0 -> return Greater(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                    }
                                } else if (operationSign == lessOperation + "=") {
                                    when (expression) {
                                        -1 -> return Append.Less(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                        0 -> return Append.LessEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                    }
                                } else if (operationSign == greaterOperation + "=") {
                                    when (expression) {
                                        1 -> return Greater(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                        0 -> return GreaterEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (Consts.SUPPORTED_BINARY_OPERATORS.contains(element.getOperationSign().getText()) && element.getROperand() != null) {
            val leftExpression = getAnyExpression(element.getLOperand(), document)
            val rightExpression = getAnyExpression(element.getROperand(), document)
            when (element.getOperationSign().getText()) {
                "+" -> return Add(element, element.getTextRange(), Arrays.asList(leftExpression, rightExpression))
                "-" -> return Subtract(element, element.getTextRange(), Arrays.asList(leftExpression, rightExpression))
                "*" -> return Multiply(element, element.getTextRange(), Arrays.asList(leftExpression, rightExpression))
                "/" -> return Divide(element, element.getTextRange(), Arrays.asList(leftExpression, rightExpression))
            }
        }
        if ("&&" == element.getOperationSign().getText()
            && element.getLOperand() is PsiBinaryExpression
            && element.getROperand() is PsiBinaryExpression
        ) {
            return getAndTwoBinaryExpressions(element, element.getLOperand() as PsiBinaryExpression, element.getROperand() as PsiBinaryExpression, document)
        }
        return null
    }

    @JvmStatic
    fun isLiteralOrNegatedLiteral(element: PsiElement?): Boolean {
        return element is PsiLiteralExpression
                || element is PsiPrefixExpression && element.getOperand() is PsiLiteralExpression && "-" == element.getOperationSign().getText()
    }

    @JvmStatic
    @Nullable
    fun getAndTwoBinaryExpressions(@NotNull parent: PsiElement, @NotNull a: PsiBinaryExpression, @NotNull b: PsiBinaryExpression, @NotNull document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        if (settings.getState().getRangeExpressionsCollapse()) {
            if ((a.getOperationSign().getText() == "<" || a.getOperationSign().getText() == "<=")
                && (b.getOperationSign().getText() == ">" || b.getOperationSign().getText() == ">=")
                && a.getROperand() != null && b.getROperand() != null
            ) {
                val e1 = getAnyExpression(a.getLOperand(), document)
                val e2 = getAnyExpression(a.getROperand(), document)
                val e3 = getAnyExpression(b.getLOperand(), document)
                val e4 = getAnyExpression(b.getROperand(), document)
                if (e1 == e3) {
                    return Range(parent, TextRange.create(a.getTextRange().getStartOffset(), b.getTextRange().getEndOffset()), e1, e4, b.getOperationSign().getText() == ">=", e2, a.getOperationSign().getText() == "<=")
                }
            }
            if ((a.getOperationSign().getText() == ">" || a.getOperationSign().getText() == ">=")
                && (b.getOperationSign().getText() == "<" || b.getOperationSign().getText() == "<=")
                && a.getROperand() != null && b.getROperand() != null
            ) {
                val e1 = getAnyExpression(a.getLOperand(), document)
                val e2 = getAnyExpression(a.getROperand(), document)
                val e3 = getAnyExpression(b.getLOperand(), document)
                val e4 = getAnyExpression(b.getROperand(), document)
                if (e1 == e3) {
                    return Range(parent, TextRange.create(a.getTextRange().getStartOffset(), b.getTextRange().getEndOffset()), e1, e2, a.getOperationSign().getText() == ">=", e4, b.getOperationSign().getText() == "<=")
                }
            }
        }
        return null
    }
}
