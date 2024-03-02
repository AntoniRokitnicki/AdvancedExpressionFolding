package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings;
import com.intellij.advancedExpressionFolding.expression.ControlFlowMultiStatementCodeBlockExpression;
import com.intellij.advancedExpressionFolding.expression.ControlFlowSingleStatementCodeBlockExpression;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.IfExpression;
import com.intellij.psi.*;
import org.jetbrains.annotations.Nullable;

public class PsiCodeBlockExt extends BaseExtension {

    @Nullable
    static Expression getCodeBlockExpression(PsiCodeBlock element) {
        PsiElement parent = element.getParent();
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (parent instanceof PsiBlockStatement
                && (
                (parent.getParent() instanceof PsiIfStatement || parent.getParent() instanceof PsiLoopStatement)
                        && element.getRBrace() != null
                        && element.getLBrace() != null
        )
                || parent instanceof PsiSwitchStatement
                || parent instanceof PsiTryStatement
                || parent instanceof PsiCatchSection) {
            if (element.getStatements().length == 1 || parent instanceof PsiSwitchStatement) {
                if (settings.getState().getControlFlowSingleStatementCodeBlockCollapse()
                        && !element.isWritable()
                        &&
                        (!(parent.getParent() instanceof PsiIfStatement) ||
                                !IfExpression.isAssertExpression(settings.getState(),
                                        (PsiIfStatement) parent.getParent()))) {
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
}
