package com.intellij.advancedExpressionFolding.expression.operation.logical

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

class LogicalBinaryExpression(
    element: PsiElement,
    textRange: TextRange,
    operands: List<Expression>,
    val operator: LogicalOperator,
    private val uppercase: Boolean,
    private val visualParentheses: Boolean,
) : Operation(element, textRange, operator.display(uppercase), operator.priority, operands) {

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        parent: Expression?
    ): Array<FoldingDescriptor> {
        if (operands.isEmpty()) {
            return Expression.EMPTY_ARRAY
        }

        val group = FoldingGroup.newGroup(javaClass.name)
        val descriptors = mutableListOf<FoldingDescriptor>()
        val wrapped = BooleanArray(operands.size)

        var offset = textRange.startOffset
        val firstOperand = operands.first()
        if (firstOperand.textRange.startOffset > offset) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(offset, firstOperand.textRange.startOffset),
                group,
                "",
            )
        }
        if (needsVisualParentheses(firstOperand)) {
            descriptors += wrapOperandDescriptor(firstOperand, group)
            wrapped[0] = true
        }

        offset = firstOperand.textRange.endOffset
        for (i in 1 until operands.size) {
            val current = operands[i]
            val range = TextRange.create(
                changeOperandsStartOffset(offset),
                changeOperandsEndOffset(current.textRange.startOffset)
            )
            val placeholder = buildFolding(getCharacter())
            if (document.getText(range) != placeholder) {
                descriptors += FoldingDescriptor(element.node, range, group, placeholder)
            }
            if (needsVisualParentheses(current)) {
                descriptors += wrapOperandDescriptor(current, group)
                wrapped[i] = true
            }
            offset = current.textRange.endOffset
        }

        if (offset < textRange.endOffset) {
            descriptors += FoldingDescriptor(
                element.node,
                TextRange.create(offset, textRange.endOffset),
                group,
                suffixText()
            )
        }

        operands.forEachIndexed { index, operand ->
            if (!wrapped[index] && operand.supportsFoldRegions(document, this)) {
                descriptors.addAll(operand.buildFoldRegions(operand.element, document, this))
            }
        }

        return descriptors.toTypedArray()
    }

    private fun needsVisualParentheses(operand: Expression): Boolean {
        return visualParentheses && operand is LogicalBinaryExpression
    }

    private fun wrapOperandDescriptor(operand: Expression, group: FoldingGroup): FoldingDescriptor {
        val placeholder = operand.renderWithParentheses(uppercase, visualParentheses)
        return FoldingDescriptor(element.node, operand.textRange, group, placeholder)
    }

    override fun buildFolding(character: String): String {
        return " ${operator.display(uppercase)} "
    }
}

enum class LogicalOperator(val priority: Int, private val text: String) {
    OR(200, "OR"),
    AND(300, "AND");

    fun display(uppercase: Boolean): String = if (uppercase) text else text.lowercase()
}

private fun Expression.renderWithParentheses(uppercase: Boolean, visualParentheses: Boolean): String {
    val content = renderLogicalText(uppercase, visualParentheses)
    return "($content)"
}

private fun Expression.renderLogicalText(uppercase: Boolean, visualParentheses: Boolean): String {
    return when (this) {
        is LogicalBinaryExpression -> renderBinaryText(uppercase, visualParentheses)
        is LogicalNotExpression -> renderNotText(uppercase, visualParentheses)
        else -> element.text
    }
}

private fun LogicalBinaryExpression.renderBinaryText(uppercase: Boolean, visualParentheses: Boolean): String {
    val builder = StringBuilder()
    operands.forEachIndexed { index, operand ->
        if (index > 0) {
            builder.append(' ')
            builder.append(operator.display(uppercase))
            builder.append(' ')
        }
        builder.append(operand.renderLogicalText(uppercase, visualParentheses))
    }
    return builder.toString()
}

private fun LogicalNotExpression.renderNotText(uppercase: Boolean, visualParentheses: Boolean): String {
    val keyword = if (uppercase) "NOT" else "not"
    val operandText = operandExpression.renderLogicalText(uppercase, visualParentheses)
    return if (visualParentheses && operandExpression is LogicalBinaryExpression) {
        "$keyword ($operandText)"
    } else {
        "$keyword $operandText"
    }
}
