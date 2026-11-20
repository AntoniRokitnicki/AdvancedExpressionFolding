package com.intellij.advancedExpressionFolding.expression.semantic.kotlin

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.group
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

/**
 * Folds the given gaps (keywords, parentheses, `throw new X(`) and lets the condition and the message
 * fold on their own.
 *
 * A child that borrows the character next to it (an interpolated message turns `)` into `")`) gets the
 * text of the gap that character came from as its overflow placeholder.
 */
class PreconditionExpression(
    element: PsiElement,
    private val gaps: List<Pair<TextRange, String>>,
    private val children: List<Child>,
) : Expression(element, element.textRange) {

    class Child(
        val expression: Expression,
        val leftOverflowPlaceholder: String? = null,
        val rightOverflowPlaceholder: String? = null,
    )

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean = true

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = PreconditionExpression::class.group()
        val descriptors = gaps.mapTo(mutableListOf()) { (range, text) ->
            FoldingDescriptor(element.node, range, group, text)
        }
        children.filter { it.expression.supportsFoldRegions(document, this) }.forEach { child ->
            val expression = child.expression
            descriptors += if (child.leftOverflowPlaceholder != null || child.rightOverflowPlaceholder != null) {
                expression.buildFoldRegions(
                    expression.element,
                    document,
                    this,
                    group,
                    child.leftOverflowPlaceholder,
                    child.rightOverflowPlaceholder
                )
            } else {
                expression.buildFoldRegions(expression.element, document, this)
            }
        }
        return descriptors.toTypedArray()
    }
}
