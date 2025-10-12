package com.intellij.advancedExpressionFolding.processor.declaration;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.controlflow.ControlFlowMultiStatementCodeBlockExpression;
import com.intellij.advancedExpressionFolding.expression.controlflow.ControlFlowSingleStatementCodeBlockExpression;
import com.intellij.advancedExpressionFolding.expression.controlflow.IfExpression;
import com.intellij.advancedExpressionFolding.processor.core.BaseExtension;
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings;
import com.intellij.psi.*;
import org.jetbrains.annotations.Nullable;

public class PsiCodeBlockExt extends BaseExtension {

    @Nullable
    public static Expression getCodeBlockExpression(PsiCodeBlock element) {
        PsiElement parent = element.getParent();
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (isSupportedParent(parent, element)) {
            if (element.getStatements().length == 1 || parent instanceof PsiSwitchStatement) {
                if (shouldCollapseSingleStatement(element, parent, settings)) {
                    return new ControlFlowSingleStatementCodeBlockExpression(element, element.getTextRange());
                }
            } else {
                if (settings.getState().getControlFlowMultiStatementCodeBlockCollapse()
                        && !element.isWritable()) {
                    //noinspection deprecation
                    return new ControlFlowMultiStatementCodeBlockExpression(element, element.getTextRange());
                }
            }
        }
        return null;
    }

    private static boolean isSupportedParent(PsiElement parent, PsiCodeBlock element) {
        return parent instanceof PsiBlockStatement
                && ((parent.getParent() instanceof PsiIfStatement
                || parent.getParent() instanceof PsiLoopStatement)
                && element.getRBrace() != null
                && element.getLBrace() != null)
                || parent instanceof PsiSwitchStatement
                || parent instanceof PsiTryStatement
                || parent instanceof PsiCatchSection;
    }

    private static boolean shouldCollapseSingleStatement(
            PsiCodeBlock element, PsiElement parent, AdvancedExpressionFoldingSettings settings) {
        return settings.getState().getControlFlowSingleStatementCodeBlockCollapse()
                && !element.isWritable()
                && (!(parent.getParent() instanceof PsiIfStatement)
                || !IfExpression.isAssertExpression(
                settings.getState(), (PsiIfStatement) parent.getParent()));
    }
}
