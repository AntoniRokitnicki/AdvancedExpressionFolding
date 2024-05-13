package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.psi.PsiField
import com.intellij.psi.PsiReferenceExpression

object ExperimentalExt : BaseExtension() {

    @JvmStatic
    fun createExpression(element: PsiReferenceExpression): Expression? {
        return experimental.on(element)?.singletonField()
    }

    private fun PsiReferenceExpression.singletonField(): Expression? {
        return resolve().asInstance<PsiField>()?.takeIf {
            it.isStatic() && it.singletonField
        }?.let {
            identifier?.let {
                val exprList = exprList(it.expr(Consts.Emoji.MAN_STANDING.toString()))
                exprList.exprWrap(this)
            }
        }
    }

}
