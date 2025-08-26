package com.intellij.advancedExpressionFolding.processor.reference;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.literal.*;
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt;
import com.intellij.advancedExpressionFolding.processor.expression.LiteralExpressionExt;
import com.intellij.advancedExpressionFolding.processor.methodcall.MethodCallExpressionExt;
import com.intellij.advancedExpressionFolding.processor.util.Consts;
import com.intellij.advancedExpressionFolding.processor.util.Helper;
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings;
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
    public static Expression getNewExpression(PsiNewExpression element, @NotNull Document document) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        @Nullable PsiType type = element.getType();
        @Nullable String erasedType = type != null ? Helper.eraseGenerics(type.getCanonicalText()) : null;

        Expression expression;
        expression = handleSupportedClasses(element, document, settings, type, erasedType);
        if (expression != null) {
            return expression;
        }
        expression = handleArrayInitializer(element, document, settings, type);
        if (expression != null) {
            return expression;
        }
        expression = handleAnonymousClass(element, document, settings, type, erasedType);
        if (expression != null) {
            return expression;
        }
        return null;
    }

    @Nullable
    private static Expression handleSupportedClasses(PsiNewExpression element, Document document,
                                                     AdvancedExpressionFoldingSettings settings, @Nullable PsiType type,
                                                     @Nullable String erasedType) {
        if (type == null || erasedType == null || !Consts.SUPPORTED_CLASSES.contains(erasedType)) {
            return null;
        }
        PsiExpressionList argumentList = element.getArgumentList();
        if (argumentList == null) {
            return null;
        }
        PsiExpression[] expressions = argumentList.getExpressions();
        if (expressions.length == 1) {
            return handleSingleArgument(element, document, settings, erasedType, expressions[0]);
        } else if (expressions.length == 0) {
            return handleNoArguments(element, settings, erasedType);
        }
        return null;
    }

    @Nullable
    private static Expression handleSingleArgument(PsiNewExpression element, Document document,
                                                   AdvancedExpressionFoldingSettings settings, String erasedType,
                                                   PsiExpression arg) {
        if (arg instanceof PsiLiteralExpression) {
            return getConstructorExpression(element, (PsiLiteralExpression) arg, erasedType);
        } else if (arg instanceof PsiReferenceExpression) {
            return ReferenceExpressionExt.getReferenceExpression((PsiReferenceExpression) arg, true);
        } else if ("java.util.ArrayList".equals(erasedType) && arg instanceof PsiMethodCallExpression) {
            Expression methodCallExpression = MethodCallExpressionExt.getMethodCallExpression((PsiMethodCallExpression) arg, document);
            if (methodCallExpression instanceof ListLiteral && settings.getState().getGetExpressionsCollapse()) {
                return new ListLiteral(element, element.getTextRange(), ((ListLiteral) methodCallExpression).getItems());
            }
        }
        return null;
    }

    @Nullable
    private static Expression handleNoArguments(PsiNewExpression element,
                                                AdvancedExpressionFoldingSettings settings,
                                                String erasedType) {
        switch (erasedType) {
            case "java.lang.String":
            case "java.lang.StringBuilder":
                return new StringLiteral(element, element.getTextRange(), "");
            case "java.util.ArrayList":
                if (settings.getState().getGetExpressionsCollapse()) {
                    return new ListLiteral(element, element.getTextRange(), Collections.emptyList());
                }
                return null;
            default:
                return null;
        }
    }

    @Nullable
    private static Expression handleArrayInitializer(PsiNewExpression element, Document document,
                                                     AdvancedExpressionFoldingSettings settings,
                                                     @Nullable PsiType type) {
        PsiArrayInitializerExpression arrayInitializer = element.getArrayInitializer();
        if (type != null && arrayInitializer != null && settings.getState().getGetExpressionsCollapse()) {
            return new ArrayLiteral(element, element.getTextRange(),
                    Arrays.stream(arrayInitializer.getInitializers())
                            .map(i -> BuildExpressionExt.getAnyExpression(i, document))
                            .collect(Collectors.toList()));
        }
        return null;
    }

    @Nullable
    private static Expression handleAnonymousClass(PsiNewExpression element, Document document,
                                                   AdvancedExpressionFoldingSettings settings, @Nullable PsiType type,
                                                   @Nullable String erasedType) {
        PsiAnonymousClass anonymousClass = element.getAnonymousClass();
        if (type == null || anonymousClass == null
                || anonymousClass.getLBrace() == null || anonymousClass.getRBrace() == null) {
            return null;
        }
        if ("java.util.HashSet".equals(erasedType) && anonymousClass.getInitializers().length == 1) {
            PsiStatement[] statements = anonymousClass.getInitializers()[0].getBody().getStatements();
            if (statements.length > 0) {
                ArrayList<PsiElement> arguments = collectHashSetArguments(statements);
                if (arguments != null && settings.getState().getGetExpressionsCollapse()) {
                    return new SetLiteral(element, element.getTextRange(),
                            TextRange.create(anonymousClass.getLBrace().getTextRange().getStartOffset(),
                                    anonymousClass.getRBrace().getTextRange().getEndOffset()),
                            anonymousClass.getInitializers()[0].getTextRange(),
                            arguments.stream()
                                    .map(a -> BuildExpressionExt.getAnyExpression(a, document))
                                    .collect(Collectors.toList()));
                }
            }
        }
        return null;
    }

    @Nullable
    private static ArrayList<PsiElement> collectHashSetArguments(PsiStatement[] statements) {
        ArrayList<PsiElement> arguments = new ArrayList<>();
        for (PsiStatement statement : statements) {
            if (!(statement instanceof PsiExpressionStatement)) {
                return null;
            }
            PsiExpression expression = ((PsiExpressionStatement) statement).getExpression();
            if (!(expression instanceof PsiMethodCallExpression)) {
                return null;
            }
            PsiMethodCallExpression methodCall = (PsiMethodCallExpression) expression;
            if (!"add".equals(methodCall.getMethodExpression().getText())
                    || methodCall.getArgumentList().getExpressions().length != 1) {
                return null;
            }
            PsiMethod method = (PsiMethod) methodCall.getMethodExpression().resolve();
            if (method == null || method.getContainingClass() == null
                    || method.getContainingClass().getQualifiedName() == null
                    || !"java.util.HashSet".equals(method.getContainingClass().getQualifiedName())) {
                return null;
            }
            arguments.add(methodCall.getArgumentList().getExpressions()[0]);
        }
        return arguments;
    }

    @Nullable
    public static Expression getConstructorExpression(@NotNull PsiElement parent, @NotNull PsiLiteralExpression argument, @NotNull String classQualifiedNameNoGenerics) {
        Expression literalExpression = LiteralExpressionExt.getLiteralExpression(argument);
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
