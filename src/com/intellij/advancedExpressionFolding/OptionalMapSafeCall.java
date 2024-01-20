package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OptionalMapSafeCall extends Operation {
    public OptionalMapSafeCall(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull List<Expression> operands) {
        super(element, textRange, "?.", 300, operands);
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

    @Override
    protected @NotNull String buildFolding(@NotNull String character) {
        return character;
    }
}
