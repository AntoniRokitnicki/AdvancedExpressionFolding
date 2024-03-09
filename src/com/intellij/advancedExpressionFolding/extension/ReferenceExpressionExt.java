package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.NumberLiteral;
import com.intellij.advancedExpressionFolding.expression.Variable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiMethodReferenceExpression;
import com.intellij.psi.PsiReferenceExpression;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ReferenceExpressionExt {

    @Nullable
    public static Expression getReferenceExpression(PsiReferenceExpression element, boolean copy) {
        Optional<PsiElement> found = Optional.empty();
        for (PsiElement c : element.getChildren()) {
            if (c instanceof PsiIdentifier) {
                found = Optional.of(c);
                break;
            }
        }
        Optional<PsiElement> identifier = found;
        if (identifier.isPresent()) {
            Object constant = Consts.SUPPORTED_CONSTANTS.get(identifier.get().getText());
            if (constant != null) {
                if (Helper.isSupportedClass(element) && constant instanceof Number) {
                    return new NumberLiteral(element, element.getTextRange(), element.getTextRange(), (Number) constant, true);
                } else if (Helper.isSupportedClass(element) && constant instanceof String) {
                    return new Variable(element, element.getTextRange(), null, (String) constant, copy);
                }
            } else {
                Expression variable = AssignmentExpressionExt.getVariableExpression(element, copy);
                if (variable != null) return variable;

                if (element instanceof PsiMethodReferenceExpression methodReferenceExpression) {
                    var methodReference = MethodReferenceExt.createExpression(methodReferenceExpression);
                    if (methodReference != null) {
                        return methodReference;
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    public static Expression getReferenceExpression(PsiReferenceExpression element) {
        return getReferenceExpression(element, false);
    }
}
