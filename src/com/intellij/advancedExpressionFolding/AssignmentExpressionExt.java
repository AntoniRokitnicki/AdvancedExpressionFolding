package com.intellij.advancedExpressionFolding;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiAssignmentExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiVariable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

public class AssignmentExpressionExt {
    @Nullable
    static Expression getAssignmentExpression(PsiAssignmentExpression element, @Nullable Document document) {
        Variable leftVariable = getVariableExpression(element.getLExpression());
        if (leftVariable != null && element.getRExpression() != null) {
            @NotNull Expression leftExpression = BuildExpressionExt.getAnyExpression(element.getRExpression(), document);
            if (leftExpression instanceof Operation) {
                Operation operation = (Operation) leftExpression;
                if (operation.getOperands().size() >= 2 && operation.getOperands().get(0).equals(leftVariable)) {
                    if (operation instanceof Add) {
                        return new AddAssign(element, element.getTextRange(),
                                Arrays.asList(leftVariable, operation.getOperands().size() > 2 ?
                                        new Add(element, TextRange.create(operation.getOperands().get(1).getTextRange().getStartOffset(),
                                                operation.getOperands().get(operation.getOperands().size() - 1).getTextRange().getEndOffset()), operation.getOperands()
                                                .subList(1, operation.getOperands().size())) : operation
                                        .getOperands().get(1)));
                    } else if (operation instanceof Subtract) {
                        return new SubtractAssign(element, element.getTextRange(),
                                Arrays.asList(leftVariable, operation.getOperands().size() > 2 ?
                                        new Add(element, TextRange.create(operation.getOperands().get(1).getTextRange().getStartOffset(),
                                                operation.getOperands().get(operation.getOperands().size() - 1).getTextRange().getEndOffset()), operation.getOperands()
                                                .subList(1, operation.getOperands().size())) : operation
                                        .getOperands().get(1)));
                    } else if (operation instanceof And) {
                        return new AndAssign(element, element.getTextRange(),
                                Arrays.asList(leftVariable, operation.getOperands().size() > 2 ?
                                        new And(element, TextRange.create(operation.getOperands().get(1).getTextRange().getStartOffset(),
                                                operation.getOperands().get(operation.getOperands().size() - 1).getTextRange().getEndOffset()), operation.getOperands()
                                                .subList(1, operation.getOperands().size())) : operation
                                        .getOperands().get(1)));
                    } else if (operation instanceof Or) {
                        return new AndAssign(element, element.getTextRange(),
                                Arrays.asList(leftVariable, operation.getOperands().size() > 2 ?
                                        new Or(element, TextRange.create(operation.getOperands().get(1).getTextRange().getStartOffset(),
                                                operation.getOperands().get(operation.getOperands().size() - 1).getTextRange().getEndOffset()), operation.getOperands()
                                                .subList(1, operation.getOperands().size())) : operation
                                        .getOperands().get(1)));
                    } else if (operation instanceof Xor) {
                        return new AndAssign(element, element.getTextRange(),
                                Arrays.asList(leftVariable, operation.getOperands().size() > 2 ?
                                        new Xor(element, TextRange.create(operation.getOperands().get(1).getTextRange().getStartOffset(),
                                                operation.getOperands().get(operation.getOperands().size() - 1).getTextRange().getEndOffset()), operation.getOperands()
                                                .subList(1, operation.getOperands().size())) : operation
                                        .getOperands().get(1)));
                    } else if (operation instanceof Multiply) {
                        return new MultiplyAssign(element, element.getTextRange(),
                                Arrays.asList(leftVariable, operation.getOperands().size() > 2 ?
                                        new Multiply(element, TextRange.create(operation.getOperands().get(1).getTextRange().getStartOffset(),
                                                operation.getOperands().get(operation.getOperands().size() - 1).getTextRange().getEndOffset()), operation.getOperands()
                                                .subList(1, operation.getOperands().size())) : operation
                                        .getOperands().get(1)));
                    } else if (operation instanceof Divide) {
                        return new DivideAssign(element, element.getTextRange(),
                                Arrays.asList(leftVariable, operation.getOperands().size() > 2 ?
                                        new Multiply(element, TextRange.create(operation.getOperands().get(1).getTextRange().getStartOffset(),
                                                operation.getOperands().get(operation.getOperands().size() - 1).getTextRange().getEndOffset()), operation.getOperands()
                                                .subList(1, operation.getOperands().size())) : operation
                                        .getOperands().get(1)));
                    } else if (operation instanceof ShiftRight && operation.getOperands().size() == 2) {
                        return new ShiftRightAssign(element, element.getTextRange(),
                                Arrays.asList(leftVariable, operation.getOperands().get(1)));
                    } else if (operation instanceof ShiftLeft && operation.getOperands().size() == 2) {
                        return new ShiftLeftAssign(element, element.getTextRange(),
                                Arrays.asList(leftVariable, operation.getOperands().get(1)));
                    } else if (operation instanceof Remainder && operation.getOperands().size() == 2) {
                        return new RemainderAssign(element, element.getTextRange(),
                                Arrays.asList(leftVariable, operation.getOperands().get(1)));
                    }
                }
            }
        }
        return null;
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
