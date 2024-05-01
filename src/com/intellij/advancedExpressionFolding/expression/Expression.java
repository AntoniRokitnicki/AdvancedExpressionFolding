package com.intellij.advancedExpressionFolding.expression;

import com.intellij.advancedExpressionFolding.extension.Consts;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class Expression {
    public static final FoldingDescriptor[] EMPTY_ARRAY = new FoldingDescriptor[0];

    private final static double EPSILON = 0.00001;

    public static String HIGHLIGHTED_GROUP_POSTFIX = ":highlighting";

    protected @NotNull PsiElement element;
    protected @NotNull TextRange textRange;

    public Expression(@NotNull PsiElement element, @NotNull TextRange textRange) {
        this.element = element;
        this.textRange = textRange;
    }

    @Override
    public String toString() {
        return element.getText(); // TODO: Use document.getText(textRange)
    }

    @Nullable
    protected static String superscript(String str) {
        return map(str, Consts.SUPERSCRIPT_MAPPING);
    }

    @Nullable
    protected static String subscript(String str) {
        return map(str, Consts.SUBSCRIPT_MAPPING);
    }

    private static String map(String str, Map<Character, Character> subscriptMapping) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            Character c = subscriptMapping.get(str.charAt(i));
            if (c == null) {
                return null;
            } else if (!c.equals('❤')) {
                sb.append((char) c);
            }
        }
        return sb.toString();
    }

    public static boolean equals(double a, double b) {
        return a == b || Math.abs(a - b) < EPSILON;
    }

    public boolean supportsFoldRegions(@NotNull Document document,
                                       @Nullable Expression parent) {
        return false; // TODO no-format: This should be impossible
    }

    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, @Nullable Expression parent) {
        return EMPTY_ARRAY;
    }

    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, @Nullable Expression parent,
                                                @Nullable FoldingGroup overflowGroup, @Nullable String overflowLeftPlaceholder, @Nullable String overflowRightPlaceholder) {
        return buildFoldRegions(element, document, parent);
    }

    public boolean isCollapsedByDefault() {
        return true;
    }

    @NotNull
    public TextRange getTextRange() {
        return textRange;
    }

    @NotNull
    public PsiElement getElement() {
        return element;
    }

    public boolean isNested() {
        return !this.textRange.equals(element.getTextRange());
    }

    public boolean isOverflow() {
        return isLeftOverflow() || isRightOverflow();
    }

    public boolean isLeftOverflow() {
        return false;
    }

    public boolean isRightOverflow() {
        return false;
    }

    public boolean isHighlighted() {
        return false;
    }

    public TextRange getHighlightedTextRange() {
        return getTextRange();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expression that = (Expression) o;

        if (!element.equals(that.element)) return false;
        return textRange.equals(that.textRange);
    }

    @Override
    public int hashCode() {
        int result = element.hashCode();
        result = 31 * result + textRange.hashCode();
        return result;
    }
}
