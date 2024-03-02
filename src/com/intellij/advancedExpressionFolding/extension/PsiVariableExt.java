package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings;
import com.intellij.advancedExpressionFolding.expression.VariableDeclarationImpl;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiForeachStatement;
import com.intellij.psi.PsiVariable;
import org.jetbrains.annotations.Nullable;

public class PsiVariableExt extends BaseExtension {

    @Nullable
    static VariableDeclarationImpl getVariableDeclaration(PsiVariable element) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (settings.getState().getVarExpressionsCollapse()
                && element.getName() != null
                && element.getTypeElement() != null
                && (element.getInitializer() != null || element.getParent() instanceof PsiForeachStatement)
                && element.getTextRange().getStartOffset() < element.getTypeElement().getTextRange().getEndOffset()) {
            boolean isFinal = Helper.calculateIfFinal(element);
            return new VariableDeclarationImpl(element, TextRange.create(
                    element.getTextRange().getStartOffset(),
                    element.getTypeElement().getTextRange().getEndOffset()),
                    element.getModifierList() != null && isFinal);
        }
        return null;
    }
}