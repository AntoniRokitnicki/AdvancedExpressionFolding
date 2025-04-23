package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.SyntheticExpressionImpl;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.intellij.advancedExpressionFolding.extension.BuildExpressionKt.tryBuildExpression;
import static com.intellij.advancedExpressionFolding.extension.CacheExt.getExpression;

public class BuildExpressionExt {

    static final FoldingDescriptor[] NO_DESCRIPTORS = new FoldingDescriptor[0];

    @Contract("_, _, true -> !null")
    static Expression buildExpression(@NotNull PsiElement element, @NotNull Document document, boolean synthetic) {
        var expression = tryBuildExpression(element, document, synthetic);
        if (expression!= null) {
            return expression;
        }

        if (synthetic) {
            ArrayList<Expression> children = new ArrayList<>();
            Helper.findChildExpressions(element, children, document);
            return new SyntheticExpressionImpl(element, element.getTextRange(), document.getText(element.getTextRange()), children);
        }
        return null;
    }

    @SuppressWarnings("WeakerAccess")
    @NotNull
    public static Expression getAnyExpression(@NotNull PsiElement element, @Nullable Document document) throws IndexNotReadyException {
        //noinspection ConstantConditions
        return getExpression(element, document, true);
    }

    /**
     * TODO: Think how we can prevent IndexNotReadyException (e.g. via "is dumb mode")
     */
    @SuppressWarnings("WeakerAccess")
    @Nullable
    public static Expression getNonSyntheticExpression(@NotNull PsiElement element, @Nullable Document document) throws IndexNotReadyException {
        //noinspection ConstantConditions
        return getExpression(element, document, false);
    }

    @SuppressWarnings({"unused", "UnusedAssignment"})
    @NotNull
    public static FoldingDescriptor @NotNull [] collectFoldRegionsRecursively(@NotNull PsiElement element, @NotNull Document document, boolean quick, Set<Expression> uniqueSet) {
        PsiElement lastElement = element;
        List<FoldingDescriptor> allDescriptors = null;
        @Nullable Expression expression = getNonSyntheticExpression(element, document);

        if (expression != null && uniqueSet.add(expression) && expression.supportsFoldRegions(document, null)) {
            FoldingDescriptor[] descriptors = expression.buildFoldRegions(expression.getElement(), document, null);
            allDescriptors = new ArrayList<>();
            Collections.addAll(allDescriptors, descriptors);
        }
        if (expression == null || expression.isNested()) {
            for (PsiElement child : element.getChildren()) {
                lastElement = child;
                FoldingDescriptor[] descriptors = collectFoldRegionsRecursively(child, document, quick, uniqueSet);
                if (descriptors.length > 0) {
                    if (allDescriptors == null) {
                        allDescriptors = new ArrayList<>();
                    }
                    Collections.addAll(allDescriptors, descriptors);
                }
            }
        }
        return allDescriptors != null ? allDescriptors.toArray(NO_DESCRIPTORS) : NO_DESCRIPTORS;
    }

}
