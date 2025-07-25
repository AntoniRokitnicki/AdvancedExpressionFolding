package com.intellij.advancedExpressionFolding.expression.math.advanced;

import com.intellij.advancedExpressionFolding.expression.ArithmeticExpression;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Function;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Sqrt extends Function implements ArithmeticExpression {
    public Sqrt(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, "sqrt", operands);
    }
}
