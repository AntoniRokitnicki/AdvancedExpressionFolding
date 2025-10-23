package com.intellij.advancedExpressionFolding.processor.core

import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.expression.SyntheticExpressionImpl
import com.intellij.advancedExpressionFolding.processor.cache.CacheExt.getExpression
import com.intellij.advancedExpressionFolding.processor.util.Helper
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Document
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.DumbService
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.psi.PsiElement

object BuildExpressionExt {

    @JvmStatic
    fun buildExpression(element: PsiElement, document: Document, synthetic: Boolean): Expression? {
        val expression = tryBuildExpression(element, document, synthetic)
        if (expression != null) {
            return expression
        }
        if (synthetic) {
            val children = arrayListOf<Expression>()
            Helper.findChildExpressions(element, children, document)
            return SyntheticExpressionImpl(
                element,
                element.textRange,
                document.getText(element.textRange),
                children
            )
        }
        return null
    }

    @JvmStatic
    @Throws(IndexNotReadyException::class)
    fun getAnyExpression(element: PsiElement, document: Document?): Expression {
        return getExpression(element, document ?: element.containingFile.viewProvider.document, true)!!
    }

    @JvmStatic
    @Throws(IndexNotReadyException::class)
    fun getNonSyntheticExpression(element: PsiElement, document: Document?): Expression? {
        if (!ApplicationManager.getApplication().isUnitTestMode && DumbService.isDumb(element.project)) {
            return null
        }
        return try {
            getExpression(element, document ?: element.containingFile.viewProvider.document, false)
        } catch (e: IndexNotReadyException) {
            null
        }
    }

    @JvmStatic
    fun collectFoldRegionsRecursively(
        element: PsiElement,
        document: Document,
        uniqueSet: MutableSet<Expression>,
        allDescriptors: MutableList<FoldingDescriptor>
    ) {
        val expression = getNonSyntheticExpression(element, document)
        val unique = expression != null && uniqueSet.add(expression)
        if (expression != null && unique && expression.supportsFoldRegions(document, null)) {
            val descriptors = expression.buildFoldRegions(expression.element, document, null)
            if (descriptors.isNotEmpty()) {
                allDescriptors.addAll(descriptors)
            }
        }
        if (expression == null || (unique && expression.isNested())) {
            for (child in element.children) {
                ProgressManager.checkCanceled()
                collectFoldRegionsRecursively(child, document, uniqueSet, allDescriptors)
            }
        }
    }
}
