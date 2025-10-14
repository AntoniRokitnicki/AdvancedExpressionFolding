package com.intellij.advancedExpressionFolding.processor.expression;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.math.basic.*;
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append;
import com.intellij.advancedExpressionFolding.expression.operation.basic.Equal;
import com.intellij.advancedExpressionFolding.expression.operation.basic.Greater;
import com.intellij.advancedExpressionFolding.expression.operation.basic.GreaterEqual;
import com.intellij.advancedExpressionFolding.expression.operation.collection.Range;
import com.intellij.advancedExpressionFolding.processor.util.Consts;
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt.getAnyExpression;
import static com.intellij.advancedExpressionFolding.processor.util.Helper.eraseGenerics;

public class BinaryExpressionExt {
    @Nullable
    public static Expression getBinaryExpression(@NotNull PsiBinaryExpression element, @NotNull Document document) {
        Expression compareToExpression = tryBuildCompareToBasedExpression(element, document);
        if (compareToExpression != null) {
            return compareToExpression;
        }
        Expression basicBinary = tryBuildBasicBinaryOperation(element, document);
        if (basicBinary != null) {
            return basicBinary;
        }
        if (isAndOfBinaryExpressions(element)) {
            return getAndTwoBinaryExpressions(element,
                    ((PsiBinaryExpression) element.getLOperand()),
                    ((PsiBinaryExpression) element.getROperand()), document);
        }
        return null;
    }

    private static boolean isAndOfBinaryExpressions(PsiBinaryExpression element) {
        return "&&".equals(element.getOperationSign().getText())
                && element.getLOperand() instanceof PsiBinaryExpression
                && element.getROperand() instanceof PsiBinaryExpression;
    }

    @Nullable
    private static Expression tryBuildBasicBinaryOperation(@NotNull PsiBinaryExpression element,
                                                           @NotNull Document document) {
        if (Consts.SUPPORTED_BINARY_OPERATORS.contains(element.getOperationSign().getText())
                && element.getROperand() != null) {
            @NotNull Expression leftExpression = getAnyExpression(element.getLOperand(), document);
            @NotNull Expression rightExpression = getAnyExpression(element.getROperand(), document);
            switch (element.getOperationSign().getText()) {
                case "+":
                    return new Add(element, element.getTextRange(), Arrays.asList(leftExpression, rightExpression));
                case "-":
                    return new Subtract(element, element.getTextRange(), Arrays.asList(leftExpression, rightExpression));
                case "*":
                    return new Multiply(element, element.getTextRange(), Arrays.asList(leftExpression, rightExpression));
                case "/":
                    return new Divide(element, element.getTextRange(), Arrays.asList(leftExpression, rightExpression));
                default:
                    return null;
            }
        }
        return null;
    }

