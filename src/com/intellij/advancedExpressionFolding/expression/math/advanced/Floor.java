package com.intellij.advancedExpressionFolding.expression.math.advanced;

import com.intellij.advancedExpressionFolding.expression.ArithmeticExpression;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Function;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Floor extends Function implements ArithmeticExpression {
    public Floor(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, "floor", operands);
    }
}
