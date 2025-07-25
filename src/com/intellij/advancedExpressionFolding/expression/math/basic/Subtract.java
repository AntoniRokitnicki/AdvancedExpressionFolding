package com.intellij.advancedExpressionFolding.expression.math.basic;

import com.intellij.advancedExpressionFolding.expression.ArithmeticExpression;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Operation;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Subtract extends Operation implements ArithmeticExpression {
    public Subtract(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, "-", 10, operands);
    }
}
