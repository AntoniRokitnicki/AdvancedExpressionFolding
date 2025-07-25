package com.intellij.advancedExpressionFolding.expression.math.bitwise;

import com.intellij.advancedExpressionFolding.expression.ArithmeticExpression;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Operation;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Xor extends Operation implements ArithmeticExpression {
    public Xor(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, "^", 50, operands);
    }
}
