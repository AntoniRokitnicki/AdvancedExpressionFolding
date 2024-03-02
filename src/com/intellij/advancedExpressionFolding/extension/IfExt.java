package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings;
import com.intellij.advancedExpressionFolding.expression.*;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class IfExt {

    static Expression getSwitchStatement(PsiSwitchStatement element) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (element.getExpression() != null
                && element.getLParenth() != null && element.getRParenth() != null
                && settings.getState().getCompactControlFlowSyntaxCollapse()) {
            return new CompactControlFlowExpression(element,
                    TextRange.create(element.getLParenth().getTextRange().getStartOffset(),
                            element.getRParenth().getTextRange().getEndOffset()));
        }
        return null;
    }

    @Nullable
    static Expression getIfExpression(PsiIfStatement element, Document document) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        var getIfExpression = LetReturnExt.getIfExpression(element);
        if (getIfExpression != null) {
            return getIfExpression;
        }

        if (settings.getState().getCheckExpressionsCollapse()
                && element.getCondition() instanceof PsiBinaryExpression) {
            PsiBinaryExpression condition = (PsiBinaryExpression) element.getCondition();
            if (condition.getOperationSign().getText().equals("!=")
                    && element.getElseBranch() == null
                    && (BaseExtension.isNull(condition.getLOperand().getType())
                    && condition.getROperand() != null
                    || condition.getROperand() != null && BaseExtension.isNull(condition.getROperand().getType()))
                    && element.getThenBranch() != null) {
                PsiStatement thenStatement = element.getThenBranch();
                if (thenStatement.getChildren().length == 1 && thenStatement
                        .getChildren()[0] instanceof PsiCodeBlock) {
                    PsiStatement[] statements = ((PsiCodeBlock) thenStatement.getChildren()[0]).getStatements();
                    if (statements.length == 1) {
                        thenStatement = statements[0];
                    } else {
                        return null;
                    }
                }
                PsiElement qualifier = BaseExtension.isNull(condition.getLOperand().getType())
                        ? condition.getROperand()
                        : condition.getLOperand();
                if (qualifier instanceof PsiReferenceExpression
                        || (qualifier instanceof PsiMethodCallExpression
                        && Helper.startsWith(((PsiMethodCallExpression) qualifier).getMethodExpression().getReferenceName(), "get")
                        && ((PsiMethodCallExpression) qualifier).getArgumentList().getExpressions().length == 0)) {
                    PsiElement r = Helper.findSameQualifier(thenStatement, qualifier);
                    if (r != null) {
                        return new ShortElvisExpression(element, element.getTextRange(),
                                BuildExpressionExt.getAnyExpression(thenStatement, document),
                                Collections.singletonList(r.getTextRange()));
                    }
                }
            }
        }
        /*if (element.getCondition() != null
                && element.getLParenth() != null && element.getRParenth() != null) {
            return new CompactControlFlowExpression(element,
                    TextRange.create(element.getLParenth().getTextRange().getStartOffset(),
                            element.getRParenth().getTextRange().getEndOffset()));
        }*/
        return new IfExpression(element, element.getTextRange());
    }

    @Nullable
    static Expression getConditionalExpression(@NotNull PsiConditionalExpression element, @NotNull Document document) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (settings.getState().getCheckExpressionsCollapse()
                && element.getCondition() instanceof @NotNull PsiBinaryExpression condition) {
            if (condition.getOperationSign().getText().equals("!=")
                    && condition.getROperand() != null
                    && (BaseExtension.isNull(condition.getLOperand().getType())
                    || BaseExtension.isNull(condition.getROperand().getType()))
                    && element.getThenExpression() != null
                    && element.getElseExpression() != null) {
                PsiElement qualifier = BaseExtension.isNull(condition.getLOperand().getType())
                        ? condition.getROperand()
                        : condition.getLOperand();
                if (qualifier instanceof PsiReferenceExpression
                        || (qualifier instanceof PsiMethodCallExpression
                        && Helper.startsWith(((PsiMethodCallExpression) qualifier).getMethodExpression().getReferenceName(), "get")
                        && ((PsiMethodCallExpression) qualifier).getArgumentList().getExpressions().length == 0)) {
                    PsiReferenceExpression r = qualifier instanceof PsiReferenceExpression
                            ? ((PsiReferenceExpression) qualifier)
                            : ((PsiMethodCallExpression) qualifier).getMethodExpression();
                    List<PsiElement> references = SyntaxTraverser.psiTraverser(element.getThenExpression())
                            .filter(e ->
                                    e instanceof PsiReferenceExpression
                                            && !(e.getParent() instanceof PsiMethodCallExpression)
                                            && Helper.isReferenceToReference((PsiReferenceExpression) e, r)
                                            || e instanceof PsiMethodCallExpression && Helper.isReferenceToReference(((PsiMethodCallExpression) e).getMethodExpression(), r)
                            ).toList();
                    if (!references.isEmpty()) {
                        return new ElvisExpression(element, element.getTextRange(),
                                BuildExpressionExt.getAnyExpression(element.getThenExpression(), document),
                                BuildExpressionExt.getAnyExpression(element.getElseExpression(), document),
                                references.stream().map(PsiElement::getTextRange).collect(Collectors.toList()));
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    static Expression getPolyadicExpression(@NotNull PsiPolyadicExpression element, @NotNull Document document) {
        boolean add = true;
        boolean string = false;
        Expression[] operands = null;
        for (int i = 0; i < element.getOperands().length - 1; i++) {
            PsiExpression a = element.getOperands()[i];
            PsiExpression b = element.getOperands()[i + 1];
            PsiJavaToken token = element.getTokenBeforeOperand(b);
            if (token != null) {
                if ("&&".equals(token.getText())
                        && a instanceof PsiBinaryExpression
                        && b instanceof PsiBinaryExpression) {
                    Expression twoBinaryExpression = BinaryExpressionExt.getAndTwoBinaryExpressions(element,
                            ((PsiBinaryExpression) a), ((PsiBinaryExpression) b), document);
                    if (twoBinaryExpression != null) {
                        return twoBinaryExpression;
                    }
                }
                if (add && "+".equals(token.getText())) {
                    if (operands == null) {
                        operands = new Expression[element.getOperands().length];
                    }
                    operands[i] = BuildExpressionExt.getAnyExpression(element.getOperands()[i], document);
                    if (operands[i] instanceof StringLiteral) {
                        string = true;
                    }
                } else {
                    add = false;
                }
            }
        }
        if (add && operands != null) {
            operands[element.getOperands().length - 1] = BuildExpressionExt.getAnyExpression(
                    element.getOperands()[element.getOperands().length - 1], document);
            if (operands[element.getOperands().length - 1] instanceof StringLiteral) {
                string = true;
            }
        }
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (add && operands != null && string && settings.getState().getConcatenationExpressionsCollapse()) {
            return new InterpolatedString(element, element.getTextRange(), Arrays.asList(operands));
        } else if (add && operands != null) {
            return new Add(element, element.getTextRange(), Arrays.asList(operands)); // TODO: Support other operations as well
        }
        if (element instanceof PsiBinaryExpression) {
            Expression binaryExpression = BinaryExpressionExt.getBinaryExpression((PsiBinaryExpression) element, document);
            if (binaryExpression != null) {
                return binaryExpression;
            }
        }
        Expression expression = IfNullSafeExt.createExpression(element, document);
        if (expression != null) {
            return expression;
        }
        return null;
    }
}
