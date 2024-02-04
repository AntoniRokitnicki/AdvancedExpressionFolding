package com.intellij.advancedExpressionFolding;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AdvancedExpressionFoldingBuilder extends FoldingBuilderEx {

    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, boolean quick) {
        return BuildExpressionExt.collectFoldRegionsRecursively(element, document, quick);
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode astNode) {
        return null;
    }

    // TODO: Collapse everything by default but use these settings when actually building the folding descriptors
    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        try {
            PsiElement element = astNode.getPsi();
            @Nullable Document document = PsiDocumentManager.getInstance(astNode.getPsi().getProject()).getDocument(astNode.getPsi().getContainingFile());
            if (document != null) {
                @Nullable Expression expression = BuildExpressionExt.getNonSyntheticExpression(element, document);
                return expression != null && expression.isCollapsedByDefault();
            }
        } catch (IndexNotReadyException e) {
            return false;
        }
        return false;
    }

}
