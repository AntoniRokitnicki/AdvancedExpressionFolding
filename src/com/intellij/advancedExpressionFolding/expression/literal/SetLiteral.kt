package com.intellij.advancedExpressionFolding.expression.literal;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.Function;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetLiteral extends Function {
    @NotNull
    private final TextRange firstBracesRange;
    @NotNull
    private final TextRange secondBracesRange;

    public SetLiteral(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull TextRange firstBracesRange,
                      @NotNull TextRange secondBracesRange, @NotNull List<Expression> items) {
        super(element, textRange, "Set.of", items);
        this.firstBracesRange = firstBracesRange;
        this.secondBracesRange = secondBracesRange;
    }

    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, @Nullable Expression parent) {
        FoldingGroup group = FoldingGroup.newGroup(getClass().getName());
        List<FoldingDescriptor> descriptors = new ArrayList<>();
        if (operands.isEmpty()) {
            descriptors.add(new FoldingDescriptor(element.getNode(), getTextRange(), group, "[]"));
            return descriptors.toArray(EMPTY_ARRAY);
        }
        int offset = getTextRange().getStartOffset();
        int start = offset;
        int end = firstBracesRange.getStartOffset();
        if (start < end) {
            descriptors.add(new FoldingDescriptor(element.getNode(), TextRange.create(start, end), group, "["));
        }
        start = firstBracesRange.getStartOffset();
        end = secondBracesRange.getStartOffset();
        if (start < end) {
            descriptors.add(new FoldingDescriptor(element.getNode(), TextRange.create(start, end), group, ""));
        }
        start = secondBracesRange.getStartOffset();
        end = operands.get(0).getTextRange().getStartOffset();
        if (start < end) {
            descriptors.add(new FoldingDescriptor(element.getNode(), TextRange.create(start, end), group, ""));
        }
        offset = operands.get(0).getTextRange().getEndOffset();
        for (int i = 1; i < operands.size(); i++) {
            start = offset;
            end = operands.get(i).getTextRange().getStartOffset();
            if (start < end) {
                TextRange r = TextRange.create(start, end);
                String p = ", ";
                if (!document.getText(r).equals(p)) {
                    descriptors.add(new FoldingDescriptor(element.getNode(), r, group, p));
                }
            }
            offset = operands.get(i).getTextRange().getEndOffset();
        }
        start = offset;
        end = secondBracesRange.getEndOffset();
        if (start < end) {
            descriptors.add(new FoldingDescriptor(element.getNode(), TextRange.create(start, end), group, ""));
        }
        start = secondBracesRange.getEndOffset();
        end = firstBracesRange.getEndOffset() - 1;
        if (start < end) {
            descriptors.add(new FoldingDescriptor(element.getNode(), TextRange.create(start, end), group, ""));
        }
        start = firstBracesRange.getEndOffset() - 1;
        end = firstBracesRange.getEndOffset();
        if (start < end) {
            descriptors.add(new FoldingDescriptor(element.getNode(), TextRange.create(start, end), group, "]"));
        }
        start = firstBracesRange.getEndOffset();
        end = getTextRange().getEndOffset();
        if (start < end) {
            descriptors.add(new FoldingDescriptor(element.getNode(), TextRange.create(start, end), group, ""));
        }
        for (Expression operand : operands) {
            if (operand.supportsFoldRegions(document, this)) {
                Collections.addAll(descriptors, operand.buildFoldRegions(operand.getElement(), document, this));
            }
        }
        return descriptors.toArray(EMPTY_ARRAY);
    }

    @NotNull
    public TextRange getFirstBracesRange() {
        return firstBracesRange;
    }

    @NotNull
    public TextRange getSecondBracesRange() {
        return secondBracesRange;
    }
}
