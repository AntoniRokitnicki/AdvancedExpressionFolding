package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.prevWhiteSpace
import com.intellij.advancedExpressionFolding.extension.end
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class WrapAroundExpression(
    element: PsiElement,
    textRange: TextRange = element.textRange,
    vararg children: Expression?,
    group: FoldingGroup? = null,
    textBefore: String? = null,
    foldPrevWhiteSpace: Boolean = false,
    textAfter: String? = null,
    foldNextWhiteSpace: Boolean = false,
) :
AbstractMultiExpression(element,
    textRange,
    *modifyChildren(children, element, textBefore, foldPrevWhiteSpace, textAfter, foldNextWhiteSpace),
    group = group,
    foldPrevWhiteSpace = foldPrevWhiteSpace) {


    override fun wrapElement(element: PsiElement, parentGroup: FoldingGroup): MutableList<FoldingDescriptor> =
        mutableListOf()

    companion object {
        fun modifyChildren(
            children: Array<out Expression?>,
            element: PsiElement,
            textBefore: String?,
            foldPrevWhiteSpace: Boolean,
            textAfter: String?,
            foldNextWhiteSpace: Boolean,
        ): Array<Expression?> {
            val list = mutableListOf<Expression?>()
            list.addAll(children)

            if (textBefore != null) {
                element.prevWhiteSpace()?.let {
                    if (foldPrevWhiteSpace) {
                        list += SimpleExpression(it, textRange = it.textRange, text = textBefore)
                    } else {
                        val text = it.text
                        val c = text.substring(text.length - 1)
                        list += SimpleExpression(it, textRange = TextRange(it.end()-1, it.end()), text = "$c$textBefore")
                    }
                }
                //TODO: else
            }

            textAfter?.let {
                TODO()
            }
            if (foldNextWhiteSpace) {
                TODO()
            }

            return list.toTypedArray()
        }
    }

}
