package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

open class WrapperExpression(element: PsiElement, textRange: TextRange = element.textRange, chain : List<Expression> = emptyList(), private val nested: Boolean = true) :
    AbstractMultiExpression(element, textRange, *chain.toTypedArray()) {


    override fun isNested() = nested

    override fun wrapElement(element: PsiElement, parentGroup: FoldingGroup): MutableList<FoldingDescriptor> =
        mutableListOf()
}
