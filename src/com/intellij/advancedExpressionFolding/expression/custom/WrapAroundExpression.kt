package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.FieldShiftExt2.hideExpr
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.nextWhiteSpace
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
            textBefore?.let {
                list += SimpleExpression(element, text = it, foldPrevWhiteSpace = foldPrevWhiteSpace)
            }
            textAfter?.let {
                list += SimpleExpression(element, element.nextWhiteSpace()?.hideExpr(), text = it)
            }
            return list.toTypedArray()
        }
    }

}
