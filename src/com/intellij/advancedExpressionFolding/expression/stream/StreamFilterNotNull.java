package com.intellij.advancedExpressionFolding.expression.stream;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Operation;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StreamFilterNotNull extends Operation {
    public StreamFilterNotNull(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull List<Expression> operands) {
        super(element, textRange, ".filterNotNull()", 300, operands);
    }

    @Override
    protected @NotNull String buildFolding(@NotNull String character) {
        return character;
    }

    @Override
    protected int changeOperandsEndOffset(int startOffset) {
        return startOffset + "Objects::nonNull".length();
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
