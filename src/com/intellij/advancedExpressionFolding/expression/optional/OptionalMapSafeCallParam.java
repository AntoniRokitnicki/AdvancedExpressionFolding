package com.intellij.advancedExpressionFolding.expression.optional;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class OptionalMapSafeCallParam extends Expression {
    private @NotNull String string;

    public OptionalMapSafeCallParam(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull String string) {
        super(element, textRange);
        this.string = string;
    }

    @NotNull
    public String getString() {
        return string;
    }

    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, @Nullable Expression parent) {
        FoldingGroup group = FoldingGroup.newGroup(OptionalMapSafeCallParam.class.getName());
        ArrayList<FoldingDescriptor> descriptors = new ArrayList<>();
        descriptors.add(new FoldingDescriptor(element.getNode(), element.getTextRange(), group, string));
        return descriptors.toArray(EMPTY_ARRAY);
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
