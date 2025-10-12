package com.intellij.advancedExpressionFolding.expression.math.basic;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Function;
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Max extends Function implements ArithmeticExpression {
    public Max(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, "max", operands);
    }
}
