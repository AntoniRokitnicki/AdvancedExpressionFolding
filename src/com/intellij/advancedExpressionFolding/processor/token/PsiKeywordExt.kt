package com.intellij.advancedExpressionFolding.processor.token

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.util.Consts.Emoji
import com.intellij.psi.*

object PsiKeywordExt : BaseExtension() {

    fun createExpression(keyword: PsiKeyword): Expression? =
        finalRemoval.takeIfTrue(keyword)?.finalRemoval() ?: finalEmoji.takeIfTrue(keyword)?.finalEmoji()

    private fun PsiKeyword.finalEmoji(): Expression? = foldFinalsExceptFields { expr(Emoji.FINAL_LOCK.toString()) }

    private fun PsiKeyword.finalRemoval(): Expression? = foldFinalsExceptFields { exprHide() }

    private inline fun PsiKeyword.foldFinalsExceptFields(foldSpaces: Boolean = false, folder: PsiElement.() -> Expression?): Expression? {
        if (text == PsiKeyword.FINAL) {
            val list = exprList()
            var ignore = false

            when (val realParent = this.parent.parent) {
                //TODO: this depends where final is in the modifier list, adjust
                is PsiMethod -> {
                    realParent.prevWhiteSpace()?.let {
                        list += it.exprHide()
                    }
                }

                is PsiLocalVariable -> {
                    realParent.nextWhiteSpace()?.let {
                        list += it.exprHide()
                    }
                }

                is PsiParameter -> {
                    realParent.nextWhiteSpace()?.let {
                        list += it.exprHide()
                    }
                }

                is PsiField -> {
                    // this doesn't happen now, fields final never enter this method.
                    // it might be a bug, and it might change in the future.
                    ignore = true
                }
            }
            if (!foldSpaces) {
                list.clear()
            }
            if (!ignore) {
                list += this.folder()
            }
            return list.exprWrap(this)
        }
        return null
    }


}

