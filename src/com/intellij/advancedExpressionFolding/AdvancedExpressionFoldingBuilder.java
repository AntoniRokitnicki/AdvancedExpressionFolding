package com.intellij.advancedExpressionFolding;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.extension.BuildExpressionExt;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdvancedExpressionFoldingBuilder extends FoldingBuilderEx {

    @NotNull
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement element, @NotNull Document document, boolean quick) {
        if (!AdvancedExpressionFoldingSettings.getInstance().getState().getGlobalOn()) {
            return Expression.EMPTY_ARRAY;
        }

        //preview(element, document, quick)
        return BuildExpressionExt.collectFoldRegionsRecursively(element, document, quick);
    }

    public List<String> preview(@NotNull PsiElement element, @NotNull Document document, boolean quick) {
        FoldingDescriptor[] foldingDescriptors = BuildExpressionExt.collectFoldRegionsRecursively(element, document, quick);
        return Arrays.stream(foldingDescriptors).map(it -> it.getRange().substring(document.getText())
                + " => "
                + it.getPlaceholderText()
                + "[" +
                it.getGroup()
                + "]").collect(Collectors.toList());
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
