package com.intellij.advancedExpressionFolding.extension.methodcall.dynamic

import com.intellij.advancedExpressionFolding.expression.custom.DynamicExpression
import com.intellij.advancedExpressionFolding.extension.group
import com.intellij.advancedExpressionFolding.extension.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.extension.methodcall.Context
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class DynamicMethodCall(val data: DynamicMethodCallData) : AbstractMethodCall() {

    override fun permission() = dynamic

    override val methodNames by lazy { listOf(data.method) }

    private val dynamicGroup: FoldingGroup by lazy { this::class.group(data.method) }

    override fun onAnyArguments(
        element: PsiMethodCallExpression,
        context: Context,
        expressions: Array<PsiExpression>,
    ): DynamicExpression {
        return DynamicExpression(
            context.identifier,
            text = data.newName,
            children = context.getOperands(),
            group = dynamicGroup
        )
    }

}