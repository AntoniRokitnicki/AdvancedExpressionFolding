package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.advancedExpressionFolding.extension.group
import com.intellij.advancedExpressionFolding.extension.prevWhiteSpace
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class AbstractMultiExpression(
    element: PsiElement,
    textRange: TextRange = element.textRange,
    /**
     * creation only, [childrenList] is used in [buildFoldRegions] and [plus]
     */
    vararg children: Expression?,
    private val text: String = "",
    private var group: FoldingGroup? = null,
    private val foldPrevWhiteSpace: Boolean = false,
) : Expression(element, textRange) {

    private val childrenList: MutableList<Expression?> = children.toMutableList()

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val parentGroup = group ?: createGroup()

        val list = wrapElement(element, parentGroup)
        if (foldPrevWhiteSpace) {
            element.prevWhiteSpace()?.let {
                list += FoldingDescriptor(
                    it.node,
                    it.textRange,
                    parentGroup,
                    "",
                )
            }
        }

        childrenList.forEach { child ->
            if (child != null) {
                assignGroupOnNotFound(child, parentGroup)
                list += child.buildFoldRegions(child.element, document, this)
            }
        }
        return list.toTypedArray()
    }

    private fun assignGroupOnNotFound(
        child: Expression?,
        parentGroup: FoldingGroup
    ) {
        child.asInstance<AbstractMultiExpression>()
            ?.takeIf {
                it.group == null
            }
            ?.let {
                it.group = parentGroup
            }
    }

    open fun wrapElement(
        element: PsiElement,
        parentGroup: FoldingGroup
    ): MutableList<FoldingDescriptor> {
        return mutableListOf(
            FoldingDescriptor(
                element.node,
                textRange,
                parentGroup,
                text,
            )
        )
    }

    open fun createGroup(): FoldingGroup = this::class.group()

}

