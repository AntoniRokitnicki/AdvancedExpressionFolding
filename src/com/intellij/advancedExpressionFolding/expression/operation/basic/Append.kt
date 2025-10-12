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

/**
 * TODO: sb.append(interpolatedString).append(x) - merge x into interpolatedString
 * TODO: merge multiple sb.append() into a single append(interpolatedString)
 */
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

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Append::class.java.name + if (operands.size == 1) HIGHLIGHTED_GROUP_POSTFIX else "")
        val descriptors = mutableListOf<FoldingDescriptor>()
        val firstOperand = operands.first()
        if (element.textRange.startOffset < firstOperand.textRange.startOffset) {
            val range = TextRange.create(
                element.textRange.startOffset,
                firstOperand.textRange.startOffset - if (firstOperand.isLeftOverflow()) 1 else 0
            )
            if (!range.isEmpty) {
                descriptors += FoldingDescriptor(element.node, range, group, "")
            }
        }
        if (firstOperand.supportsFoldRegions(document, this)) {
            val regions = if (firstOperand.isOverflow()) {
                firstOperand.buildFoldRegions(firstOperand.element, document, this, group, "", "")
            } else {
                firstOperand.buildFoldRegions(firstOperand.element, document, this)
            }
            descriptors += regions.toList()
        }
        if (operands.size > 1) {
            for (i in 0 until operands.size - 1) {
                val current = operands[i]
                val next = operands[i + 1]
                if (next.supportsFoldRegions(document, this)) {
                    val regions = if (next.isOverflow()) {
                        next.buildFoldRegions(next.element, document, this, group, "", "")
                    } else {
                        next.buildFoldRegions(next.element, document, this)
                    }
                    descriptors += regions.toList()
                }
                descriptors += FoldingDescriptor(
                    element.node,
                    TextRange.create(
                        current.textRange.endOffset + if (current.isRightOverflow()) 1 else 0,
                        next.textRange.startOffset - if (next.isLeftOverflow()) 1 else 0
                    ),
                    group,
                    if (i == 0 && assign) " += " else " + "
                )
            }
        }
        val lastOperand = operands.last()
        if (lastOperand.textRange.endOffset < element.textRange.endOffset) {
            val range = TextRange.create(
                lastOperand.textRange.endOffset + if (lastOperand.isRightOverflow()) 1 else 0,
                element.textRange.endOffset
            )
            if (!range.isEmpty) {
                descriptors += FoldingDescriptor(element.node, range, group, "")
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
