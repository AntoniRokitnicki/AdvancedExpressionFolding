package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.NullableExt.fieldAnnotationExpression
import com.intellij.advancedExpressionFolding.extension.NullableExt.readCheckNotNullMethods
import com.intellij.advancedExpressionFolding.extension.methodcall.dynamic.DynamicExt
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiParameter

object PsiMethodExt : BaseExtension() {
    fun createExpression(method: PsiMethod, document: Document): Expression? {
        if (interfaceExtensionProperties && method.body == null) {
            // For interfaces only; other functionality was not tested with the interfaces’ methods
            return InterfacePropertiesExt.foldProperties(method)
        }
        val list = exprList(fieldAnnotationExpression(method.annotations, method.returnTypeElement))

        if (summaryParentOverride) {
            SummaryParentExt.summaryParent(method, list)
        }

        list.addIfEnabled(expressionFunc) {
            SingleExpressionFunctionExt.createSingleExpressionFunctions(method, document)
        }

        list.addIfEnabled(dynamic) {
            DynamicExt.createExpression(method)
        }

        // may lead to issues
        if (true) {
            getAnyExpression(method.modifierList, document).let(list::add)
            method.returnTypeElement?.let {
                getAnyExpression(it, document).let(list::add)
            }
            getAnyExpressions(method.body?.statements, document).let(list::addAll)
            getAnyExpressions(method.parameterList.parameters, document).let(list::addAll)
        }
        return list.exprWrap(method)
    }

    fun createExpression(parameter: PsiParameter, document: Document): Expression? {
        if (interfaceExtensionProperties && InterfacePropertiesExt.ignoreFoldingParameter(parameter)) {
            return null
        }

        val list = exprList(readCheckNotNullMethods(parameter, document))
        list += fieldAnnotationExpression(
            parameter.annotations,
            parameter.typeElement,
            true
        )
        return list.exprWrap(parameter)
    }
}