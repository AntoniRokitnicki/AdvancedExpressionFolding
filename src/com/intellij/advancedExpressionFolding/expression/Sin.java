package com.intellij.advancedExpressionFolding.expression;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Sin extends Function implements ArithmeticExpression {
    public Sin(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, "sin", operands);
    }
}
