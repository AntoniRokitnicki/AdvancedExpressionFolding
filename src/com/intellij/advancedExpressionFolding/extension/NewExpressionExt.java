package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings;
import com.intellij.advancedExpressionFolding.expression.*;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class NewExpressionExt {
    @Nullable
    static Expression getNewExpression(PsiNewExpression element, @NotNull Document document) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        @Nullable PsiType type = element.getType();

        @Nullable String erasedType = type != null ? Helper.eraseGenerics(type.getCanonicalText()) : null;
        if (type != null && Consts.SUPPORTED_CLASSES.contains(erasedType)) {
            if (element.getArgumentList() != null && element.getArgumentList().getExpressions().length == 1) {
                if (element.getArgumentList().getExpressions()[0] instanceof PsiLiteralExpression) {
                    return getConstructorExpression(element,
                            (PsiLiteralExpression) element.getArgumentList().getExpressions()[0],
                            erasedType);
                } else if (element.getArgumentList().getExpressions()[0] instanceof PsiReferenceExpression) {
                    return Helper.getReferenceExpression(
                            (PsiReferenceExpression) element.getArgumentList().getExpressions()[0], true);
                } else if (erasedType.equals("java.util.ArrayList")
                        && element.getArgumentList().getExpressions()[0] instanceof PsiMethodCallExpression) {
                    Expression methodCallExpression = MethodCallExpressionExt.getMethodCallExpression(((PsiMethodCallExpression) element.getArgumentList().getExpressions()[0]), document);
                    if (methodCallExpression instanceof ListLiteral && settings.getState().getGetExpressionsCollapse()) {
                        return new ListLiteral(element, element.getTextRange(), ((ListLiteral) methodCallExpression).getItems());
                    }
                }
            } else if (element.getArgumentList() != null && element.getArgumentList().getExpressions().length == 0) {
                switch (erasedType) {
                    case "java.lang.String":
                    case "java.lang.StringBuilder":
                        return new StringLiteral(element, element.getTextRange(), "");
                    case "java.util.ArrayList":
                        if (settings.getState().getGetExpressionsCollapse()) {
                            return new ListLiteral(element, element.getTextRange(), Collections.emptyList());
                        } else {
                            return null;
                        }
                }
            }
        }
        @Nullable PsiArrayInitializerExpression arrayInitializer = element.getArrayInitializer();
        if (type != null && arrayInitializer != null && settings.getState().getGetExpressionsCollapse()) {
            return new ArrayLiteral(element, element.getTextRange(),
                    Arrays.stream(arrayInitializer.getInitializers())
                            .map(i -> BuildExpressionExt.getAnyExpression(i, document)).collect(
                            Collectors.toList()));
        }
        @Nullable PsiAnonymousClass anonymousClass = element.getAnonymousClass();
        if (type != null && anonymousClass != null && anonymousClass.getLBrace() != null && anonymousClass.getRBrace() != null) {
            if (erasedType.equals("java.util.HashSet")) {
                if (anonymousClass.getInitializers().length == 1) {
                    @NotNull PsiStatement[] statements = anonymousClass.getInitializers()[0].getBody().getStatements();
                    if (statements.length > 0) {
                        boolean flag = true;
                        ArrayList<PsiElement> arguments = new ArrayList<>();
                        for (PsiStatement statement : statements) {
                            if (statement instanceof PsiExpressionStatement
                                    && ((PsiExpressionStatement) statement).getExpression() instanceof PsiMethodCallExpression) {
                                @NotNull PsiMethodCallExpression methodCall = (PsiMethodCallExpression) ((PsiExpressionStatement) statement).getExpression();
                                if (methodCall.getMethodExpression().getText().equals("add") && methodCall.getArgumentList().getExpressions().length == 1) {
                                    PsiMethod method = (PsiMethod) methodCall.getMethodExpression().resolve();
                                    if (method != null && method.getContainingClass() != null
                                            && method.getContainingClass().getQualifiedName() != null
                                            && method.getContainingClass().getQualifiedName().equals("java.util.HashSet")) {
                                        arguments.add(methodCall.getArgumentList().getExpressions()[0]);
                                    } else {
                                        flag = false;
                                        break;
                                    }
                                } else {
                                    flag = false;
                                    break;
                                }
                            } else {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            if (settings.getState().getGetExpressionsCollapse())
                                return new SetLiteral(element, element.getTextRange(),
                                        TextRange.create(anonymousClass.getLBrace().getTextRange().getStartOffset(),
                                                anonymousClass.getRBrace().getTextRange().getEndOffset()),
                                        anonymousClass.getInitializers()[0].getTextRange(),
                                        arguments.stream().map(a -> BuildExpressionExt.getAnyExpression(a, document)).collect(Collectors.toList()));
                        }
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    static Expression getConstructorExpression(@NotNull PsiElement parent, @NotNull PsiLiteralExpression argument, @NotNull String classQualifiedNameNoGenerics) {
        Expression literalExpression = Helper.getLiteralExpression(argument);
        if (literalExpression instanceof NumberLiteral) {
            return new NumberLiteral(parent, parent.getTextRange(), literalExpression.getTextRange(), ((NumberLiteral) literalExpression).getNumber(), false);
        } else {
            try {
                String value = argument.getText();
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                }
                switch (classQualifiedNameNoGenerics) {
                    case "java.lang.Long":
                        return new NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), Long.valueOf(value),
                                !(argument.getValue() instanceof Number));
                    case "java.lang.Integer":
                        return new NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), Integer.valueOf(value),
                                !(argument.getValue() instanceof Number));
                    case "java.lang.Float":
                        return new NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), Float.valueOf(value),
                                !(argument.getValue() instanceof Number));
                    case "java.lang.Double":
                        return new NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), Double.valueOf(value),
                                !(argument.getValue() instanceof Number));
                    case "java.math.BigDecimal":
                        return new NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), new BigDecimal(value),
                                !(argument.getValue() instanceof Number));
                    case "java.math.BigInteger":
                        return new NumberLiteral(parent, parent.getTextRange(), argument.getTextRange(), new BigInteger(value),
                                !(argument.getValue() instanceof Number));
                    case "java.lang.StringBuilder":
                    case "java.lang.String":
                        return new StringLiteral(parent, parent.getTextRange(), value);
                }
            } catch (Exception ignore) {
            }
        }
        return null;
    }
}
