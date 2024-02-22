package com.intellij.advancedExpressionFolding.expression;

import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IGetter extends INameable {
    @NotNull
    String getName();

    @NotNull
    TextRange getGetterTextRange();

    void makeFieldShift();

    Expression getObject();

    FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, @Nullable Expression parent);
}
