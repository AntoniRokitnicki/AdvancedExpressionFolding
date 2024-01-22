package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OptionalOf extends OptionalOfNullable {
    public OptionalOf(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull List<Expression> operands) {
        super(element, textRange, operands);
    }

    @Override
    protected @NotNull String suffixText() {
        return "!!";
    }

}
