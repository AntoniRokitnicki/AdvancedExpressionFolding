package com.intellij.advancedExpressionFolding

import com.google.common.collect.Lists
import com.google.common.collect.Sets
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.cache.CacheExt.invalidateExpired
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.IConfig
import com.intellij.advancedExpressionFolding.util.withDocument
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaFile

class AdvancedExpressionFoldingBuilder(private val config: IConfig = getInstance().state) : FoldingBuilderEx(), IConfig by config {
    override fun buildFoldRegions(element: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> =
        withDocument(document) {
            buildFoldRegionsWithDocument(element, quick)
        }

    context(editorDocument: Document)
    private fun buildFoldRegionsWithDocument(
        element: PsiElement,
        quick: Boolean,
    ): Array<FoldingDescriptor> {
        if (!globalOn || isFoldingFile(element)) {
            return store.store(Expression.EMPTY_ARRAY)
        }
        if (debugFolding) {
            preview(element)
        }

        val cachedDescriptors = when {
            memoryImprovement -> readCache(element, quick)
            else -> null
        }
        val foldingDescriptors = cachedDescriptors ?: collect(element)
        if (memoryImprovement && !quick && cachedDescriptors !== foldingDescriptors) {
            writeCache(element, foldingDescriptors)
        }
        return store.store(foldingDescriptors)
    }

    private fun isFoldingFile(element: PsiElement) =
        element.asInstance<PsiJavaFile>()?.name?.endsWith("-folded.java") == true

    context(editorDocument: Document)
    private fun readCache(
        element: PsiElement,
        quick: Boolean,
    ): Array<FoldingDescriptor>? {
        if (!quick) {
            (element as? PsiJavaFile)?.let { file ->
                if (!file.invalidateExpired(editorDocument, false)) {
                    return file.getUserData(Keys.FULL_CACHE)
                }
            }
        }
        return null
    }

    private fun writeCache(
        element: PsiElement,
        foldingDescriptors: Array<FoldingDescriptor>
    ) {
        (element as? PsiJavaFile)?.run {
            putUserData(Keys.FULL_CACHE, foldingDescriptors)
        }
    }

    context(editorDocument: Document)
    fun preview(element: PsiElement): List<String> {
        val groupIds = Sets.newIdentityHashSet<FoldingGroup>()
        return collect(element).map { descriptor ->
            descriptor.group?.let(groupIds::add)
            buildString {
                append(descriptor.range.substring(editorDocument.text))
                append(" => ")
                append(descriptor.placeholderText)
                append('[')
                append(groupIds.size)
                append("-")
                append(descriptor.group?.run {
                    toString().substringAfterLast(".")
                } ?: "null")
                append(']')
            }
        }
    }

    context(editorDocument: Document)
    private fun collect(
        element: PsiElement,
    ): Array<FoldingDescriptor> {
        //TODO: default list size based on file size
        val allDescriptors = Lists.newArrayListWithCapacity<FoldingDescriptor>(1_000)
        BuildExpressionExt.collectFoldRegionsRecursively(element, editorDocument, Sets.newIdentityHashSet(), allDescriptors)
        return allDescriptors.toTypedArray()
    }

    override fun getPlaceholderText(astNode: ASTNode) = null

    // TODO: Collapse everything by default but use these settings when actually building the folding descriptors
    override fun isCollapsedByDefault(astNode: ASTNode): Boolean {
        try {
            val element = astNode.psi
            val document = PsiDocumentManager.getInstance(astNode.psi.project).getDocument(astNode.psi.containingFile)
            if (document != null) {
                val expression = BuildExpressionExt.getNonSyntheticExpression(element, document)
                return expression != null && expression.isCollapsedByDefault
            }
        } catch (_: IndexNotReadyException) {
            return false
        }
        return false
    }

    private val debugFolding = false
}

var store: Storage = EmptyStorage
