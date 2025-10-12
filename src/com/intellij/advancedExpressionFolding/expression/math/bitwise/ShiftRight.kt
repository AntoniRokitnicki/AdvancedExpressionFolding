package com.intellij.advancedExpressionFolding.expression.math.bitwise;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Operation;
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class ShiftRight extends Operation implements ArithmeticExpression {
    public ShiftRight(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, ">>", 20, operands);
    }
}
