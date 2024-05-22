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
        val body = method.body?.takeIf {
            !it.hasComments()
        } ?: return false
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

    fun isAllArgsConstructor(method: PsiMethod, fields: Collection<PsiField>): Boolean {
        val body = method.body?.takeIf {
            !it.hasComments()
        } ?: return false
        val statements = body.statements.toMutableList()

        val psiFieldPsiStatementMutableMap = mutableMapOf<PsiField, PsiStatement>()
        val psiStatementPsiParameterMutableMap = mutableMapOf<PsiStatement, PsiParameter>()

        statements.forEach { statement ->
            fields.forEach { field ->
                val reference = field.findLocalReference(statement)
                if (reference != null) {
                    psiFieldPsiStatementMutableMap[field] = statement
                }
            }
            method.parameterList.parameters.forEach { parameter ->
                val reference = parameter.findLocalReference(statement)
                if (reference != null) {
                    psiStatementPsiParameterMutableMap[statement] = parameter
                }
            }
        }
        if (statements.size == psiFieldPsiStatementMutableMap.size + 1) {
            (body.statements.toList() - psiStatementPsiParameterMutableMap.keys).singleOrNull()?.takeIf {
                isEmptySuperCall(it)
            }?: return false
        } else {
            (statements.size == psiFieldPsiStatementMutableMap.size).on() ?: return false
        }

        psiFieldPsiStatementMutableMap.all { (field, statement) ->
            val param = psiStatementPsiParameterMutableMap[statement] ?: return@all false
            !method.isDirtyAssignment(statement, field, param)
        }.on() ?: return false
        return true
    }
}


