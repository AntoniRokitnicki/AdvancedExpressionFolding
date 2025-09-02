package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.SyntheticExpressionImpl
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.psi.PsiElement
import org.jetbrains.annotations.Contract
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.ArrayList
import com.intellij.advancedExpressionFolding.processor.cache.CacheExt.getExpression

object BuildExpressionExt {
    @JvmStatic
    @Contract("_, _, true -> !null")
    fun buildExpression(@NotNull element: PsiElement, @NotNull document: Document, synthetic: Boolean): Expression? {
        var expression: Expression? = tryBuildExpression(element, document, synthetic)
        if (expression != null) {
            return expression
        }
        if (synthetic) {
            val children = ArrayList<Expression>()
            Helper.findChildExpressions(element, children, document)
            return SyntheticExpressionImpl(element, element.getTextRange(), document.getText(element.getTextRange()), children)
        }
        return null
    }

    @JvmStatic
    @SuppressWarnings("WeakerAccess")
    @NotNull
    @Throws(IndexNotReadyException::class)
    fun getAnyExpression(@NotNull element: PsiElement, @Nullable document: Document?): Expression {
        //noinspection ConstantConditions
        return getExpression(element, document!!, true)!!
    }

    /**
     * TODO: Think how we can prevent IndexNotReadyException (e.g. via "is dumb mode")
     */
    @JvmStatic
    @SuppressWarnings("WeakerAccess")
    @Nullable
    @Throws(IndexNotReadyException::class)
    fun getNonSyntheticExpression(@NotNull element: PsiElement, @Nullable document: Document?): Expression? {
        //noinspection ConstantConditions
        return getExpression(element, document!!, false)
    }

    @JvmStatic
    fun collectFoldRegionsRecursively(@NotNull element: PsiElement, @NotNull document: Document, uniqueSet: MutableSet<Expression?>, allDescriptors: MutableList<FoldingDescriptor>) {
        @Nullable var expression: Expression? = getNonSyntheticExpression(element, document)
        val unique: Boolean = uniqueSet.add(expression)
        if (expression != null && unique && expression.supportsFoldRegions(document, null)) {
            //TODO: add to allDescriptors list instead of creating temporary arrays
            val descriptors: Array<FoldingDescriptor> = expression.buildFoldRegions(expression.getElement(), document, null)
            if (descriptors.size > 0) {
                for (descriptor in descriptors) {
                    allDescriptors.add(descriptor)
                }
            }
        }
        if (expression == null || unique && expression.isNested()) {
            for (child in element.getChildren()) {
                collectFoldRegionsRecursively(child, document, uniqueSet, allDescriptors)
            }
        }
    }
}

