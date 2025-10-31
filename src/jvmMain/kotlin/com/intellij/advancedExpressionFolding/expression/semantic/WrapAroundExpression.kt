package com.intellij.advancedExpressionFolding.expression.semantic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.end
import com.intellij.advancedExpressionFolding.processor.nextWhiteSpace
import com.intellij.advancedExpressionFolding.processor.prevWhiteSpace
import com.intellij.advancedExpressionFolding.processor.start
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class WrapAroundExpression(
    element: PsiElement,
    textRange: TextRange = element.textRange,
    children: List<Expression?> = emptyList<Expression?>(), //TODO: not needed
    group: FoldingGroup? = null,
    textBefore: String? = null,
    foldPrevWhiteSpace: Boolean = false,
    //TODO: not needed
    textAfter: String? = null,
    foldNextWhiteSpace: Boolean = false,
) :
AbstractMultiExpression(element,
    textRange,
    modifyChildren(children, element, textBefore, foldPrevWhiteSpace, textAfter, foldNextWhiteSpace, group),
    group = group,
    foldPrevWhiteSpace = foldPrevWhiteSpace) {


    override fun wrapElement(element: PsiElement, parentGroup: FoldingGroup): MutableList<FoldingDescriptor> =
        mutableListOf()

    companion object {
        fun modifyChildren(
            children: List<Expression?>,
            element: PsiElement,
            textBefore: String?,
            foldPrevWhiteSpace: Boolean,
            textAfter: String?,
            foldNextWhiteSpace: Boolean,
            group: FoldingGroup?,
        ): List<Expression?> {
            val list = mutableListOf<Expression?>()
            list.addAll(children)

            if (textBefore != null) {
                element.prevWhiteSpace()?.let {
                    if (foldPrevWhiteSpace) {
                        list += SimpleExpression(it, textRange = it.textRange, text = textBefore, group = group)
                    } else {
                        val text = it.text
                        val c = text.substring(text.length - 1)
                        list += SimpleExpression(it, textRange = TextRange(it.end()-1, it.end()), text = "$c$textBefore", group = group)
                    }
                }
                //TODO: else
            }

            element.nextWhiteSpace()?.let { nextWhiteSpace ->
                when {
                    textAfter != null && foldNextWhiteSpace -> {
                        list += SimpleExpression(nextWhiteSpace, textRange = nextWhiteSpace.textRange, text = textAfter, group = group)
                    }

                    textAfter != null -> {
                        val firstChar = nextWhiteSpace.text.firstOrNull()
                        if (firstChar != null) {
                            list += SimpleExpression(
                                nextWhiteSpace,
                                textRange = TextRange(nextWhiteSpace.start(), nextWhiteSpace.start() + 1),
                                text = "$textAfter$firstChar",
                                group = group,
                            )
                        }
                    }

                    foldNextWhiteSpace -> {
                        list += SimpleExpression(nextWhiteSpace, textRange = nextWhiteSpace.textRange, text = "", group = group)
                    }
                }
            }

            return list
        }
    }

}
