package com.intellij.advancedExpressionFolding.expression.operation.basic

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral
import com.intellij.advancedExpressionFolding.expression.math.basic.Add
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class Append(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>,
    private val assign: Boolean
) : Operation(element, textRange, "+", 10, operands) {

    override fun isCollapsedByDefault(): Boolean {
        if (!super.isCollapsedByDefault()) {
            return false
        }
        for (operand in operands) {
            if (operand is Add && operand.operands.any { it !is StringLiteral }) {
                return false
            }
        }
        return true
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return operands.isNotEmpty() && super.supportsFoldRegions(document, parent)
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Append::class.java.name + if (operands.size == 1) HIGHLIGHTED_GROUP_POSTFIX else "")
        val descriptors = ArrayList<FoldingDescriptor>()
        val a = operands[0]
        if (element.textRange.startOffset < a.textRange.startOffset) {
            val range = TextRange.create(
                element.textRange.startOffset,
                a.textRange.startOffset - (if (a.isLeftOverflow()) 1 else 0)
            )
            if (!range.isEmpty) {
                descriptors.add(FoldingDescriptor(element.node, range, group, ""))
            }
        }
        if (a.supportsFoldRegions(document, this)) {
            if (a.isOverflow()) {
                descriptors.addAll(a.buildFoldRegions(a.element, document, this, group, "", ""))
            } else {
                descriptors.addAll(a.buildFoldRegions(a.element, document, this))
            }
        }
        if (operands.size > 1) {
            for (i in 0 until operands.size - 1) {
                val b = operands[i]
                val c = operands[i + 1]
                if (c.supportsFoldRegions(document, this)) {
                    if (c.isOverflow()) {
                        descriptors.addAll(c.buildFoldRegions(c.element, document, this, group, "", ""))
                    } else {
                        descriptors.addAll(c.buildFoldRegions(c.element, document, this))
                    }
                }
                descriptors.add(
                    FoldingDescriptor(
                        element.node,
                        TextRange.create(
                            b.textRange.endOffset + if (b.isRightOverflow()) 1 else 0,
                            c.textRange.startOffset - if (c.isLeftOverflow()) 1 else 0
                        ),
                        group,
                        if (i == 0 && assign) " += " else " + "
                    )
                )
            }
        }
        val d = operands[operands.size - 1]
        if (d.textRange.endOffset < element.textRange.endOffset) {
            val range = TextRange.create(
                d.textRange.endOffset + if (d.isRightOverflow()) 1 else 0,
                element.textRange.endOffset
            )
            if (!range.isEmpty) {
                descriptors.add(FoldingDescriptor(element.node, range, group, ""))
            }
        }
        return descriptors.toTypedArray()
    }

    override fun isHighlighted(): Boolean = operands.size == 1

    class Less(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
        Operation(element, textRange, "<", 18, operands)

    class LessEqual(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
        Operation(element, textRange, "≤", 18, operands)
}