    @Nullable
    private static Expression tryBuildCompareToBasedExpression(@NotNull PsiBinaryExpression element,
                                                               @NotNull Document document) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (!settings.getState().getComparingExpressionsCollapse()) {
            return null;
        }
        PsiMethodCallExpression methodCall = getMethodCallOperand(element);
        PsiExpression literal = getLiteralOperand(element);
        if (!isSupportedCompareToPattern(methodCall, literal)) {
            return null;
        }
        PsiMethod method = (PsiMethod) methodCall.getMethodExpression().resolve();
        if (!isSupportedCompareToMethod(method)) {
            return null;
        }
        Expression qualifier = getQualifierExpression(methodCall, document);
        if (qualifier == null) {
            return null;
        }
        @NotNull Expression argument = getAnyExpression(methodCall.getArgumentList().getExpressions()[0], document);
        String operationSign = element.getOperationSign().getText();
        int expressionValue = Integer.parseInt(literal.getText());
        String lessOperation = "<";
        String greaterOperation = ">";
        if (literal == element.getLOperand()) {
            lessOperation = ">";
            greaterOperation = "<";
        }
        return buildCompareToExpression(element, qualifier, argument, operationSign,
                expressionValue, lessOperation, greaterOperation);
    }

    @Nullable
    private static PsiMethodCallExpression getMethodCallOperand(@NotNull PsiBinaryExpression element) {
        if (element.getLOperand() instanceof PsiMethodCallExpression) {
            return (PsiMethodCallExpression) element.getLOperand();
        } else if (element.getROperand() instanceof PsiMethodCallExpression) {
            return (PsiMethodCallExpression) element.getROperand();
        }
        return null;
    }

    @Nullable
    private static PsiExpression getLiteralOperand(@NotNull PsiBinaryExpression element) {
        if (isLiteralOrNegatedLiteral(element.getLOperand())) {
            return element.getLOperand();
        } else if (isLiteralOrNegatedLiteral(element.getROperand())) {
            return element.getROperand();
        }
        return null;
    }

    private static boolean isSupportedCompareToPattern(@Nullable PsiMethodCallExpression methodCall,
                                                       @Nullable PsiExpression literal) {
        if (methodCall == null || literal == null) {
            return false;
        }
        if (!(literal.getText().equals("0") || literal.getText().equals("1") || literal.getText().equals("-1"))) {
            return false;
        }
        Optional<PsiElement> identifier = Stream.of(methodCall.getMethodExpression().getChildren())
                .filter(c -> c instanceof PsiIdentifier).findAny();
        return identifier.isPresent() && identifier.get().getText().equals("compareTo")
                && methodCall.getArgumentList().getExpressions().length == 1;
    }

    private static boolean isSupportedCompareToMethod(@Nullable PsiMethod method) {
        if (method == null) {
            return false;
        }
        PsiClass psiClass = method.getContainingClass();
        return psiClass != null
                && ((psiClass.getQualifiedName() != null
                && Consts.SUPPORTED_CLASSES.contains(eraseGenerics(psiClass.getQualifiedName())))
                || Consts.UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS.contains(method.getName()));
    }

    @Nullable
    private static Expression getQualifierExpression(@NotNull PsiMethodCallExpression methodCall,
                                                     @NotNull Document document) {
        if (methodCall.getMethodExpression().getQualifierExpression() != null) {
            return getAnyExpression(methodCall.getMethodExpression().getQualifierExpression(), document);
        }
        return null;
    }

    @Nullable
    private static Expression buildCompareToExpression(@NotNull PsiBinaryExpression element,
                                                       @NotNull Expression qualifier,
                                                       @NotNull Expression argument,
                                                       @NotNull String operationSign,
                                                       int expressionValue,
                                                       @NotNull String lessOperation,
                                                       @NotNull String greaterOperation) {
        if (operationSign.equals("==")) {
            return buildEqualComparison(element, qualifier, argument, expressionValue);
        } else if (operationSign.equals("!=")) {
            return buildNotEqualComparison(element, qualifier, argument, expressionValue);
        } else if (operationSign.equals(lessOperation)) {
            return buildLessComparison(element, qualifier, argument, expressionValue);
        } else if (operationSign.equals(greaterOperation)) {
            return buildGreaterComparison(element, qualifier, argument, expressionValue);
        } else if (operationSign.equals(lessOperation + "=")) {
            return buildLessEqualComparison(element, qualifier, argument, expressionValue);
        } else if (operationSign.equals(greaterOperation + "=")) {
            return buildGreaterEqualComparison(element, qualifier, argument, expressionValue);
        }
        return null;
    }

    @Nullable
    private static Expression buildEqualComparison(@NotNull PsiBinaryExpression element,
                                                   @NotNull Expression qualifier,
                                                   @NotNull Expression argument,
                                                   int expressionValue) {
        switch (expressionValue) {
            case -1:
                return new Append.Less(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            case 0:
                return new Equal(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            case 1:
                return new Greater(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            default:
                return null;
        }
    }

    @Nullable
    private static Expression buildNotEqualComparison(@NotNull PsiBinaryExpression element,
                                                      @NotNull Expression qualifier,
                                                      @NotNull Expression argument,
                                                      int expressionValue) {
        switch (expressionValue) {
            case 1:
                return new Append.LessEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            case 0:
                return new NotEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            case -1:
                return new GreaterEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            default:
                return null;
        }
    }

    @Nullable
    private static Expression buildLessComparison(@NotNull PsiBinaryExpression element,
                                                  @NotNull Expression qualifier,
                                                  @NotNull Expression argument,
                                                  int expressionValue) {
        switch (expressionValue) {
            case 1:
                return new Append.LessEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            case 0:
                return new Append.Less(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            default:
                return null;
        }
    }

    @Nullable
    private static Expression buildGreaterComparison(@NotNull PsiBinaryExpression element,
                                                     @NotNull Expression qualifier,
                                                     @NotNull Expression argument,
                                                     int expressionValue) {
        switch (expressionValue) {
            case -1:
                return new GreaterEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            case 0:
                return new Greater(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            default:
                return null;
        }
    }

    @Nullable
    private static Expression buildLessEqualComparison(@NotNull PsiBinaryExpression element,
                                                       @NotNull Expression qualifier,
                                                       @NotNull Expression argument,
                                                       int expressionValue) {
        switch (expressionValue) {
            case -1:
                return new Append.Less(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            case 0:
                return new Append.LessEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            default:
                return null;
        }
    }

    @Nullable
    private static Expression buildGreaterEqualComparison(@NotNull PsiBinaryExpression element,
                                                          @NotNull Expression qualifier,
                                                          @NotNull Expression argument,
                                                          int expressionValue) {
        switch (expressionValue) {
            case 1:
                return new Greater(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            case 0:
                return new GreaterEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
            default:
                return null;
        }
    }

    static boolean isLiteralOrNegatedLiteral(PsiElement element) {
        return element instanceof PsiLiteralExpression
                || element instanceof PsiPrefixExpression
                && ((PsiPrefixExpression) element).getOperand() instanceof PsiLiteralExpression
                && "-".equals(((PsiPrefixExpression) element).getOperationSign().getText());
    }

    @Nullable
    public static Expression getAndTwoBinaryExpressions(@NotNull PsiElement parent, @NotNull PsiBinaryExpression a,
                                                        @NotNull PsiBinaryExpression b, @NotNull Document document) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (settings.getState().getRangeExpressionsCollapse()) {
            if ((a.getOperationSign().getText().equals("<") || a.getOperationSign().getText().equals("<="))
                    && (b.getOperationSign().getText().equals(">") || b.getOperationSign().getText().equals(">="))
                    && a.getROperand() != null
                    && b.getROperand() != null) //noinspection Duplicates
            {
                @NotNull Expression e1 = getAnyExpression(a.getLOperand(), document);
                @NotNull Expression e2 = getAnyExpression(a.getROperand(), document);
                @NotNull Expression e3 = getAnyExpression(b.getLOperand(), document);
                @NotNull Expression e4 = getAnyExpression(b.getROperand(), document);
                if (/*e1 instanceof Variable && e3 instanceof Variable && */e1.equals(e3)) {
                    return new Range(parent, TextRange.create(a.getTextRange().getStartOffset(),
                            b.getTextRange().getEndOffset()), e1,
                            e4, b.getOperationSign().getText().equals(">="), e2, a.getOperationSign().getText().equals("<="));
                }
            }
            if ((a.getOperationSign().getText().equals(">") || a.getOperationSign().getText().equals(">="))
                    && (b.getOperationSign().getText().equals("<") || b.getOperationSign().getText().equals("<="))
                    && a.getROperand() != null
                    && b.getROperand() != null) //noinspection Duplicates
            {
                @NotNull Expression e1 = getAnyExpression(a.getLOperand(), document);
                @NotNull Expression e2 = getAnyExpression(a.getROperand(), document);
                @NotNull Expression e3 = getAnyExpression(b.getLOperand(), document);
                @NotNull Expression e4 = getAnyExpression(b.getROperand(), document);
                if (/*e1 instanceof Variable && e3 instanceof Variable && */e1.equals(e3)) {
                    return new Range(parent, TextRange.create(a.getTextRange().getStartOffset(),
                            b.getTextRange().getEndOffset()), e1,
                            e2, a.getOperationSign().getText().equals(">="), e4, b.getOperationSign().getText().equals("<="));
                }
            }
        }
        return null;
    }
}
