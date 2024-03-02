package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings;
import com.intellij.advancedExpressionFolding.expression.CompactControlFlowExpression;
import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDoWhileStatement;
import com.intellij.psi.PsiForeachStatement;
import com.intellij.psi.PsiWhileStatement;

public class LoopExt {
    static Expression getForeachStatementExpression(PsiForeachStatement element) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (element.getIteratedValue() != null && element.getRParenth() != null &&
                settings.getState().getCompactControlFlowSyntaxCollapse()) {
            return new CompactControlFlowExpression(element,
                    TextRange.create(element.getLParenth().getTextRange().getStartOffset(),
                            element.getRParenth().getTextRange().getEndOffset()));
        }
        return null;
    }

    static Expression getWhileStatement(PsiWhileStatement element) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (element.getCondition() != null
                && element.getLParenth() != null && element.getRParenth() != null
                && settings.getState().getCompactControlFlowSyntaxCollapse()) {
            return new CompactControlFlowExpression(element,
                    TextRange.create(element.getLParenth().getTextRange().getStartOffset(),
                            element.getRParenth().getTextRange().getEndOffset()));
        }
        return null;
    }

    static Expression getDoWhileStatement(PsiDoWhileStatement element) {
        AdvancedExpressionFoldingSettings settings = AdvancedExpressionFoldingSettings.getInstance();
        if (element.getCondition() != null
                && element.getLParenth() != null && element.getRParenth() != null
                && settings.getState().getCompactControlFlowSyntaxCollapse()) {
            return new CompactControlFlowExpression(element,
                    TextRange.create(element.getLParenth().getTextRange().getStartOffset(),
                            element.getRParenth().getTextRange().getEndOffset()));
        }
        return null;
    }
}
