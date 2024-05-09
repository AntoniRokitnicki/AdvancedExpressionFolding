package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.PsiClassExt.prevWhiteSpace
import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.advancedExpressionFolding.extension.group
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

abstract class AbstractMultiExpression(
    element: PsiElement,
    textRange: TextRange = element.textRange,
    private vararg val children: Expression?,
    private val text: String = "",
    private var group: FoldingGroup? = null,
    private val foldPrevWhiteSpace: Boolean = false
) : Expression(element, textRange) {

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val parentGroup = group ?: createGroup()

        val list = mutableListOf(
            FoldingDescriptor(
                element.node,
                textRange,
                parentGroup,
                text,
            )
        )
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
            if (child == null) {
                return@forEach
            }
            child.asInstance<AbstractMultiExpression>()
                ?.takeIf {
                    it.group == null
                }
                ?.let {
                    it.group = parentGroup
                }
            if (child.supportsFoldRegions(document, parent)) {
                list += child.buildFoldRegions(child.element, document, this)
            }
        }
        return list.toTypedArray()
    }

    open fun createGroup(): FoldingGroup = this::class.group()

}

