package com.intellij.advancedExpressionFolding.processor

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.util.Consts
import com.intellij.psi.PsiField
import com.intellij.psi.PsiReferenceExpression


object ExperimentalExt : BaseExtension() {

    @JvmStatic
    fun createExpression(element: PsiReferenceExpression): Expression? {
        return emojify.on(element)?.singletonField()
    }

    private fun PsiReferenceExpression.singletonField(): Expression? {
        return resolve().asInstance<PsiField>()?.takeIf {
            it.isStatic() && it.singletonField
        }?.let {
            identifier?.takeIf {
                it.textMatches("INSTANCE")
            }?.let {
                val exprList = exprList(it.expr(Consts.Emoji.SINGLETON_MAN_STANDING.toString()))
                exprList.exprWrap(this)
            }
        }
    }

}
