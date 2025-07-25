package com.intellij.advancedExpressionFolding.processor.language.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.psi.*
import com.jetbrains.rd.util.firstOrNull

object MethodDefaultParameterExt : BaseExtension(){

    fun enhanceMethodsWithDefaultParams(clazz: PsiClass): Expression? {
        val defaultParamMethods = findMethodsWithDefaultParams(clazz)
        return buildExpressions(defaultParamMethods, clazz)
    }

    private fun findMethodsWithDefaultParams(clazz: PsiClass): List<DefaultValue> = clazz.methods.filter {
        it.body != null
    }.groupBy {
        it.name
    }.values.mapNotNull { methods ->
        methods.groupBy {
            getParentMethod(it)
        }.filterKeys {
            it != null
        }.firstOrNull()?.let { (mainMethod, duplicates) ->
            findDefaultValuesForMostParams(mainMethod!!, duplicates)
        }
    }

    private fun findDefaultValuesForMostParams(
        mainMethod: PsiMethod,
        duplicates: List<PsiMethod>
    ): DefaultValue? {
        val paramMap = methodToDefaultValues(duplicates)
        return paramMap.entries.maxByOrNull { (_, paramsMap) ->
            paramsMap.map.size
        }?.let { (method, params) ->
            DefaultValue(mainMethod, params, method)
        }
    }

    private fun buildExpressions(
        defaultParamMethods: List<DefaultValue>,
        clazz: PsiClass
    ): Expression? {
        val list = exprList()
        defaultParamMethods.forEach { (method, params, duplicatedMethod) ->
            val group = group()
            params.map.forEach { (paramNr, value) ->
                val paramWithValue = method.parameterList.parameters.getOrNull(paramNr)
                val paramNextElement = paramWithValue?.nextSibling
                list += paramNextElement?.expr(" = $value" + paramNextElement.text, group = group)
            }
            if (params.map.isNotEmpty()) {
                list += duplicatedMethod.exprHide(group = group)
            }
        }
        return list.exprWrap(clazz)
    }

    private fun methodToDefaultValues(duplicates: List<PsiMethod>): Map<PsiMethod, DefaultParameterMap> {
        return duplicates.associateWith { method ->
            val methodCall = getFirstMethodCallExpression(method)
            val expressions = methodCall?.argumentList?.expressions
            val params = expressions?.mapIndexedNotNull { index, psiExpression ->
                if (asPsiParameter(psiExpression) == null) {
                    index to psiExpression.text
                } else {
                    null
                }
            }?.toMap() ?: emptyMap()
            DefaultParameterMap(params)
        }
    }

    private fun asPsiParameter(psiExpression: PsiExpression?) =
        psiExpression.asInstance<PsiReferenceExpression>()?.resolve().asInstance<PsiParameter>()

    private fun getParentMethod(candidate: PsiMethod): PsiMethod? {
        return getFirstMethodCallExpression(candidate)?.methodExpression?.resolve().asInstance<PsiMethod>()
            ?.takeIf { parentMethod ->
                hasSameAttributes(parentMethod, candidate)
            }
    }

    private fun hasSameAttributes(parentMethod: PsiMethod, candidate: PsiMethod): Boolean {
        return parentMethod.name == candidate.name &&
                parentMethod.containingClass == candidate.containingClass &&
                parentMethod.modifier() == candidate.modifier() &&
                parentMethod.isStatic() == candidate.isStatic() &&
                hasSamePrefixParameterTypes(parentMethod, candidate)
    }

    private fun hasSamePrefixParameterTypes(parentMethod: PsiMethod, candidate: PsiMethod): Boolean {
        return candidate.parameterList.parameters.zip(parentMethod.parameterList.parameters)
            .all { (param1, param2) ->
                param1.type.canonicalText == param2.type.canonicalText
            }
    }

    private fun getFirstMethodCallExpression(method: PsiMethod): PsiMethodCallExpression? {
        return when (val firstStatement = method.body?.statements?.singleOrNull()) {
            is PsiReturnStatement -> firstStatement.returnValue.asInstance<PsiMethodCallExpression>()
            is PsiMethodCallExpression -> firstStatement
            else -> null
        }
    }

    private data class DefaultValue(
        val parentMethod: PsiMethod,
        val defaultParams: DefaultParameterMap,
        val overloadMethod: PsiMethod
    )

    @JvmInline
    private value class DefaultParameterMap(val map: Map<ParameterIndex, ParameterDefaultValueAsString>)
}

typealias ParameterIndex = Int
typealias ParameterDefaultValueAsString = String

