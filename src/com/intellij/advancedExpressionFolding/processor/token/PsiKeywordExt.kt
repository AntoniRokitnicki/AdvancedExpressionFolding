package com.intellij.advancedExpressionFolding.processor.token

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.*
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension
import com.intellij.advancedExpressionFolding.processor.util.Consts.Emoji
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

object PsiKeywordExt : BaseExtension() {

    fun createExpression(keyword: PsiKeyword): Expression? =
        finalRemoval.on(keyword)?.finalRemoval() ?: finalEmoji.on(keyword)?.finalEmoji()

    private fun PsiKeyword.finalEmoji(): Expression? = foldFinalsExceptFields { expr(Emoji.FINAL_LOCK.toString()) }

    private fun PsiKeyword.finalRemoval(): Expression? = foldFinalsExceptFields(foldSpaces = true) { exprHide() }

    private inline fun PsiKeyword.foldFinalsExceptFields(foldSpaces: Boolean = false, folder: PsiElement.() -> Expression?): Expression? {
        if (text == PsiKeyword.FINAL) {
            val list = exprList()
            var ignore = false

            val modifierList = parent as? PsiModifierList
            val modifierChildren = modifierList?.children
            val keywordIndex = modifierChildren?.indexOf(this) ?: -1
            val hasMeaningfulModifierBefore = keywordIndex > 0 && modifierChildren
                ?.take(keywordIndex)
                ?.any { it !is PsiWhiteSpace }
                ?: false
            val hasMeaningfulModifierAfter = keywordIndex >= 0 && modifierChildren
                ?.drop(keywordIndex + 1)
                ?.any { it !is PsiWhiteSpace }
                ?: false

            val combinedTextRange = if (foldSpaces) {
                val fileText = containingFile?.text
                if (fileText != null) {
                    var startOffset = textRange.startOffset
                    var endOffset = textRange.endOffset

                    if (hasMeaningfulModifierBefore) {
                        var index = startOffset - 1
                        while (index >= 0 && fileText[index].isSpaceWithoutLineBreak()) {
                            index--
                        }
                        val newStart = index + 1
                        if (newStart < startOffset) {
                            startOffset = newStart
                        }

                        if (!hasMeaningfulModifierAfter) {
                            var rightIndex = endOffset
                            while (rightIndex < fileText.length && fileText[rightIndex].isSpaceWithoutLineBreak()) {
                                rightIndex++
                            }
                            if (rightIndex < fileText.length && (fileText[rightIndex] == '\r' || fileText[rightIndex] == '\n')) {
                                val withLineBreak = fileText.consumeLineBreak(rightIndex)
                                if (withLineBreak > endOffset) {
                                    endOffset = withLineBreak
                                }
                            }
                        }
                    } else {
                        var index = endOffset
                        while (index < fileText.length && fileText[index].isSpaceWithoutLineBreak()) {
                            index++
                        }
                        index = fileText.consumeLineBreak(index)
                        if (index > endOffset) {
                            endOffset = index
                        }
                    }

                    if (startOffset != textRange.startOffset || endOffset != textRange.endOffset) {
                        TextRange(startOffset, endOffset)
                    } else {
                        null
                    }
                } else {
                    null
                }
            } else {
                null
            }

            when (val realParent = this.parent.parent) {
                //TODO: this depends where final is in the modifier list, adjust
                is PsiMethod -> {}

                is PsiLocalVariable -> {}

                is PsiParameter -> {}

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
                val expression = if (foldSpaces && combinedTextRange != null) {
                    this.exprHide(textRange = combinedTextRange)
                } else {
                    this.folder()
                }
                list += expression
            }
            return list.exprWrap(this)
        }
        return null
    }


}

private fun CharSequence.consumeLineBreak(start: Int): Int {
    var index = start
    if (index < length && this[index] == '\r') {
        index++
        if (index < length && this[index] == '\n') {
            index++
        }
    } else if (index < length && this[index] == '\n') {
        index++
    }
    return index
}

private fun Char.isSpaceWithoutLineBreak(): Boolean = when (this) {
    ' ', '\t' -> true
    else -> false
}

