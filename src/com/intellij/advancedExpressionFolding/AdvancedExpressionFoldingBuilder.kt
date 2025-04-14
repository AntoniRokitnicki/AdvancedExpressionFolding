package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IConfig
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.extension.BuildExpressionExt
import com.intellij.advancedExpressionFolding.extension.CacheExt.isExpired
import com.intellij.advancedExpressionFolding.extension.Keys
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

class AdvancedExpressionFoldingBuilder(private val config: IConfig = getInstance().state) : FoldingBuilderEx(), IConfig by config {
    override fun buildFoldRegions(element: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        if (!globalOn) {
            return Expression.EMPTY_ARRAY
        }
        //preview(element, document, quick)

        var descriptors: Array<FoldingDescriptor>? = null

        if (memoryImprovement) {
            val file  = element as? PsiJavaFile
            if (file != null && !quick) {
                if (file.isExpired(document, false)) {
                    descriptors = collect(element, document, false)
                    if (descriptors.isNotEmpty()) {
                        file.putUserData(Keys.FULL_CACHE, descriptors)
                    }
                } else {
                    descriptors = file.getUserData(Keys.FULL_CACHE)
                }
            }
        }
        val foldingDescriptors = descriptors ?: collect(element, document, quick)
        if (memoryImprovement) {
            (element as? PsiJavaFile)?.run {
                putUserData(Keys.FULL_CACHE, foldingDescriptors)
            }
        }
        return store.store(foldingDescriptors, document)
    }

    @Suppress("unused")
    fun preview(element: PsiElement, document: Document, quick: Boolean): List<String> {
        val foldingDescriptors = collect(element, document, quick)
        return Arrays.stream(foldingDescriptors).map { it: FoldingDescriptor ->
            (it.range.substring(document.text)
                    + " => "
                    + it.placeholderText
                    + "[" +
                    it.group
                    + "]")
        }.collect(Collectors.toList())
    }

    private fun collect(
        element: PsiElement,
        document: Document,
        quick: Boolean
    ): Array<FoldingDescriptor> {
        return BuildExpressionExt.collectFoldRegionsRecursively(element, document, quick)
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