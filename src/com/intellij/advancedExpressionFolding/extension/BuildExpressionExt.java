package com.intellij.advancedExpressionFolding.extension;

import com.intellij.advancedExpressionFolding.expression.Expression;
import com.intellij.advancedExpressionFolding.expression.SyntheticExpressionImpl;
import com.intellij.advancedExpressionFolding.extension.util.Helper;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.intellij.advancedExpressionFolding.extension.BuildExpressionKt.tryBuildExpression;
import static com.intellij.advancedExpressionFolding.extension.cache.CacheExt.getExpression;

public class BuildExpressionExt {

    @Contract("_, _, true -> !null")
    public static Expression buildExpression(@NotNull PsiElement element, @NotNull Document document, boolean synthetic) {
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

    public static void collectFoldRegionsRecursively(@NotNull PsiElement element, @NotNull Document document, Set<Expression> uniqueSet, List<FoldingDescriptor> allDescriptors) {
        @Nullable Expression expression = getNonSyntheticExpression(element, document);

        boolean unique = uniqueSet.add(expression);
        if (expression != null && unique && expression.supportsFoldRegions(document, null)) {
            //TODO: add to allDescriptors list instead of creating temporary arrays
            FoldingDescriptor[] descriptors = expression.buildFoldRegions(expression.getElement(), document, null);
            if (descriptors.length > 0) {
                allDescriptors.addAll(Arrays.asList(descriptors));
            }
        }
        if (expression == null || (unique && expression.isNested())) {
            for (PsiElement child : element.getChildren()) {
                collectFoldRegionsRecursively(child, document, uniqueSet, allDescriptors);
            }
        }
    }

}
