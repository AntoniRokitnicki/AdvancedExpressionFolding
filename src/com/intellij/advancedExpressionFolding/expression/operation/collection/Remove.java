package com.intellij.advancedExpressionFolding.expression.operation.collection;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Operation;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import java.util.List;

public class Remove extends Operation {
    public Remove(PsiElement element, TextRange textRange, List<Expression> operands) {
        super(element, textRange, "-=", 10, operands);
    }
}
