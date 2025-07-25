package com.intellij.advancedExpressionFolding.processor.expression;

import com.intellij.advancedExpressionFolding.expression.operation.basic.TypeCast;
import com.intellij.advancedExpressionFolding.processor.BaseExtension;
import com.intellij.advancedExpressionFolding.processor.BuildExpressionExt;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiTypeCastExpression;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PsiTypeCastExpressionExt extends BaseExtension {

    @Nullable
    public static TypeCast getTypeCastExpression(@NotNull PsiTypeCastExpression expression, @NotNull Document document) {
        PsiExpression operand = expression.getOperand();
        return operand != null
                ? new TypeCast(expression, expression.getTextRange(), BuildExpressionExt.getAnyExpression(operand, document))
                : null;
    }
}
