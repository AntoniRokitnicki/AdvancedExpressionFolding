package com.intellij.advancedExpressionFolding.expression;

import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;

public interface IGetter {
    @NotNull
    String getName();

    @NotNull
    TextRange getGetterTextRange();

    void makeFieldShift();
}
