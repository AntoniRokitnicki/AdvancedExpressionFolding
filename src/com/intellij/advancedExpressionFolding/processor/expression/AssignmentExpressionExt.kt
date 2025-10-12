package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.advancedExpressionFolding.expression.math.basic.Add
import com.intellij.advancedExpressionFolding.expression.math.basic.AddAssign
import com.intellij.advancedExpressionFolding.expression.math.basic.Divide
import com.intellij.advancedExpressionFolding.expression.math.basic.DivideAssign
import com.intellij.advancedExpressionFolding.expression.math.basic.Multiply
import com.intellij.advancedExpressionFolding.expression.math.basic.MultiplyAssign
import com.intellij.advancedExpressionFolding.expression.math.basic.Subtract
import com.intellij.advancedExpressionFolding.expression.math.basic.SubtractAssign
import com.intellij.advancedExpressionFolding.expression.math.bitwise.And
import com.intellij.advancedExpressionFolding.expression.math.bitwise.AndAssign
import com.intellij.advancedExpressionFolding.expression.math.bitwise.Or
import com.intellij.advancedExpressionFolding.expression.math.bitwise.ShiftLeft
import com.intellij.advancedExpressionFolding.expression.math.bitwise.ShiftLeftAssign
import com.intellij.advancedExpressionFolding.expression.math.bitwise.ShiftRight
import com.intellij.advancedExpressionFolding.expression.math.bitwise.ShiftRightAssign
import com.intellij.advancedExpressionFolding.expression.math.bitwise.Xor
import com.intellij.advancedExpressionFolding.expression.math.bitwise.Remainder
import com.intellij.advancedExpressionFolding.expression.math.bitwise.RemainderAssign
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.language.FieldShiftExt
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiAssignmentExpression
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiVariable
import java.util.Objects

object AssignmentExpressionExt {

    fun getAssignmentExpression(element: PsiAssignmentExpression, document: Document?): Expression? {
        val leftVariable = getVariableExpression(element.lExpression)
        val rightExpression = element.rExpression ?: return FieldShiftExt.createExpression(element, document)
        if (leftVariable != null) {
            val leftExpression = BuildExpressionExt.getAnyExpression(rightExpression, document)
            if (leftExpression is Operation) {
                val operands = leftExpression.operands
                if (operands.size >= 2 && operands[0] == leftVariable) {
                    val firstOperand = first(leftExpression)
                    return when (leftExpression) {
                        is Add -> AddAssign(
                            element,
                            element.textRange,
                            listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Add(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand)
                        )
                        is Subtract -> SubtractAssign(
                            element,
                            element.textRange,
                            listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Add(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand)
                        )
                        is And -> AndAssign(
                            element,
                            element.textRange,
                            listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) And(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand)
                        )
                        is Or -> AndAssign(
                            element,
                            element.textRange,
                            listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Or(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand)
                        )
                        is Xor -> AndAssign(
                            element,
                            element.textRange,
                            listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Xor(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand)
                        )
                        is Multiply -> MultiplyAssign(
                            element,
                            element.textRange,
                            listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Multiply(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand)
                        )
                        is Divide -> DivideAssign(
                            element,
                            element.textRange,
                            listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Multiply(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand)
                        )
                        is ShiftRight -> if (twoOperands(leftExpression)) {
                            ShiftRightAssign(element, element.textRange, listOf(leftVariable, firstOperand))
                        } else {
                            null
                        }
                        is ShiftLeft -> if (twoOperands(leftExpression)) {
                            ShiftLeftAssign(element, element.textRange, listOf(leftVariable, firstOperand))
                        } else {
                            null
                        }
                        is Remainder -> if (twoOperands(leftExpression)) {
                            RemainderAssign(element, element.textRange, listOf(leftVariable, firstOperand))
                        } else {
                            null
                        }
                        else -> null
                    }
                }
            }
        }
        FieldShiftExt.createExpression(element, document)?.let { return it }
        return null
    }

    fun getVariableExpression(element: PsiElement, copy: Boolean = false): Variable? {
        val reference: PsiReference? = element.reference
        val resolved = reference?.resolve()
        if (resolved is PsiVariable && resolved.name != null && Objects.equals(resolved.name, element.text)) {
            val name = resolved.name ?: return null
            return Variable(element, element.textRange, null, name, copy)
        }
        return null
    }

    private fun twoOperands(operation: Operation): Boolean = operation.operands.size == 2

    private fun atLeastTwoOperands(operation: Operation): Boolean = operation.operands.size > 2

    private fun first(operation: Operation): Expression = operation.operands[1]

    private fun getOperands(operation: Operation): List<Expression> = operation.operands.subList(1, operation.operands.size)

    private fun getTextRange(operation: Operation): TextRange {
        val operands = operation.operands
        return TextRange.create(first(operation).textRange.startOffset, operands.last().textRange.endOffset)
    }
}
