package com.intellij.advancedExpressionFolding;

import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class LombokGetterSetterExpression extends Expression {
    private final List<LombokGetterSetter> getterSetterList;

    public LombokGetterSetterExpression(PsiClass element, List<LombokGetterSetter> getterSetterList) {
        super(element, element.getTextRange());
        this.getterSetterList = getterSetterList;
    }

    @Override
    public boolean supportsFoldRegions(@NotNull Document document, @Nullable Expression parent) {
        return true;
    }

    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, @Nullable Expression parent) {
        return Stream.concat(getterSetterList.stream().flatMap(this::mapSubElement), Stream.of(mapClass(element, document))).toArray(FoldingDescriptor[]::new);
    }

    private FoldingDescriptor mapClass(@NotNull PsiElement clazz, @NotNull Document document) {
        var element = clazz;
        var range = element.getTextRange();
        TextRange newRange = TextRange.create(range.getStartOffset(), range.getStartOffset() + 1);
        String firstChar = newRange.substring(document.getText());
        return fold(element, newRange, "@Getter @Setter " + firstChar);
    }

    private Stream<FoldingDescriptor> mapSubElement(LombokGetterSetter lombokGetterSetter) {
        return lombokGetterSetter.getElements().flatMap(this::mapSubElement);
    }

    private Stream<FoldingDescriptor> mapSubElement(PsiElement element) {
        PsiElement prev = element.getPrevSibling();
        FoldingDescriptor fold = fold(element, element.getTextRange(), "");
        if (prev instanceof PsiWhiteSpace) {
            return Stream.of(fold, fold(prev, prev.getTextRange(), ""));
        }
        return Stream.of(fold);
    }

    @NotNull
    private static FoldingDescriptor fold(PsiElement element, TextRange textRange, String placeholderText) {
        return new FoldingDescriptor(element.getNode(),
                textRange,
                FoldingGroup.newGroup(LombokGetterSetterExpression.class.getName()),
                placeholderText);
    }
}
