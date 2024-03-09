package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.expression.CharacterLiteral;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.NumberLiteral;
import com.intellij.advancedExpressionFolding.expression.StringLiteral;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LiteralExpressionExt {
    @Nullable
    public static Expression getLiteralExpression(@NotNull PsiLiteralExpression element) {
        if (element.getType() != null) {
            if (Consts.SUPPORTED_PRIMITIVE_TYPES.contains(element.getType().getCanonicalText())) {
                Object value = element.getValue();
                if (value instanceof Number) {
                    return new NumberLiteral(element, element.getTextRange(), null, (Number) value, false);
                } else if (value instanceof String) {
                    return new StringLiteral(element, element.getTextRange(), (String) value);
                } else if (value instanceof Character) {
                    return new CharacterLiteral(element, element.getTextRange(), (Character) value);
                }
            }
        }
        return null;
    }
}
