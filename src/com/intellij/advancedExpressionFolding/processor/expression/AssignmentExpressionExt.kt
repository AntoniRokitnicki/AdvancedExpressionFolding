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
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

import java.util.Objects
import java.util.Arrays.asList

object AssignmentExpressionExt {
    @JvmStatic
    @Nullable
    fun getAssignmentExpression(element: PsiAssignmentExpression, @Nullable document: Document?): Expression? {
        val leftVariable = getVariableExpression(element.getLExpression())
        if (leftVariable != null && element.getRExpression() != null) {
            val leftExpression = BuildExpressionExt.getAnyExpression(element.getRExpression(), document)
            if (leftExpression is Operation) {
                if (leftExpression.getOperands().size >= 2 && leftExpression.getOperands()[0] == leftVariable) {
                    val firstOperand = first(leftExpression)
                    if (leftExpression is Add) {
                        return AddAssign(element, element.getTextRange(), asList(leftVariable, if (atLeastTwoOperands(leftExpression)) Add(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                    } else if (leftExpression is Subtract) {
                        return SubtractAssign(element, element.getTextRange(), asList(leftVariable, if (atLeastTwoOperands(leftExpression)) Add(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                    } else if (leftExpression is And) {
                        return AndAssign(element, element.getTextRange(), asList(leftVariable, if (atLeastTwoOperands(leftExpression)) And(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                    } else if (leftExpression is Or) {
                        return AndAssign(element, element.getTextRange(), asList(leftVariable, if (atLeastTwoOperands(leftExpression)) Or(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                    } else if (leftExpression is Xor) {
                        return AndAssign(element, element.getTextRange(), asList(leftVariable, if (atLeastTwoOperands(leftExpression)) Xor(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                    } else if (leftExpression is Multiply) {
                        return MultiplyAssign(element, element.getTextRange(), asList(leftVariable, if (atLeastTwoOperands(leftExpression)) Multiply(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                    } else if (leftExpression is Divide) {
                        return DivideAssign(element, element.getTextRange(), asList(leftVariable, if (atLeastTwoOperands(leftExpression)) Multiply(element, getTextRange(leftExpression), getOperands(leftExpression)) else firstOperand))
                    } else if (leftExpression is ShiftRight && twoOperands(leftExpression)) {
                        return ShiftRightAssign(element, element.getTextRange(), asList(leftVariable, firstOperand))
                    } else if (leftExpression is ShiftLeft && twoOperands(leftExpression)) {
                        return ShiftLeftAssign(element, element.getTextRange(), asList(leftVariable, firstOperand))
                    } else if (leftExpression is Remainder && twoOperands(leftExpression)) {
                        return RemainderAssign(element, element.getTextRange(), asList(leftVariable, firstOperand))
                    }
                }
            }
        }
        val expression = FieldShiftExt.createExpression(element, document)
        if (expression != null) {
            return expression
        }
        return null
    }

    @JvmStatic
    @Nullable
    fun getVariableExpression(element: PsiElement, copy: Boolean): Variable? {
        val reference = element.getReference()
        if (reference != null) {
            val e = reference.resolve()
            if (e is PsiVariable && e.getName() != null && Objects.equals(e.getName(), element.getText())) {
                val name = e.getName()
                if (name != null) {
                    return Variable(element, element.getTextRange(), null, name, copy)
                }
            }
        }
        return null
    }

    @JvmStatic
    @Nullable
    fun getVariableExpression(element: PsiElement): Variable? {
        return getVariableExpression(element, false)
    }

    private fun twoOperands(operation: Operation): Boolean {
        return operation.getOperands().size == 2
    }

    private fun atLeastTwoOperands(operation: Operation): Boolean {
        return operation.getOperands().size > 2
    }

    private fun first(operation: Operation): Expression {
        return operation.getOperands()[1]
    }

    @NotNull
    private fun getOperands(operation: Operation): List<Expression> {
        return operation.getOperands().subList(1, operation.getOperands().size)
    }

    @NotNull
    private fun getTextRange(operation: Operation): TextRange {
        return TextRange.create(first(operation).getTextRange().getStartOffset(), operation.getOperands()[operation.getOperands().size - 1].getTextRange().getEndOffset())
    }
}
