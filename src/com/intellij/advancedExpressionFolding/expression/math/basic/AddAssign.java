package com.intellij.advancedExpressionFolding.expression.math.basic;

import com.intellij.advancedExpressionFolding.expression.ArithmeticExpression;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Operation;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddAssign extends Operation implements ArithmeticExpression {
    public AddAssign(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull List<Expression> operands) {
        super(element, textRange, "+=", 300, operands);
    }
}
