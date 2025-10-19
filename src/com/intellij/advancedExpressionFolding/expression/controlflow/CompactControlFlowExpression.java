package com.intellij.advancedExpressionFolding.expression.controlflow;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.literal.InterpolatedString;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.PsiBlockStatement;
import com.intellij.psi.PsiBreakStatement;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiKeyword;
import com.intellij.psi.PsiStatement;
import com.intellij.psi.PsiSwitchBlock;
import com.intellij.psi.PsiSwitchExpression;
import com.intellij.psi.PsiSwitchLabelStatement;
import com.intellij.psi.PsiSwitchLabeledRuleStatement;
import com.intellij.psi.PsiSwitchStatement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.PsiYieldStatement;
import com.intellij.psi.PsiReturnStatement;
import com.intellij.psi.PsiThrowStatement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompactControlFlowExpression extends Expression {
    public CompactControlFlowExpression(@NotNull PsiElement element,
                                        @NotNull TextRange textRange) {
        super(element, textRange);
    }

    public static void buildFoldRegions(@NotNull PsiElement element, FoldingGroup group,
                                        ArrayList<FoldingDescriptor> descriptors, TextRange textRange) {
        descriptors.add(new FoldingDescriptor(element.getNode(),
                TextRange.create(textRange.getStartOffset(),
                        textRange.getStartOffset() + 1), group, ""));
        descriptors.add(new FoldingDescriptor(element.getNode(),
                TextRange.create(textRange.getEndOffset() - 1,
                        textRange.getEndOffset()), group, ""));
        if (element instanceof PsiSwitchExpression) {
            foldSwitchBlock((PsiSwitchExpression) element, group, descriptors);
        } else if (element instanceof PsiSwitchStatement) {
            foldSwitchBlock((PsiSwitchStatement) element, group, descriptors);
        }
    }

    private static void foldSwitchBlock(@NotNull PsiSwitchBlock switchBlock,
                                        @NotNull FoldingGroup group,
                                        @NotNull ArrayList<FoldingDescriptor> descriptors) {
        PsiElement keyword = ((PsiElement) switchBlock).getFirstChild();
        while (keyword instanceof PsiWhiteSpace) {
            keyword = keyword.getNextSibling();
        }
        if (keyword instanceof PsiKeyword && ((PsiKeyword) keyword).getTokenType() == JavaTokenType.SWITCH_KEYWORD) {
            descriptors.add(new FoldingDescriptor(switchBlock.getNode(), keyword.getTextRange(), group, "when"));
        }
        PsiCodeBlock body = switchBlock.getBody();
        if (body == null) {
            return;
        }
        for (PsiStatement statement : body.getStatements()) {
            if (statement instanceof PsiSwitchLabeledRuleStatement) {
                foldRuleStatement(switchBlock, group, descriptors, (PsiSwitchLabeledRuleStatement) statement);
            }
        }
        if (switchBlock instanceof PsiSwitchStatement) {
            foldColonStyleSwitch((PsiSwitchStatement) switchBlock, group, descriptors);
        }
    }

    private static void foldRuleStatement(@NotNull PsiSwitchBlock switchBlock,
                                          @NotNull FoldingGroup group,
                                          @NotNull ArrayList<FoldingDescriptor> descriptors,
                                          @NotNull PsiSwitchLabeledRuleStatement statement) {
        PsiElement first = statement.getFirstChild();
        while (first instanceof PsiWhiteSpace) {
            first = first.getNextSibling();
        }
        if (first instanceof PsiKeyword && ((PsiKeyword) first).getTokenType() == JavaTokenType.DEFAULT_KEYWORD) {
            descriptors.add(new FoldingDescriptor(switchBlock.getNode(), first.getTextRange(), group, "else"));
        }
    }

    private static void foldColonStyleSwitch(@NotNull PsiSwitchStatement statement,
                                             @NotNull FoldingGroup group,
                                             @NotNull ArrayList<FoldingDescriptor> descriptors) {
        PsiCodeBlock body = statement.getBody();
        if (body == null) {
            return;
        }
        PsiStatement[] statements = body.getStatements();
        boolean hasColonLabels = false;
        for (PsiStatement psiStatement : statements) {
            if (psiStatement instanceof PsiSwitchLabelStatement) {
                hasColonLabels = true;
                break;
            }
        }
        if (!hasColonLabels) {
            return;
        }
        List<ColonBranch> branches = collectColonBranches(statement);
        if (branches == null) {
            return;
        }
        for (ColonBranch branch : branches) {
            descriptors.add(new FoldingDescriptor(statement.getNode(), branch.getLabelRange(), group, branch.getPlaceholder()));
            for (TextRange range : branch.getSuppressedRanges()) {
                if (range != null && range.getStartOffset() < range.getEndOffset()) {
                    descriptors.add(new FoldingDescriptor(statement.getNode(), range, group, ""));
                }
            }
        }
    }

    @Override
    public boolean supportsFoldRegions(@NotNull Document document, @Nullable Expression parent) {
        return supportsFoldRegions(document, textRange);
    }

    public static boolean supportsFoldRegions(@NotNull Document document, TextRange textRange) {
        return textRange.getStartOffset() > 0 && textRange.getEndOffset() < document.getTextLength() - 1
                &&
                InterpolatedString.OVERFLOW_CHARACTERS.contains(document.getText(
                        TextRange.create(textRange.getStartOffset() - 1, textRange.getStartOffset())))
                && InterpolatedString.OVERFLOW_CHARACTERS.contains(document.getText(
                TextRange.create(textRange.getEndOffset(), textRange.getEndOffset() + 1)));
    }

    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document,
                                                @Nullable Expression parent) {
        ArrayList<FoldingDescriptor> descriptors = new ArrayList<>();
        buildFoldRegions(element, FoldingGroup
                        .newGroup(CompactControlFlowExpression.class.getName()
                                + Expression.HIGHLIGHTED_GROUP_POSTFIX),
                descriptors, textRange);
        return descriptors.toArray(EMPTY_ARRAY);
    }

    @Override
    public boolean isHighlighted() {
        return true;
    }

    @Nullable
    private static List<ColonBranch> collectColonBranches(@NotNull PsiSwitchStatement statement) {
        PsiCodeBlock body = statement.getBody();
        if (body == null) {
            return Collections.emptyList();
        }
        PsiStatement[] statements = body.getStatements();
        if (statements.length == 0) {
            return Collections.emptyList();
        }
        ArrayList<ColonBranch> result = new ArrayList<>();
        int index = 0;
        while (index < statements.length) {
            if (!(statements[index] instanceof PsiSwitchLabelStatement)) {
                return null;
            }
            ArrayList<PsiSwitchLabelStatement> labels = new ArrayList<>();
            while (index < statements.length && statements[index] instanceof PsiSwitchLabelStatement) {
                labels.add((PsiSwitchLabelStatement) statements[index]);
                index++;
            }
            ArrayList<PsiStatement> branchStatements = new ArrayList<>();
            while (index < statements.length && !(statements[index] instanceof PsiSwitchLabelStatement)) {
                branchStatements.add(statements[index]);
                index++;
            }
            PsiStatement nextStatement = index < statements.length ? statements[index] : null;
            ColonBranch branch = createColonBranch(statement, labels, branchStatements, nextStatement);
            if (branch == null) {
                return null;
            }
            result.add(branch);
        }
        return result;
    }

    @Nullable
    private static ColonBranch createColonBranch(@NotNull PsiSwitchStatement statement,
                                                 @NotNull List<PsiSwitchLabelStatement> labels,
                                                 @NotNull List<PsiStatement> branchStatements,
                                                 @Nullable PsiStatement nextStatement) {
        boolean isDefault = false;
        ArrayList<String> labelValues = new ArrayList<>();
        for (PsiSwitchLabelStatement label : labels) {
            if (label.isDefaultCase()) {
                isDefault = true;
            } else {
                labelValues.addAll(extractLabelValues(label));
            }
        }
        if (!isDefault && labelValues.isEmpty()) {
            return null;
        }
        List<TextRange> suppressedRanges = new ArrayList<>();
        List<PsiStatement> visibleStatements = new ArrayList<>();
        boolean hasBreak = false;
        for (PsiStatement statementInBranch : branchStatements) {
            if (statementInBranch instanceof PsiBreakStatement) {
                PsiBreakStatement breakStatement = (PsiBreakStatement) statementInBranch;
                if (breakStatement.getLabelIdentifier() != null) {
                    return null;
                }
                suppressedRanges.add(breakStatement.getTextRange());
                hasBreak = true;
            } else if (statementInBranch instanceof PsiBlockStatement) {
                visibleStatements.add(statementInBranch);
                PsiCodeBlock block = ((PsiBlockStatement) statementInBranch).getCodeBlock();
                PsiStatement[] innerStatements = block.getStatements();
                for (int i = 0; i < innerStatements.length; i++) {
                    PsiStatement inner = innerStatements[i];
                    if (inner instanceof PsiBreakStatement) {
                        PsiBreakStatement breakStatement = (PsiBreakStatement) inner;
                        if (breakStatement.getLabelIdentifier() != null || i != innerStatements.length - 1) {
                            return null;
                        }
                        suppressedRanges.add(breakStatement.getTextRange());
                        hasBreak = true;
                    }
                }
            } else {
                visibleStatements.add(statementInBranch);
            }
        }
        boolean lastBranch = nextStatement == null;
        if (!isConvertibleBranch(visibleStatements, hasBreak, lastBranch)) {
            return null;
        }
        PsiSwitchLabelStatement firstLabel = labels.get(0);
        PsiSwitchLabelStatement lastLabel = labels.get(labels.size() - 1);
        TextRange labelRange = TextRange.create(firstLabel.getTextRange().getStartOffset(),
                lastLabel.getTextRange().getEndOffset());
        String placeholder = (isDefault ? "else" : "case " + String.join(", ", labelValues)) + " ->";
        return new ColonBranch(labelRange, placeholder, suppressedRanges);
    }

    private static boolean isConvertibleBranch(@NotNull List<PsiStatement> visibleStatements,
                                               boolean hasBreak,
                                               boolean lastBranch) {
        if (visibleStatements.isEmpty()) {
            return hasBreak || lastBranch;
        }
        if (hasBreak) {
            return true;
        }
        if (lastBranch) {
            return true;
        }
        PsiStatement lastStatement = visibleStatements.get(visibleStatements.size() - 1);
        if (lastStatement instanceof PsiReturnStatement
                || lastStatement instanceof PsiThrowStatement
                || lastStatement instanceof PsiYieldStatement) {
            return true;
        }
        if (lastStatement instanceof PsiBlockStatement) {
            PsiCodeBlock block = ((PsiBlockStatement) lastStatement).getCodeBlock();
            PsiStatement[] statements = block.getStatements();
            int length = statements.length;
            while (length > 0 && statements[length - 1] instanceof PsiBreakStatement) {
                PsiBreakStatement breakStatement = (PsiBreakStatement) statements[length - 1];
                if (breakStatement.getLabelIdentifier() != null) {
                    break;
                }
                length--;
            }
            if (length == 0) {
                return false;
            }
            PsiStatement innerLast = statements[length - 1];
            return innerLast instanceof PsiReturnStatement
                    || innerLast instanceof PsiThrowStatement
                    || innerLast instanceof PsiYieldStatement;
        }
        return false;
    }

    private static List<String> extractLabelValues(@NotNull PsiSwitchLabelStatement label) {
        String text = label.getText().trim();
        if (text.endsWith(":")) {
            text = text.substring(0, text.length() - 1).trim();
        }
        if (text.startsWith("case")) {
            text = text.substring(4).trim();
        }
        if (text.isEmpty()) {
            return Collections.emptyList();
        }
        String[] parts = text.split(",");
        ArrayList<String> values = new ArrayList<>();
        for (String part : parts) {
            String value = part.trim();
            if (!value.isEmpty()) {
                values.add(value);
            }
        }
        return values;
    }

    private static final class ColonBranch {
        private final TextRange labelRange;
        private final String placeholder;
        private final List<TextRange> suppressedRanges;

        private ColonBranch(@NotNull TextRange labelRange,
                            @NotNull String placeholder,
                            @NotNull List<TextRange> suppressedRanges) {
            this.labelRange = labelRange;
            this.placeholder = placeholder;
            this.suppressedRanges = suppressedRanges;
        }

        public TextRange getLabelRange() {
            return labelRange;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public List<TextRange> getSuppressedRanges() {
            return suppressedRanges;
        }
    }
}
