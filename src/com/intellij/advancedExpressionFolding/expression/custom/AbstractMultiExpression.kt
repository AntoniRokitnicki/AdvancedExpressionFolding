package com.intellij.advancedExpressionFolding.expression.custom

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.asInstance
import com.intellij.advancedExpressionFolding.extension.distinctNot
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

        val notDistinct = childrenList.distinctNot()
        val combined = notDistinct.zipWithNext { a, b ->
            if (a is FieldAnnotationExpression && b is FieldAnnotationExpression) {
                val combinedCustomClassAnnotations = (a.customClassAnnotations + b.customClassAnnotations).distinct()
                val combinedElementsToFold = (a.elementsToFold + b.elementsToFold).distinct()
                val combinedFieldAnnotationExpression = FieldAnnotationExpression(
                    element = a.element,
                    customClassAnnotations = combinedCustomClassAnnotations,
                    elementsToFold = combinedElementsToFold
                )
                combinedFieldAnnotationExpression
            } else {
                println("WARNING!: folding on same element")
                null
            }
        }.filterNotNull()

        if (combined.isNotEmpty()) {
            childrenList.removeAll(notDistinct)
            childrenList.addAll(combined)
        }


        childrenList.forEach { child ->
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

    fun addChild(other: Expression?) {
        childrenList.add(other)
    }

}

