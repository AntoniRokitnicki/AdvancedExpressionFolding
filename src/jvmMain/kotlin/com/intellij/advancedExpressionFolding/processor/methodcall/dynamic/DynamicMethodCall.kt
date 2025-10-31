package com.intellij.advancedExpressionFolding.processor.methodcall.dynamic

import com.intellij.advancedExpressionFolding.expression.semantic.DynamicExpression
import com.intellij.advancedExpressionFolding.processor.group
import com.intellij.advancedExpressionFolding.processor.methodcall.AbstractMethodCall
import com.intellij.advancedExpressionFolding.processor.methodcall.Context
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiMethodCallExpression

class DynamicMethodCall(val data: DynamicMethodCallData) : AbstractMethodCall() {

    override fun canExecute() = dynamic

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
