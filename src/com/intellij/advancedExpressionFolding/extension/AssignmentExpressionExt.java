package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.expression.*;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiAssignmentExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;


public class AssignmentExpressionExt {
    @Nullable
    static Expression getAssignmentExpression(PsiAssignmentExpression element, @Nullable Document document) {
        Variable leftVariable = getVariableExpression(element.getLExpression());
        if (leftVariable != null && element.getRExpression() != null) {
            @NotNull Expression leftExpression = BuildExpressionExt.getAnyExpression(element.getRExpression(), document);
            if (leftExpression instanceof Operation operation) {
                if (operation.getOperands().size() >= 2 && operation.getOperands().get(0).equals(leftVariable)) {
                    Expression first = first(operation);
                    if (operation instanceof Add) {
                        return new AddAssign(element, element.getTextRange(),
                                asList(leftVariable, atLeastTwoOperands(operation) ?
                                        new Add(element, getTextRange(operation), getOperands(operation)) : first));
                    } else if (operation instanceof Subtract) {
                        return new SubtractAssign(element, element.getTextRange(),
                                asList(leftVariable, atLeastTwoOperands(operation) ?
                                        new Add(element, getTextRange(operation), getOperands(operation)) : first));
                    } else if (operation instanceof And) {
                        return new AndAssign(element, element.getTextRange(),
                                asList(leftVariable, atLeastTwoOperands(operation) ?
                                        new And(element, getTextRange(operation), getOperands(operation)) : first));
                    } else if (operation instanceof Or) {
                        return new AndAssign(element, element.getTextRange(),
                                asList(leftVariable, atLeastTwoOperands(operation) ?
                                        new Or(element, getTextRange(operation), getOperands(operation)) : first));
                    } else if (operation instanceof Xor) {
                        return new AndAssign(element, element.getTextRange(),
                                asList(leftVariable, atLeastTwoOperands(operation) ?
                                        new Xor(element, getTextRange(operation), getOperands(operation)) : first));
                    } else if (operation instanceof Multiply) {
                        return new MultiplyAssign(element, element.getTextRange(),
                                asList(leftVariable, atLeastTwoOperands(operation) ?
                                        new Multiply(element, getTextRange(operation), getOperands(operation)) : first));
                    } else if (operation instanceof Divide) {
                        return new DivideAssign(element, element.getTextRange(),
                                asList(leftVariable, atLeastTwoOperands(operation) ?
                                        new Multiply(element, getTextRange(operation), getOperands(operation)) : first));
                    } else if (operation instanceof ShiftRight && twoOperands(operation)) {
                        return new ShiftRightAssign(element, element.getTextRange(),
                                asList(leftVariable, first));
                    } else if (operation instanceof ShiftLeft && twoOperands(operation)) {
                        return new ShiftLeftAssign(element, element.getTextRange(),
                                asList(leftVariable, first));
                    } else if (operation instanceof Remainder && twoOperands(operation)) {
                        return new RemainderAssign(element, element.getTextRange(),
                                asList(leftVariable, first));
                    }
                }
            }
        }
        return null;
    }

    private static boolean twoOperands(Operation operation) {
        return operation.getOperands().size() == 2;
    }

    private static boolean atLeastTwoOperands(Operation operation) {
        return operation.getOperands().size() > 2;
    }

    private static Expression first(Operation operation) {
        return operation.getOperands().get(1);
    }

    private static @NotNull List<Expression> getOperands(Operation operation) {
        return operation.getOperands().subList(1, operation.getOperands().size());
    }

    private static @NotNull TextRange getTextRange(Operation operation) {
        return TextRange.create(first(operation).getTextRange().getStartOffset(),
                operation.getOperands().get(operation.getOperands().size() - 1).getTextRange().getEndOffset());
    }

    @Nullable
    static Variable getVariableExpression(PsiElement element) {
        return getVariableExpression(element, false);
    }

    @Nullable
    static Variable getVariableExpression(PsiElement element, boolean copy) {
        PsiReference reference = element.getReference();
        if (reference != null) {
            PsiElement e = reference.resolve();
            if (e instanceof PsiVariable variable && variable.getName() != null
                    && Objects.equals(variable.getName(), element.getText())) {
                String name = variable.getName();
                if (name != null) {
                    // TODO: Please make sure this fix works
                    /*if (supportedClasses.contains(eraseGenerics(variable.getType().getCanonicalText()))) {
                        return new Variable(element, element.getTextRange(), null, name, copy);
                    } else if (supportedPrimitiveTypes
                            .contains(eraseGenerics(variable.getType().getCanonicalText()))) {*/
                    return new Variable(element, element.getTextRange(), null, name, copy);
                    /*}*/
                }
            }
        }
        return null;
    }
}
