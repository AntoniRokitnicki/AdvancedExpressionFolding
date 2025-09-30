package com.intellij.advancedExpressionFolding.expression.literal;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class LocalDateLiteral extends Expression {
    public static final String DATE_SEPARATOR = "-";

    public static final String YEAR_POSTFIX = "Y";
    public static final String MONTH_POSTFIX = "M";
    public static final String DAY_POSTFIX = "D";

    @NotNull
    private final PsiLiteralExpression year;
    @NotNull
    private final PsiLiteralExpression month;
    @NotNull
    private final PsiLiteralExpression day;

    public LocalDateLiteral(@NotNull PsiElement element, @NotNull TextRange textRange, @NotNull PsiLiteralExpression year, @NotNull PsiLiteralExpression month, @NotNull PsiLiteralExpression day) {
        super(element, textRange);
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean supportsFoldRegions(@NotNull Document document,
                                       @Nullable Expression parent) {
        return true;
    }

    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, @Nullable Expression parent) {
        FoldingGroup group = FoldingGroup.newGroup(ListLiteral.class.getName());
        ArrayList<FoldingDescriptor> descriptors = new ArrayList<>();
        descriptors.add(createPrefixDescriptor(element, group));

        boolean usePostfix = AdvancedExpressionFoldingSettings.getInstance().getState().getLocalDateLiteralPostfixCollapse();
        String dateSep = DATE_SEPARATOR;
        String yearPostfix = usePostfix ? YEAR_POSTFIX : "";
        String monthPostfix = usePostfix ? MONTH_POSTFIX : "";
        String dayPostfix = usePostfix ? DAY_POSTFIX : "";

        descriptors.add(createYearMonthDescriptor(element, group, dateSep, yearPostfix));
        descriptors.add(createMonthDayDescriptor(element, group, dateSep, monthPostfix));
        descriptors.add(createDaySuffixDescriptor(element, group, dayPostfix));

        return descriptors.toArray(new FoldingDescriptor[0]);
    }

    private FoldingDescriptor createPrefixDescriptor(@NotNull PsiElement element, @NotNull FoldingGroup group) {
        return new FoldingDescriptor(element.getNode(), TextRange.create(textRange.getStartOffset(),
                year.getTextRange().getStartOffset()), group) {
            @NotNull
            @Override
            public String getPlaceholderText() {
                return "";
            }
        };
    }

    private FoldingDescriptor createYearMonthDescriptor(@NotNull PsiElement element,
                                                        @NotNull FoldingGroup group,
                                                        @NotNull String dateSep,
                                                        @NotNull String yearPostfix) {
        return new FoldingDescriptor(element.getNode(), TextRange.create(year.getTextRange().getEndOffset(),
                month.getTextRange().getStartOffset()), group) {
            @NotNull
            @Override
            public String getPlaceholderText() {
                if (month.getTextLength() == 1) {
                    return yearPostfix + dateSep + "0";
                } else {
                    return yearPostfix + dateSep;
                }
            }
        };
    }

    private FoldingDescriptor createMonthDayDescriptor(@NotNull PsiElement element,
                                                       @NotNull FoldingGroup group,
                                                       @NotNull String dateSep,
                                                       @NotNull String monthPostfix) {
        return new FoldingDescriptor(element.getNode(), TextRange.create(month.getTextRange().getEndOffset(),
                day.getTextRange().getStartOffset()), group) {
            @NotNull
            @Override
            public String getPlaceholderText() {
                if (day.getTextLength() == 1) {
                    return monthPostfix + dateSep + "0";
                } else {
                    return monthPostfix + dateSep;
                }
            }
        };
    }

    private FoldingDescriptor createDaySuffixDescriptor(@NotNull PsiElement element,
                                                        @NotNull FoldingGroup group,
                                                        @NotNull String dayPostfix) {
        return new FoldingDescriptor(element.getNode(), TextRange.create(day.getTextRange().getEndOffset(),
                textRange.getEndOffset()), group) {
            @NotNull
            @Override
            public String getPlaceholderText() {
                return dayPostfix + "";
            }
        };
    }
}
