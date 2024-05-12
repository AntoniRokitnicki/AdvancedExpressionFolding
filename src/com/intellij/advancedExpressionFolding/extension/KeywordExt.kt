package com.intellij.advancedExpressionFolding.extension

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.custom.WrapperExpression
import com.intellij.advancedExpressionFolding.extension.Consts.Emoji
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.nextWhiteSpace
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.prevWhiteSpace
import com.intellij.psi.*

object KeywordExt : BaseExtension() {

    @JvmStatic
    fun createExpression(keyword: PsiKeyword): Expression? {
        return finalRemoval.on(keyword)?.finalRemoval() ?: finalEmoji.on(keyword)?.finalEmoji()
    }

    private fun PsiKeyword.finalEmoji(): Expression? = foldFinalsExceptFields { expr(Emoji.LOCK.toString()) }

    private fun PsiKeyword.finalRemoval(): Expression? = foldFinalsExceptFields { exprHide() }

    private inline fun PsiKeyword.foldFinalsExceptFields(foldSpaces: Boolean = false, folder: PsiElement.() -> Expression?): WrapperExpression? {
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

