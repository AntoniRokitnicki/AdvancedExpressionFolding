package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static com.intellij.advancedExpressionFolding.BuildExpressionExt.getAnyExpression;
import static com.intellij.advancedExpressionFolding.Helper.eraseGenerics;

public class BinaryExpressionExt {
    @Nullable
    static Expression getBinaryExpression(@NotNull PsiBinaryExpression element, @NotNull Document document) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if ((element.getLOperand() instanceof PsiMethodCallExpression
                && isLiteralOrNegatedLiteral(element.getROperand())
                || element.getROperand() instanceof PsiMethodCallExpression &&
                isLiteralOrNegatedLiteral(element.getLOperand()))
                && settings.getState().getComparingExpressionsCollapse()) {
            PsiMethodCallExpression methodCallExpression = (PsiMethodCallExpression) (element
                    .getLOperand() instanceof PsiMethodCallExpression
                    ? element.getLOperand() : element.getROperand());

            @Nullable PsiExpression literalExpression = isLiteralOrNegatedLiteral(element.getLOperand())
                    ? element.getLOperand() : element.getROperand();

            if (literalExpression != null && literalExpression.getText().equals("0")
                    || literalExpression != null && literalExpression.getText().equals("-1")
                    || literalExpression != null && literalExpression.getText().equals("1")) {

                @NotNull Optional<PsiElement> identifier = Stream.of(methodCallExpression.getMethodExpression().getChildren())
                        .filter(c -> c instanceof PsiIdentifier).findAny();
                if (identifier.isPresent() && identifier.get().getText().equals("compareTo") && methodCallExpression.getArgumentList().getExpressions().length == 1) {
                    @Nullable PsiMethod method = (PsiMethod) methodCallExpression.getMethodExpression().resolve();
                    if (method != null) {
                        @Nullable PsiClass psiClass = method.getContainingClass();
                        if (psiClass != null && (psiClass.getQualifiedName() != null && Consts.SUPPORTED_CLASSES.contains(eraseGenerics(psiClass.getQualifiedName()))
                                || Consts.UNSUPPORTED_CLASSES_METHODS_EXCEPTIONS.contains(method.getName()))) {
                            @Nullable Expression qualifier = methodCallExpression.getMethodExpression()
                                    .getQualifierExpression() != null ? getAnyExpression(methodCallExpression.getMethodExpression()
                                    .getQualifierExpression(), document) : null;
                            if (qualifier != null) {
                                @NotNull Expression argument = getAnyExpression(methodCallExpression.getArgumentList()
                                        .getExpressions()[0], document);

                                String operationSign = element.getOperationSign().getText();

                                int expression = Integer.parseInt(literalExpression.getText());
                                String lessOperation = "<";
                                String greaterOperation = ">";
                                if (literalExpression == element.getLOperand()) {
                                    lessOperation = ">";
                                    greaterOperation = "<";
                                }

                                if (operationSign.equals("==")) {
                                    switch (expression) {
                                        case -1:
                                            return new Less(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                        case 0:
                                            return new Equal(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                        case 1:
                                            return new Greater(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                    }
                                } else if (operationSign.equals("!=")) {
                                    switch (expression) {
                                        case 1:
                                            return new LessEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                        case 0:
                                            return new NotEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                        case -1:
                                            return new GreaterEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                    }

                                }
                                else if(operationSign.equals(lessOperation)) {
                                    switch (expression) {
                                        case 1:
                                            return new LessEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                        case 0:
                                            return new Less(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                    }
                                }
                                else if(operationSign.equals(greaterOperation)) {
                                    switch (expression) {
                                        case -1:
                                            return new GreaterEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                        case 0:
                                            return new Greater(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                    }
                                }
                                else if(operationSign.equals(lessOperation + "=")) {
                                    switch (expression) {
                                        case -1:
                                            return new Less(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                        case 0:
                                            return new LessEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                    }
                                }
                                else if(operationSign.equals(greaterOperation + "=")) {
                                    switch (expression) {
                                        case 1:
                                            return new Greater(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                        case 0:
                                            return new GreaterEqual(element, element.getTextRange(), Arrays.asList(qualifier, argument));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (Consts.SUPPORTED_BINARY_OPERATORS.contains(element.getOperationSign().getText()) && element.getROperand() != null) {
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
            }
        }
        if ("&&".equals(element.getOperationSign().getText())
                && element.getLOperand() instanceof PsiBinaryExpression
                && element.getROperand() instanceof PsiBinaryExpression) {
            return getAndTwoBinaryExpressions(element,
                    ((PsiBinaryExpression) element.getLOperand()), ((PsiBinaryExpression) element.getROperand()), document);
        }
        return null;
    }

    static boolean isLiteralOrNegatedLiteral(PsiElement element) {
        return element instanceof PsiLiteralExpression
                || element instanceof PsiPrefixExpression
                && ((PsiPrefixExpression) element).getOperand() instanceof PsiLiteralExpression
                && "-".equals(((PsiPrefixExpression) element).getOperationSign().getText());
    }

    @Nullable
    static Expression getAndTwoBinaryExpressions(@NotNull PsiElement parent, @NotNull PsiBinaryExpression a,
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
