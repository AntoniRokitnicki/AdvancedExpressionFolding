package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.BuildExpressionExt
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaFile
import java.util.*
import java.util.stream.Collectors

class AdvancedExpressionFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(element: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        if (!getInstance().state.globalOn) {
            return Expression.EMPTY_ARRAY
        }

        if (element is PsiJavaFile) {
            document.hashCode()
        }


        //preview(element, document, quick)
        val foldingDescriptors = BuildExpressionExt.collectFoldRegionsRecursively(element, document, quick)

        return store.store(foldingDescriptors, document)
    }

    @Suppress("unused")
    fun preview(element: PsiElement, document: Document, quick: Boolean): List<String> {
        val foldingDescriptors = BuildExpressionExt.collectFoldRegionsRecursively(element, document, quick)
        return Arrays.stream(foldingDescriptors).map { it: FoldingDescriptor ->
            (it.range.substring(document.text)
                    + " => "
                    + it.placeholderText
                    + "[" +
                    it.group
                    + "]")
        }.collect(Collectors.toList())
    }

    override fun getPlaceholderText(astNode: ASTNode): String? {
        return null
    }

    // TODO: Collapse everything by default but use these settings when actually building the folding descriptors
    override fun isCollapsedByDefault(astNode: ASTNode): Boolean {
        try {
            val element = astNode.psi
            val document = PsiDocumentManager.getInstance(astNode.psi.project).getDocument(astNode.psi.containingFile)
            if (document != null) {
                val expression = BuildExpressionExt.getNonSyntheticExpression(element, document)
                return expression != null && expression.isCollapsedByDefault
            }
        } catch (e: IndexNotReadyException) {
            return false
        }
        return false
    }

}

var store = EmptyStorage()