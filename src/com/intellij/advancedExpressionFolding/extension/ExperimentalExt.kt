package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.psi.PsiField
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.impl.source.PsiClassReferenceType

object ExperimentalExt : BaseExtension() {
    @JvmStatic
    fun createExpression(element: PsiReferenceExpression): Expression? {
        return experimental.on(element)?.singletonField()
    }

}

private fun PsiReferenceExpression.singletonField(): Expression? {
    return this.resolve().asInstance<PsiField>()?.takeIf {
        it.isStatic() && it.sameClassAsReturnType()
    }?.let {
        this.identifier?.let {
            val exprList = exprList(it.expr(Consts.Emoji.MAN_STANDING.toString()))
            exprList.exprWrap(this)
        }
    }
}

private fun PsiField.sameClassAsReturnType(): Boolean =
    type.asInstance<PsiClassReferenceType>()?.resolve() == containingClass
