package com.intellij.advancedExpressionFolding.processor.expression;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.literal.CharacterLiteral;
import com.intellij.advancedExpressionFolding.expression.literal.NumberLiteral;
import com.intellij.advancedExpressionFolding.expression.literal.StringLiteral;
import com.intellij.advancedExpressionFolding.processor.util.Consts;
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings;
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
                    if (!element.isTextBlock()) {
                        return new StringLiteral(element, element.getTextRange(), (String) value);
                    } else if (AdvancedExpressionFoldingSettings.getInstance().getState().getLogFoldingTextBlocks()) {
                        return new StringLiteral(element, element.getTextRange(), (String) value);
                    }
                } else if (value instanceof Character) {
                    return new CharacterLiteral(element, element.getTextRange(), (Character) value);
                }
            }
        }
        return null;
    }
}
