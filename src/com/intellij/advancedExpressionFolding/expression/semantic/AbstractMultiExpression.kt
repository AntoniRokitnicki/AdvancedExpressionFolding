package com.intellij.advancedExpressionFolding.expression.semantic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.group
import com.intellij.advancedExpressionFolding.processor.prevWhiteSpace
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class AbstractMultiExpression(
    element: PsiElement,
    textRange: TextRange = element.textRange,
    private val children: List<Expression?>,
    private val text: String = "",
    private var group: FoldingGroup? = null,
    private val foldPrevWhiteSpace: Boolean = false,
) : Expression(element, textRange) {

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

        children.forEach { child ->
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

