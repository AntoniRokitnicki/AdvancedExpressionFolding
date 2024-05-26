package com.intellij.advancedExpressionFolding.extension.methodcall.dynamic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.advancedExpressionFolding.extension.expr
import com.intellij.advancedExpressionFolding.extension.exprWrap
import com.intellij.advancedExpressionFolding.extension.identifier
import com.intellij.advancedExpressionFolding.extension.methodcall.MethodCallFactory
import com.intellij.psi.PsiMethod

object DynamicExt {
    fun createExpression(method: PsiMethod): Expression? {
        return MethodCallFactory.findByMethodName(method.name)?.mapNotNull {
            it.asInstance<DynamicMethodCall>()
        }?.map {
            val newName = it.data.newName
            method.identifier?.expr(newName)
        }?.run {
            exprWrap(method)
        }
    }
}