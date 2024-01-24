package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OptionalMapCall extends Operation {
    public OptionalMapCall(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull List<Expression> operands, boolean flatMap) {
        super(element, textRange, flatMap ? ".*" : ".", 300, operands);
    }

    @Override
    protected @NotNull String buildFolding(@NotNull String character) {
        return character;
    }

    @Override
    public boolean isCollapsedByDefault() {
        return true;
    }

    @Override
    public boolean supportsFoldRegions(@NotNull Document document,
                                       @Nullable Expression parent) {
        return true;
    }
}
