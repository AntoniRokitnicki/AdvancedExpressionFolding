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
import java.util.Arrays
import java.util.List
import java.util.Set

import com.intellij.advancedExpressionFolding.processor.cache.CacheExt.getExpression
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionKt.tryBuildExpression

class BuildExpressionExt private constructor() {
    companion object {
        @Contract("_, _, true -> !null")
        fun buildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? {
            var expression = tryBuildExpression(element, document, synthetic)
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

        @SuppressWarnings("WeakerAccess")
        @Throws(IndexNotReadyException::class)
        fun getAnyExpression(element: PsiElement, document: Document?): Expression {
            return getExpression(element, document, true)
        }

        /**
         * TODO: Think how we can prevent IndexNotReadyException (e.g. via "is dumb mode")
         */
        @SuppressWarnings("WeakerAccess")
        @Throws(IndexNotReadyException::class)
        fun getNonSyntheticExpression(element: PsiElement, document: Document?): Expression? {
            return getExpression(element, document, false)
        }

        fun collectFoldRegionsRecursively(element: PsiElement, document: Document, uniqueSet: Set<Expression?>, allDescriptors: List<FoldingDescriptor>) {
            val expression: Expression? = getNonSyntheticExpression(element, document)
            val unique = uniqueSet.add(expression)
            if (expression != null && unique && expression.supportsFoldRegions(document, null)) {
                val descriptors = expression.buildFoldRegions(expression.getElement(), document, null)
                if (descriptors.size > 0) {
                    allDescriptors.addAll(Arrays.asList(*descriptors))
                }
            }
            if (expression == null || unique && expression.isNested()) {
                for (child in element.getChildren()) {
                    collectFoldRegionsRecursively(child, document, uniqueSet, allDescriptors)
                }
            }
        }
    }
}
