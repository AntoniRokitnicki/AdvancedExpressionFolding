package com.intellij.advancedExpressionFolding.expression.operation.basic;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Operation;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Greater extends Operation {
    public Greater(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, ">", 18, operands);
    }
}
