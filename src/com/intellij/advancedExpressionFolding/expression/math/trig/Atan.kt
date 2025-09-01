package com.intellij.advancedExpressionFolding.expression.math.trig;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Function;
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Atan extends Function implements ArithmeticExpression {
    public Atan(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, "atan", operands);
    }
}
