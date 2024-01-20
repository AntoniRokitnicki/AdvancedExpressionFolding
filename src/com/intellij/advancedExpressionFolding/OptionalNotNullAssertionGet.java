package com.intellij.advancedExpressionFolding;

import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;

public class OptionalNotNullAssertionGet extends Expression {
    private @Nullable Expression object;

    public OptionalNotNullAssertionGet(@NotNull PsiElement element, @NotNull TextRange textRange, @Nullable Expression object) {
        super(element, TextRange.create(textRange.getStartOffset()-1,
                textRange.getEndOffset()+2));
        this.object = object;
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
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, @Nullable Expression parent) {
        ArrayList<FoldingDescriptor> descriptors = new ArrayList<>();
        descriptors.add(
                new FoldingDescriptor(element.getNode(), textRange,
                        FoldingGroup.newGroup(Getter.class.getName()), "!!"));
        if (object != null && object.supportsFoldRegions(document, this)) {
            Collections.addAll(descriptors, object.buildFoldRegions(object.getElement(), document, this));
        }
        return descriptors.toArray(FoldingDescriptor.EMPTY);
    }
}
