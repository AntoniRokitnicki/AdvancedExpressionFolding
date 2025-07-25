package com.intellij.advancedExpressionFolding.processor.expression;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.math.basic.Negate;
import com.intellij.advancedExpressionFolding.expression.math.basic.NotEqual;
import com.intellij.advancedExpressionFolding.expression.operation.basic.Append;
import com.intellij.advancedExpressionFolding.expression.operation.basic.Equal;
import com.intellij.advancedExpressionFolding.expression.operation.basic.GreaterEqual;
import com.intellij.advancedExpressionFolding.processor.util.Helper;
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.intellij.advancedExpressionFolding.processor.BuildExpressionExt.getAnyExpression;

public class PrefixExpressionExt {

    @Nullable
    static Expression getPrefixExpression(@NotNull PsiPrefixExpression element, @NotNull Document document) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (element.getOperand() != null) {
            if (element.getOperationSign().getText().equals("!")) {
                if (settings.getState().getComparingLocalDatesCollapse()) {
                    if (element.getOperand() instanceof PsiMethodCallExpression operand) {
                        Optional<MethodCallInformation> methodCallInformationOptional = MethodCallInformation.tryGet(operand, document, "isBefore", "isAfter", "before", "after");
                        if (methodCallInformationOptional.isPresent()) {
                            MethodCallInformation callInformation = methodCallInformationOptional.get();

                            String methodName = callInformation.methodName;
                            if (methodName.equals("isBefore") || methodName.equals("before")) {
                                return new GreaterEqual(element, element.getTextRange(), Arrays.asList(callInformation.qualifierExpression, callInformation.getFoldedArgument(0)));
                            }

                            if (methodName.equals("isAfter") || methodName.equals("after")) {
                                return new Append.LessEqual(element, element.getTextRange(), Arrays.asList(callInformation.qualifierExpression, callInformation.getFoldedArgument(0)));
                            }
                        }

                    }
                }
                if (settings.getState().getComparingExpressionsCollapse()) {
                    @NotNull Expression operand = getAnyExpression(element.getOperand(), document);
                    if (operand instanceof Equal) {
                        return new NotEqual(element, element.getTextRange(), ((Equal) operand).getOperands());
                    }
                } else if (element.getOperationSign().getText().equals("-")) {
                    @NotNull Expression operand = getAnyExpression(element.getOperand(), document);
                    return new Negate(element, element.getTextRange(), Collections.singletonList(operand));
                }
            } else if (element.getOperationSign().getText().equals("-")) {
                @NotNull Expression operand = getAnyExpression(element.getOperand(), document);
                return new Negate(element, element.getTextRange(), Collections.singletonList(operand));
            }
        }
        return null;
    }

    private static class MethodCallInformation {
        private final PsiMethodCallExpression element;
        private final Expression qualifierExpression;
        private final PsiExpression[] arguments;
        private final Document document;
        private final String methodName;
        private final String className;
        private final PsiClass psiClass;

        public MethodCallInformation(PsiMethodCallExpression element, Expression qualifierExpression, String methodName, String className, PsiClass psiClass, Document document) {
            this.element = element;
            this.qualifierExpression = qualifierExpression;
            this.methodName = methodName;
            this.className = className;
            this.psiClass = psiClass;
            this.arguments = element.getArgumentList().getExpressions();
            this.document = document;
        }

        Expression getFoldedArgument(int index) {
            return getAnyExpression(element.getArgumentList().getExpressions()[index], document);
        }

        static Optional<MethodCallInformation> tryGet(PsiMethodCallExpression element, @NotNull Document document, String... methodNames) {
            return tryGet(element, document, Arrays.asList(methodNames)::contains, (actualClassName, methodName) -> true);
        }

        static Optional<MethodCallInformation> tryGet(PsiMethodCallExpression element, @NotNull Document document, Predicate<String> isMethodNameSupported, BiPredicate<String, String> isMethodSupported) {
            PsiReferenceExpression referenceExpression = element.getMethodExpression();
            Optional<PsiElement> identifier = Stream.of(referenceExpression.getChildren())
                    .filter(c -> c instanceof PsiIdentifier).findAny();
            @Nullable PsiExpression qualifier = element.getMethodExpression().getQualifierExpression();

            if (identifier.isPresent() && isMethodNameSupported.test(identifier.get().getText())) {
                PsiMethod method = (PsiMethod) referenceExpression.resolve();
                if (method != null) {
                    PsiClass psiClass = method.getContainingClass();
                    if (psiClass != null && psiClass.getQualifiedName() != null) {
                        String className = Helper.eraseGenerics(psiClass.getQualifiedName());
                        String methodName = identifier.get().getText();
                        if (isMethodSupported.test(className, methodName)
                                && qualifier != null) {
                            @NotNull Expression qualifierExpression = getAnyExpression(qualifier, document);
                            return Optional.of(new MethodCallInformation(element, qualifierExpression, methodName, className, psiClass, document));
                        }
                    }
                }
            }
            return Optional.empty();
        }
    }
}
