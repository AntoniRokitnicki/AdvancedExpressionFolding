package com.intellij.advancedExpressionFolding.processor.methodcall;

import com.intellij.advancedExpressionFolding.MethodCallFoldingLoaderService;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.property.Getter;
import com.intellij.advancedExpressionFolding.expression.property.GetterRecord;
import com.intellij.advancedExpressionFolding.expression.property.Setter;
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt;
import com.intellij.advancedExpressionFolding.processor.language.FieldShiftExt;
import com.intellij.advancedExpressionFolding.processor.logger.LoggerBracketsExt;
import com.intellij.advancedExpressionFolding.processor.util.Helper;
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static com.intellij.advancedExpressionFolding.processor.util.PropertyUtil.guessPropertyName;

public class MethodCallExpressionExt {

    @Nullable
    public static Expression getMethodCallExpression(PsiMethodCallExpression element, @NotNull Document document) {
        @NotNull AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        PsiReferenceExpression referenceExpression = element.getMethodExpression();
        Optional<PsiElement> identifierOpt = Stream.of(referenceExpression.getChildren())
                .filter(c -> c instanceof PsiIdentifier).findAny();
        if (identifierOpt.isEmpty()) {
            return null;
        }
        PsiElement identifier = identifierOpt.orElseThrow();
        @Nullable PsiExpression qualifier = element.getMethodExpression().getQualifierExpression();

        Expression shiftExpr = FieldShiftExt.createExpression(element, document, qualifier);
        if (shiftExpr != null) {
            return shiftExpr;
        }

        var factoryResult = useMethodCallFactory(identifier, referenceExpression, document, qualifier, element);
        if (factoryResult != null) {
            return factoryResult;
        }

        return onAnyArguments(element, settings, document, identifier, qualifier, referenceExpression);
    }

    @Nullable
    private static Expression useMethodCallFactory(PsiElement identifier, PsiReferenceExpression referenceExpression, @NotNull Document document,
                                                   @Nullable PsiExpression qualifier, PsiMethodCallExpression element) {
        var factory = MethodCallFoldingLoaderService.factory();
        if (factory.getSupportedMethods().contains(identifier.getText())) {
            PsiMethod method = (PsiMethod) referenceExpression.resolve();
            if (method != null) {
                PsiClass psiClass = method.getContainingClass();
                if (psiClass != null && psiClass.getQualifiedName() != null) {
                    String className = Helper.eraseGenerics(psiClass.getQualifiedName());
                    if ((factory.getSupportedClasses().contains(className) || factory.getClasslessMethods().contains(method.getName()))) {
                        return onAnyExpression(element, document, qualifier, identifier, className, method);
                    }
                }
            }
        }
        return null;
    }

    private static @Nullable Expression onAnyExpression(PsiMethodCallExpression element, @NotNull Document document, @Nullable PsiExpression qualifier, PsiElement identifier, String className, PsiMethod method) {
        @Nullable Expression qualifierExpression;
        if (qualifier != null) {
            qualifierExpression = BuildExpressionExt.getAnyExpression(qualifier, document);
        } else {
            qualifierExpression = null;
        }
        String methodName = identifier.getText();
        var factory = MethodCallFoldingLoaderService.factory();
        var methodCalls = factory.findByMethodName(methodName);
        if (methodCalls != null) {
            for (AbstractMethodCall methodCall : methodCalls) {
                var args = Arrays.stream(element.getArgumentList().getExpressions()).map(arg -> BuildExpressionExt.getAnyExpression(arg, document)).toList();
                var context = new Context(methodName, className, qualifierExpression, method, document, identifier, args);
                var expression = methodCall.execute(element, context);
                if (expression != null) {
                    return expression;
                }
            }
        }
        return null;
    }

    private static Expression onAnyArguments(PsiMethodCallExpression element, AdvancedExpressionFoldingSettings settings, Document document, PsiElement identifier, PsiExpression qualifier, PsiReferenceExpression referenceExpression) {
        if (settings.getState().getGetSetExpressionsCollapse()) {
            var result = onGetterSetter(element, document, identifier, qualifier);
            if (result != null) {
                return result;
            }
        }
        if (referenceExpression.resolve() instanceof PsiMethod psiMethod) {
            PsiClass psiClass = psiMethod.getContainingClass();
            var result = onGetterRecord(element, settings, document, psiClass, qualifier, identifier);
            if (result != null) {
                return result;
            }
        }
        String text = identifier.getText();
        return LoggerBracketsExt.createExpression(element, text, document);
    }

    private static Expression onGetterRecord(PsiMethodCallExpression element, AdvancedExpressionFoldingSettings settings, Document document, PsiClass psiClass, PsiExpression qualifier, PsiElement identifier) {
        if (psiClass != null && psiClass.isRecord() && element.getArgumentList().getExpressionCount() == 0) {
            if (settings.getState().getGetSetExpressionsCollapse()) {
                Expression expression = qualifier != null
                        ? BuildExpressionExt.getAnyExpression(qualifier, document)
                        : null;
                return new GetterRecord(element, element.getTextRange(), TextRange.create(identifier.getTextRange().getStartOffset(),
                        element.getTextRange().getEndOffset()),
                        expression,
                        guessPropertyName(identifier.getText()));
            }
        }
        return null;
    }

    private static Expression onGetterSetter(PsiMethodCallExpression element, Document document, PsiElement identifier, @Nullable PsiExpression qualifier) {
        if (Helper.isGetter(identifier, element)) {
            Expression expression = qualifier != null
                    ? BuildExpressionExt.getAnyExpression(qualifier, document)
                    : null;
            return new Getter(element, element.getTextRange(), TextRange.create(identifier.getTextRange().getStartOffset(),
                    element.getTextRange().getEndOffset()),
                    expression,
                    guessPropertyName(identifier.getText()));
        } else {
            String text = identifier.getText();
            if (isSimpleSetter(text, element, qualifier)) {
                Expression qualifierExpression = qualifier != null ? BuildExpressionExt.getAnyExpression(qualifier, document) : null;
                Expression paramExpression = BuildExpressionExt.getAnyExpression(element.getArgumentList().getExpressions()[0], document);
                String propertyName = guessPropertyName(text);
                return new Setter(element, element.getTextRange(), TextRange.create(identifier.getTextRange().getStartOffset(),
                        element.getTextRange().getEndOffset()),
                        qualifierExpression,
                        propertyName,
                        paramExpression);
            }
        }
        return null;
    }

    private static boolean isSimpleSetter(String text, PsiMethodCallExpression element, PsiExpression qualifier) {
        return Helper.isSetter(text)
                && element.getArgumentList().getExpressions().length == 1
                && element.getParent() instanceof PsiStatement
                && (!(qualifier instanceof PsiMethodCallExpression)
                || !Helper.startsWith(((PsiMethodCallExpression) qualifier).getMethodExpression().getReferenceName(), "set"));
    }


}
