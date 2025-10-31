package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.expr
import com.intellij.advancedExpressionFolding.processor.exprWrap
import com.intellij.advancedExpressionFolding.processor.identifier
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallFactory
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
