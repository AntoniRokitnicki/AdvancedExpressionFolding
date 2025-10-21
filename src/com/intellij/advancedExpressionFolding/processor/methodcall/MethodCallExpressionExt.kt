package com.intellij.advancedExpressionFolding.processor.methodcall

import com.intellij.advancedExpressionFolding.MethodCallFoldingLoaderService
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.property.Getter
import com.intellij.advancedExpressionFolding.expression.property.GetterRecord
import com.intellij.advancedExpressionFolding.expression.property.Setter
import com.intellij.advancedExpressionFolding.processor.argumentExpressions
import com.intellij.advancedExpressionFolding.processor.argumentCount
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.language.FieldShiftExt
import com.intellij.advancedExpressionFolding.processor.logger.LoggerBracketsExt
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.advancedExpressionFolding.processor.util.PropertyUtil.guessPropertyName
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiMethodCallExpression
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.PsiStatement

object MethodCallExpressionExt : BaseExtension() {

    fun getMethodCallExpression(element: PsiMethodCallExpression, document: Document): Expression? {
        val settings = AdvancedExpressionFoldingSettings.getInstance()
        val referenceExpression = element.methodExpression
        val identifier = referenceExpression.children.firstOrNull { it is PsiIdentifier } ?: return null
        val qualifier = referenceExpression.qualifierExpression

        FieldShiftExt.createExpression(element, document, qualifier)?.let { return it }

        useMethodCallFactory(identifier, referenceExpression, document, qualifier, element)?.let { return it }

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
        if (!factory.supportedMethods.contains(identifier.text)) {
            return null
        }
        val method = referenceExpression.resolve() as? PsiMethod ?: return null
        val psiClass = method.containingClass ?: return null
        val qualifiedName = psiClass.qualifiedName ?: return null
        val className = Helper.eraseGenerics(qualifiedName)
        val supported = factory.supportedClasses.contains(className) || factory.classlessMethods.contains(method.name)
        return if (supported) {
            onAnyExpression(element, document, qualifier, identifier, className, method)
        } else {
            null
        }
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
        val methodCalls = factory.findByMethodName(methodName) ?: return null
        for (methodCall in methodCalls) {
            val args = element.argumentExpressions.map { BuildExpressionExt.getAnyExpression(it, document) }
            val context = Context(methodName, className, qualifierExpression, method, document, identifier, args)
            val expression = methodCall.execute(element, context)
            if (expression != null) {
                return expression
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
            onGetterSetter(
                element,
                document,
                identifier,
                qualifier,
                settings.state.experimental
            )?.let { return it }
        }
        val resolved = referenceExpression.resolve()
        if (resolved is PsiMethod) {
            val psiClass = resolved.containingClass
            onGetterRecord(element, settings, document, psiClass, qualifier, identifier)?.let { return it }
        }
        val text = identifier.text
        return LoggerBracketsExt.createExpression(element, text, document)
    }

    private fun onGetterRecord(
        element: PsiMethodCallExpression,
        settings: AdvancedExpressionFoldingSettings,
        document: Document,
        psiClass: com.intellij.psi.PsiClass?,
        qualifier: PsiExpression?,
        identifier: PsiElement
    ): Expression? {
        if (psiClass != null && psiClass.isRecord && element.argumentCount == 0) {
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
        qualifier: PsiExpression?,
        experimental: Boolean
    ): Expression? {
        val resolvedMethod = element.resolveMethod()
        if (
            Helper.isGetter(identifier, element) ||
            experimental && Helper.isNamelessGetter(identifier.text, element, resolvedMethod)
        ) {
            val expression = qualifier?.let { BuildExpressionExt.getAnyExpression(it, document) }
            val propertyName = guessPropertyName(identifier.text).ifBlank {
                Helper.guessNamelessPropertyName(resolvedMethod)
            }
            return Getter(
                element,
                element.textRange,
                TextRange.create(identifier.textRange.startOffset, element.textRange.endOffset),
                expression,
                propertyName
            )
        }
        val text = identifier.text
        if (isSimpleSetter(text, element, qualifier, experimental, resolvedMethod)) {
            val qualifierExpression = qualifier?.let { BuildExpressionExt.getAnyExpression(it, document) }
            val paramExpression = BuildExpressionExt.getAnyExpression(element.argumentExpressions[0], document)
            val propertyName = guessPropertyName(text).ifBlank {
                Helper.guessNamelessPropertyName(resolvedMethod)
            }
            return Setter(
                element,
                element.textRange,
                TextRange.create(identifier.textRange.startOffset, element.textRange.endOffset),
                qualifierExpression,
                propertyName,
                paramExpression
            )
        }
        return null
    }

    private fun isSimpleSetter(
        text: String,
        element: PsiMethodCallExpression,
        qualifier: PsiExpression?,
        experimental: Boolean,
        method: PsiMethod?
    ): Boolean {
        val isNamelessSetter = experimental && Helper.isNamelessSetter(text, element, method)
        if (!Helper.isSetter(text) && !isNamelessSetter) {
            return false
        }
        if (element.argumentCount != 1) {
            return false
        }
        if (element.parent !is PsiStatement) {
            return false
        }
        if (qualifier is PsiMethodCallExpression) {
            val referenceName = qualifier.methodExpression.referenceName
            if (referenceName != null && Helper.startsWith(referenceName, "set")) {
                return false
            }
        }
        return true
    }
}
