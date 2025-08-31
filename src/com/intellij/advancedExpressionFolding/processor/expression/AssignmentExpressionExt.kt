package com.intellij.advancedExpressionFolding.processor.expression

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.Operation
import com.intellij.advancedExpressionFolding.expression.math.basic.*
import com.intellij.advancedExpressionFolding.expression.math.bitwise.*
import com.intellij.advancedExpressionFolding.expression.operation.basic.Variable
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.processor.language.FieldShiftExt
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiAssignmentExpression
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiVariable
import java.util.*

object AssignmentExpressionExt {
    fun getAssignmentExpression(element: PsiAssignmentExpression, document: Document?): Expression? {
        val leftVariable = getVariableExpression(element.lExpression)
        if (leftVariable != null && element.rExpression != null) {
            val leftExpression = BuildExpressionExt.getAnyExpression(element.rExpression!!, document)
            if (leftExpression is Operation) {
                if (leftExpression.operands.size >= 2 && leftExpression.operands[0] == leftVariable) {
                    val firstOperand = first(leftExpression)
                    return when (leftExpression) {
                        is Add -> AddAssign(element, element.textRange, listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Add(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                        is Subtract -> SubtractAssign(element, element.textRange, listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Add(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                        is And -> AndAssign(element, element.textRange, listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) And(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                        is Or -> AndAssign(element, element.textRange, listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Or(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                        is Xor -> AndAssign(element, element.textRange, listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Xor(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                        is Multiply -> MultiplyAssign(element, element.textRange, listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Multiply(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                        is Divide -> DivideAssign(element, element.textRange, listOf(leftVariable, if (atLeastTwoOperands(leftExpression)) Multiply(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                        is ShiftRight -> if (twoOperands(leftExpression)) ShiftRightAssign(element, element.textRange, listOf(leftVariable, firstOperand)) else null
                        is ShiftLeft -> if (twoOperands(leftExpression)) ShiftLeftAssign(element, element.textRange, listOf(leftVariable, firstOperand)) else null
                        is Remainder -> if (twoOperands(leftExpression)) RemainderAssign(element, element.textRange, listOf(leftVariable, firstOperand)) else null
                        else -> null
                    }
                }
            }
        }
        val expression = FieldShiftExt.createExpression(element, document)
        return expression
    }

    fun getVariableExpression(element: PsiElement, copy: Boolean): Variable? {
        val reference = element.reference
        if (reference != null) {
            val e = reference.resolve()
            if (e is PsiVariable && e.name != null && Objects.equals(e.name, element.text)) {
                val name = e.name
                if (name != null) {
                    return Variable(element, element.textRange, null, name, copy)
                }
            }
        }
        return null
    }

    fun getVariableExpression(element: PsiElement): Variable? {
        return getVariableExpression(element, false)
    }

    private fun twoOperands(operation: Operation): Boolean {
        return operation.operands.size == 2
    }

    private fun atLeastTwoOperands(operation: Operation): Boolean {
        return operation.operands.size > 2
    }

    private fun first(operation: Operation): Expression {
        return operation.operands[1]
    }

    private fun getOperands(operation: Operation): List<Expression> {
        return operation.operands.subList(1, operation.operands.size)
    }

    private fun getTextRange(operation: Operation): TextRange {
        return TextRange.create(
            first(operation).textRange.startOffset,
            operation.operands[operation.operands.size - 1].textRange.endOffset
        )
    }
}

