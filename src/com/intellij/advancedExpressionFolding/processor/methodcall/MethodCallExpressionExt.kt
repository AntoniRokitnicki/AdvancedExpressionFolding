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
import java.util.*
import java.util.stream.Stream

import com.intellij.advancedExpressionFolding.processor.util.PropertyUtil.guessPropertyName

object MethodCallExpressionExt {
    fun getMethodCallExpression(element: PsiMethodCallExpression, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val referenceExpression = element.methodExpression
        val identifierOpt = Stream.of(*referenceExpression.children)
            .filter { c: PsiElement? -> c is PsiIdentifier }
            .findAny()
        if (identifierOpt.isEmpty) {
            return null
        }
        val identifier = identifierOpt.get()
        val qualifier = element.methodExpression.qualifierExpression
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

    private fun useMethodCallFactory(
        identifier: PsiElement,
        referenceExpression: PsiReferenceExpression,
        document: Document,
        qualifier: PsiExpression?,
        element: PsiMethodCallExpression
    ): Expression? {
        val factory = MethodCallFoldingLoaderService.factory()
        if (factory.supportedMethods.contains(identifier.text)) {
            val method = referenceExpression.resolve() as PsiMethod?
            if (method != null) {
                val psiClass = method.containingClass
                if (psiClass != null && psiClass.qualifiedName != null) {
                    val className = Helper.eraseGenerics(psiClass.qualifiedName!!)
                    if (factory.supportedClasses.contains(className) || factory.classlessMethods.contains(method.name)) {
                        return onAnyExpression(element, document, qualifier, identifier, className, method)
                    }
                }
            }
        }
        return null
    }

    private fun onAnyExpression(
        element: PsiMethodCallExpression,
        document: Document,
        qualifier: PsiExpression?,
        identifier: PsiElement,
        className: String,
        method: PsiMethod
    ): Expression? {
        val qualifierExpression = qualifier?.let { BuildExpressionExt.getAnyExpression(it, document) }
        val methodName = identifier.text
        val factory = MethodCallFoldingLoaderService.factory()
        val methodCalls = factory.findByMethodName(methodName)
        if (methodCalls != null) {
            for (methodCall in methodCalls) {
                val args = element.argumentList.expressions.map { BuildExpressionExt.getAnyExpression(it, document) }
                val context = Context(methodName, className, qualifierExpression, method, document, identifier, args)
                val expression = methodCall.execute(element, context)
                if (expression != null) {
                    return expression
                }
            }
        }
        return null
    }

    private fun onAnyArguments(
        element: PsiMethodCallExpression,
        settings: AdvancedExpressionFoldingSettings,
        document: Document,
        identifier: PsiElement,
        qualifier: PsiExpression?,
        referenceExpression: PsiReferenceExpression
    ): Expression? {
        if (settings.state.getSetExpressionsCollapse) {
            val result = onGetterSetter(element, document, identifier, qualifier)
            if (result != null) {
                return result
            }
        }
        if (referenceExpression.resolve() is PsiMethod) {
            val psiMethod = referenceExpression.resolve() as PsiMethod
            val psiClass = psiMethod.containingClass
            val result = onGetterRecord(element, settings, document, psiClass, qualifier, identifier)
            if (result != null) {
                return result
            }
        }
        val text = identifier.text
        return LoggerBracketsExt.createExpression(element, text, document)
    }

    private fun onGetterRecord(
        element: PsiMethodCallExpression,
        settings: AdvancedExpressionFoldingSettings,
        document: Document,
        psiClass: PsiClass?,
        qualifier: PsiExpression?,
        identifier: PsiElement
    ): Expression? {
        if (psiClass != null && psiClass.isRecord && element.argumentList.expressionCount == 0) {
            if (settings.state.getSetExpressionsCollapse) {
                val expression = qualifier?.let { BuildExpressionExt.getAnyExpression(it, document) }
                return GetterRecord(
                    element,
                    element.textRange,
                    TextRange.create(identifier.textRange.startOffset, element.textRange.endOffset),
                    expression,
                    guessPropertyName(identifier.text)
                )
            }
        }
        return null
    }

    private fun onGetterSetter(
        element: PsiMethodCallExpression,
        document: Document,
        identifier: PsiElement,
        qualifier: PsiExpression?
    ): Expression? {
        return if (Helper.isGetter(identifier, element)) {
            val expression = qualifier?.let { BuildExpressionExt.getAnyExpression(it, document) }
            Getter(
                element,
                element.textRange,
                TextRange.create(identifier.textRange.startOffset, element.textRange.endOffset),
                expression,
                guessPropertyName(identifier.text)
            )
        } else {
            val text = identifier.text
            if (Helper.isSetter(text) && element.argumentList.expressions.size == 1 && element.parent is PsiStatement &&
                (qualifier !is PsiMethodCallExpression || !Helper.startsWith(qualifier.methodExpression.referenceName, "set"))
            ) {
                val qualifierExpression = qualifier?.let { BuildExpressionExt.getAnyExpression(it, document) }
                val paramExpression = BuildExpressionExt.getAnyExpression(element.argumentList.expressions[0], document)
                val propertyName = guessPropertyName(text)
                Setter(
                    element,
                    element.textRange,
                    TextRange.create(identifier.textRange.startOffset, element.textRange.endOffset),
                    qualifierExpression,
                    propertyName,
                    paramExpression
                )
            } else {
                null
            }
        }
    }
}

