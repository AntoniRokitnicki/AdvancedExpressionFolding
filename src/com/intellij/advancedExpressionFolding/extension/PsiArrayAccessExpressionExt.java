
package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings;
import com.intellij.advancedExpressionFolding.expression.ArrayGet;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.NumberLiteral;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiArrayAccessExpression;
import com.intellij.psi.PsiAssignmentExpression;
import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.impl.source.tree.java.PsiAssignmentExpressionImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PsiArrayAccessExpressionExt extends BaseExtension {

    @Nullable
    static Expression getArrayAccessExpression(@NotNull PsiArrayAccessExpression element, @NotNull Document document) {
        @Nullable PsiExpression index = element.getIndexExpression();
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (!(element.getParent() instanceof PsiAssignmentExpression
                && ((PsiAssignmentExpressionImpl) element.getParent()).getLExpression() == element) && index != null && settings.getState().getGetExpressionsCollapse()) {
            @NotNull Expression arrayExpression = BuildExpressionExt.getAnyExpression(element.getArrayExpression(), document);
            if (index instanceof PsiBinaryExpression a2b) {
                NumberLiteral position = Helper.getSlicePosition(element, arrayExpression, a2b, document);
                if (position != null && position.getNumber().equals(-1)) {
                    return new ArrayGet(element, element.getTextRange(), arrayExpression);
                }
            }
        }
        return null;
    }
}