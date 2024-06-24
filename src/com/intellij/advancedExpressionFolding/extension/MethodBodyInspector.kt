package com.intellij.advancedExpressionFolding.extension

import com.intellij.psi.*

object MethodBodyInspector {

    fun PsiMethod.isDirtyGetter(): Boolean {
        val field = this.propertyField ?: return false
        return body
            ?.statements
            ?.singleOrNull()
            .asInstance<PsiReturnStatement>()
            ?.returnValue
            .asInstance<PsiReferenceExpression>()
            ?.resolve() != field
    }

    fun PsiMethod.isDirtySetter(): Boolean {
        val field: PsiField = this.propertyField ?: return false
        val statement = body
            ?.statements
            ?.singleOrNull()
        val firstParam = parameterList.parameters.singleOrNull() ?: return false
        return isDirtyAssignment(statement, field, firstParam)
    }

    private fun PsiMethod.isDirtyAssignment(
        statement: PsiStatement?,
        field: PsiField,
        param: PsiParameter
    ): Boolean {
        val assignment = statement
            .asInstance<PsiExpressionStatement>()
            ?.expression
            .asInstance<PsiAssignmentExpression>() ?: return true

        // check `this.*data* = data;` point to correct field
        assignment.lExpression
            .asInstance<PsiReferenceExpression>()
            ?.takeIf {
                it.qualifier.isInstance<PsiThisExpression>()
            }?.takeIf { thisField ->
                thisField.resolve() == field
            } ?: return true

        // check `this.data = *data*;` is same as method param
        assignment.rExpression.asInstance<PsiReferenceExpression>()?.takeIf {
            it.resolve() == param
        } ?: return true

        return false
    }

    fun isPureNoArgsConstructor(method: PsiMethod): Boolean {
        val body = method.body ?: return false
        return (body.statementCount == 0) || isSuperNoArgsConstructor(body)
    }

    private fun isSuperNoArgsConstructor(body: PsiCodeBlock): Boolean {
        val statement = body.statements
            .takeIfSize(1)
            ?.firstOrNull()
        return isEmptySuperCall(statement)
    }

    private fun isEmptySuperCall(statement: PsiStatement?): Boolean {
        val callExpression = statement.asInstance<PsiExpressionStatement>()
            ?.expression.asInstance<PsiMethodCallExpression>() ?: return false
        return callExpression
            .argumentList
            .expressionCount == 0 &&
                callExpression
                    .methodExpression.asInstance<PsiReferenceExpression>()
                    ?.text == "super"
    }

    fun isAllArgsConstructor(
        method: PsiMethod,
        fields: Collection<PsiField>
    ) = createParameterFieldMap(method, fields)?.isNotEmpty() == true

    fun createParameterFieldMap(
        method: PsiMethod,
        fields: Collection<PsiField>
    ): Map<PsiParameter, PsiField>? {
        val body = method.body?.takeIf {
            !it.hasComments()
        } ?: return null
        val statements = body.statements.toMutableList()

        val fieldToStatementMap = mutableMapOf<PsiField, PsiStatement>()
        val statementToParameterMap = mutableMapOf<PsiStatement, PsiParameter>()
        statements.forEach { statement ->
            fields.forEach { field ->
                val reference = field.findLocalReference(statement)
                if (reference != null) {
                    fieldToStatementMap[field] = statement
                }
            }
            method.parameterList.parameters.forEach { parameter ->
                val reference = parameter.findLocalReference(statement)
                if (reference != null) {
                    statementToParameterMap[statement] = parameter
                }
            }
        }
        if (statements.size == fieldToStatementMap.size + 1) {
            (body.statements.toList() - statementToParameterMap.keys).singleOrNull()?.takeIf {
                isEmptySuperCall(it)
            } ?: return null
        } else {
            if (statements.size != fieldToStatementMap.size) {
                return null
            }
        }

        if (fieldToStatementMap.size != statementToParameterMap.size ||
            !(fieldToStatementMap.isUnique() && statementToParameterMap.isUnique())) {
            return null
        }

        return if (isNotDirty(fieldToStatementMap, statementToParameterMap, method)) {
            statementToParameterMap.mapNotNull {
                val statement = it.key
                val field = fieldToStatementMap.entries.firstOrNull { entry ->
                    entry.value == statement
                }?.key ?: return@mapNotNull null
                it.value to field
            }.toMap()
        } else {
            null

        }
    }

    private fun isNotDirty(
        fieldToStatementMap: MutableMap<PsiField, PsiStatement>,
        statementToParameterMap: MutableMap<PsiStatement, PsiParameter>,
        method: PsiMethod
    ) = fieldToStatementMap.all { (field, statement) ->
        val param = statementToParameterMap[statement] ?: return@all false
        !method.isDirtyAssignment(statement, field, param)
    }

}






