package com.intellij.advancedExpressionFolding.expression.optional;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Operation;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FieldShiftMethod extends Operation {
    private final String text;

    public FieldShiftMethod(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull List<Expression> operands, String text) {
        super(element, textRange, "", 300, operands);
        this.text = text;
    }

    @Override
    protected @NotNull String buildFolding(@NotNull String character) {
        return character;
    }

    @NotNull
    @Override
    protected String suffixText() {
        return text;
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
