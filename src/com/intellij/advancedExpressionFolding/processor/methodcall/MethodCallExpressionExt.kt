package com.intellij.advancedExpressionFolding.processor.methodcall

import com.intellij.advancedExpressionFolding.MethodCallFoldingLoaderService
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.property.Getter
import com.intellij.advancedExpressionFolding.expression.property.GetterRecord
import com.intellij.advancedExpressionFolding.expression.property.Setter
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.language.FieldShiftExt
import com.intellij.advancedExpressionFolding.processor.logger.LoggerBracketsExt
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.Arrays
import java.util.Optional
import java.util.stream.Stream
import com.intellij.advancedExpressionFolding.processor.util.PropertyUtil.guessPropertyName

object MethodCallExpressionExt {
    @JvmStatic
    @Nullable
    fun getMethodCallExpression(element: PsiMethodCallExpression, @NotNull document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val referenceExpression = element.getMethodExpression()
        val identifierOpt = Stream.of(referenceExpression.getChildren()).filter { c -> c is PsiIdentifier }.findAny()
        if (identifierOpt.isEmpty) {
            return null
        }
        val identifier = identifierOpt.orElseThrow()
        val qualifier = element.getMethodExpression().getQualifierExpression()
        val shiftExpr = FieldShiftExt.createExpression(element, document, qualifier)
        if (shiftExpr != null) {
            return shiftExpr
        }
        val factoryResult = useMethodCallFactory(identifier, referenceExpression, document, qualifier, element)
        if (factoryResult != null) {
            return factoryResult
        }
        return onAnyArguments(element, settings, document, identifier, qualifier, referenceExpression)
    }

    private fun useMethodCallFactory(identifier: PsiElement, referenceExpression: PsiReferenceExpression, @NotNull document: Document, qualifier: PsiExpression?, element: PsiMethodCallExpression): Expression? {
        val factory = MethodCallFoldingLoaderService.factory()
        if (factory.getSupportedMethods().contains(identifier.getText())) {
            val method = referenceExpression.resolve() as PsiMethod?
            if (method != null) {
                val psiClass = method.getContainingClass()
                if (psiClass != null && psiClass.getQualifiedName() != null) {
                    val className = Helper.eraseGenerics(psiClass.getQualifiedName())
                    if (factory.getSupportedClasses().contains(className) || factory.getClasslessMethods().contains(method.getName())) {
                        return onAnyExpression(element, document, qualifier, identifier, className, method)
                    }
                }
            }
        }
        return null
    }

    private fun onAnyExpression(element: PsiMethodCallExpression, @NotNull document: Document, qualifier: PsiExpression?, identifier: PsiElement, className: String, method: PsiMethod): Expression? {
        val qualifierExpression = if (qualifier != null) BuildExpressionExt.getAnyExpression(qualifier, document) else null
        val methodName = identifier.getText()
        val factory = MethodCallFoldingLoaderService.factory()
        val methodCalls = factory.findByMethodName(methodName)
        if (methodCalls != null) {
            for (methodCall in methodCalls) {
                val args = Arrays.stream(element.getArgumentList().getExpressions()).map { arg -> BuildExpressionExt.getAnyExpression(arg, document) }.toList()
                val context = Context(methodName, className, qualifierExpression, method, document, identifier, args)
                val expression = methodCall.execute(element, context)
                if (expression != null) {
                    return expression
                }
            }
        }
        return null
    }

    private fun onAnyArguments(element: PsiMethodCallExpression, settings: AdvancedExpressionFoldingSettings, document: Document, identifier: PsiElement, qualifier: PsiExpression?, referenceExpression: PsiReferenceExpression): Expression? {
        if (settings.getState().getGetSetExpressionsCollapse()) {
            val result = onGetterSetter(element, document, identifier, qualifier)
            if (result != null) {
                return result
            }
        }
        if (referenceExpression.resolve() is PsiMethod) {
            val psiClass = (referenceExpression.resolve() as PsiMethod).getContainingClass()
            val result = onGetterRecord(element, settings, document, psiClass, qualifier, identifier)
            if (result != null) {
                return result
            }
        }
        val text = identifier.getText()
        return LoggerBracketsExt.createExpression(element, text, document)
    }

    private fun onGetterRecord(element: PsiMethodCallExpression, settings: AdvancedExpressionFoldingSettings, document: Document, psiClass: PsiClass?, qualifier: PsiExpression?, identifier: PsiElement): Expression? {
        if (psiClass != null && psiClass.isRecord() && element.getArgumentList().getExpressionCount() == 0) {
            if (settings.getState().getGetSetExpressionsCollapse()) {
                val expression = if (qualifier != null) BuildExpressionExt.getAnyExpression(qualifier, document) else null
                return GetterRecord(
                    element,
                    element.getTextRange(),
                    TextRange.create(identifier.getTextRange().getStartOffset(), element.getTextRange().getEndOffset()),
                    expression,
                    guessPropertyName(identifier.getText())
                )
            }
        }
        return null
    }

    private fun onGetterSetter(element: PsiMethodCallExpression, document: Document, identifier: PsiElement, qualifier: PsiExpression?): Expression? {
        if (Helper.isGetter(identifier, element)) {
            val expression = if (qualifier != null) BuildExpressionExt.getAnyExpression(qualifier, document) else null
            return Getter(
                element,
                element.getTextRange(),
                TextRange.create(identifier.getTextRange().getStartOffset(), element.getTextRange().getEndOffset()),
                expression,
                guessPropertyName(identifier.getText())
            )
        } else {
            val text = identifier.getText()
            if (Helper.isSetter(text)
                && element.getArgumentList().getExpressions().size == 1
                && element.getParent() is PsiStatement
                && (!(qualifier is PsiMethodCallExpression) || !Helper.startsWith((qualifier as PsiMethodCallExpression).getMethodExpression().getReferenceName(), "set"))
            ) {
                val qualifierExpression = if (qualifier != null) BuildExpressionExt.getAnyExpression(qualifier, document) else null
                val paramExpression = BuildExpressionExt.getAnyExpression(element.getArgumentList().getExpressions()[0], document)
                val propertyName = guessPropertyName(text)
                return Setter(
                    element,
                    element.getTextRange(),
                    TextRange.create(identifier.getTextRange().getStartOffset(), element.getTextRange().getEndOffset()),
                    qualifierExpression,
                    propertyName,
                    paramExpression
                )
            }
        }
        return null
    }
}
