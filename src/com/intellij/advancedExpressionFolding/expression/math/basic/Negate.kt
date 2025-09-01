package com.intellij.advancedExpressionFolding.expression.math.basic;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Function;
import com.intellij.advancedExpressionFolding.expression.math.ArithmeticExpression;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Negate extends Function implements ArithmeticExpression {
    public Negate(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, "negate", operands);
    }

    @Override
    public boolean supportsFoldRegions(@NotNull Document document,
                                       @Nullable Expression parent) {
        return false;
    }
}
