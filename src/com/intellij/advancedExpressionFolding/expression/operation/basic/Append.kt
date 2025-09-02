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
import java.util.ArrayList
import java.util.Collections
import java.util.List

/**
 * TODO: sb.append(interpolatedString).append(x) - merge x into interpolatedString
 * TODO: merge multiple sb.append() into a single append(interpolatedString)
 */
class Append(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>,
    assign: Boolean
) : Operation(element, textRange, "+", 10, operands) {
    private var assign: Boolean = assign

    override fun isCollapsedByDefault(): Boolean {
        if (!super.isCollapsedByDefault()) {
            return false
        }
        for (operand in operands) {
            if (operand is Add && operand.getOperands().stream().anyMatch { o -> o !is StringLiteral }) {
                return false
            }
        }
        return true
    }

    override fun supportsFoldRegions(document: Document, parent: Expression?): Boolean {
        return operands.size > 0 && super.supportsFoldRegions(document, parent)
    }

    override fun buildFoldRegions(element: PsiElement, document: Document, parent: Expression?): Array<FoldingDescriptor> {
        val group = FoldingGroup.newGroup(Append::class.java.getName() + if (operands.size == 1) HIGHLIGHTED_GROUP_POSTFIX else "")
        val descriptors = ArrayList<FoldingDescriptor>()
        val a = operands[0]
        if (element.getTextRange().getStartOffset() < a.getTextRange().getStartOffset()) {
            val range = TextRange.create(
                element.getTextRange().getStartOffset(),
                a.getTextRange().getStartOffset() - (if (a.isLeftOverflow()) 1 else 0)
            )
            if (!range.isEmpty) {
                descriptors.add(FoldingDescriptor(element.getNode(), range, group, ""))
            }
        }
        if (a.supportsFoldRegions(document, this)) {
            if (a.isOverflow()) {
                Collections.addAll(descriptors, *a.buildFoldRegions(a.getElement(), document, this, group, "", ""))
            } else {
                Collections.addAll(descriptors, *a.buildFoldRegions(a.getElement(), document, this))
            }
        }
        if (operands.size > 1) {
            var i = 0
            while (i < operands.size - 1) {
                val b = operands[i]
                val c = operands[i + 1]
                if (c.supportsFoldRegions(document, this)) {
                    if (c.isOverflow()) {
                        Collections.addAll(descriptors, *c.buildFoldRegions(c.getElement(), document, this, group, "", ""))
                    } else {
                        Collections.addAll(descriptors, *c.buildFoldRegions(c.getElement(), document, this))
                    }
                }
                descriptors.add(
                    FoldingDescriptor(
                        element.getNode(),
                        TextRange.create(
                            b.getTextRange().getEndOffset() + (if (b.isRightOverflow()) 1 else 0),
                            c.getTextRange().getStartOffset() - (if (c.isLeftOverflow()) 1 else 0)
                        ),
                        group,
                        if (i == 0 && assign) " += " else " + "
                    )
                )
                i++
            }
        }
        val d = operands[operands.size - 1]
        if (d.getTextRange().getEndOffset() < element.getTextRange().getEndOffset()) {
            val range = TextRange.create(
                d.getTextRange().getEndOffset() + (if (d.isRightOverflow()) 1 else 0),
                element.getTextRange().getEndOffset()
            )
            if (!range.isEmpty) {
                descriptors.add(FoldingDescriptor(element.getNode(), range, group, ""))
            }
        }
        return descriptors.toTypedArray()
    }

    override fun isHighlighted(): Boolean {
        return operands.size == 1
    }

    class Less(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
        Operation(element, textRange, "<", 18, operands)

    class LessEqual(element: PsiElement, textRange: TextRange, operands: List<Expression>) :
        Operation(element, textRange, "≤", 18, operands)
}
