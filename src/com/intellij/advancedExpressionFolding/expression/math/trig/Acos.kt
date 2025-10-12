package com.intellij.advancedExpressionFolding.expression.math.trig;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Function;
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Acos extends Function implements ArithmeticExpression {
    public Acos(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull List<Expression> operands) {
        super(element, textRange, "acos", operands);
    }
}
