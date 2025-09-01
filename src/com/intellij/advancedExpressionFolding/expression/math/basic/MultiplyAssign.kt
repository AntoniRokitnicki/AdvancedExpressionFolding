package com.intellij.advancedExpressionFolding.expression.math.basic;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Operation;
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class MultiplyAssign extends Operation implements ArithmeticExpression {
    public MultiplyAssign(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, "*=", 300, operands);
    }
}
