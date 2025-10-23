package com.intellij.advancedExpressionFolding.application.service

import com.google.common.collect.Lists
import com.google.common.collect.Sets
import com.intellij.advancedExpressionFolding.application.port.input.BuildFoldRegionsRequest
import com.intellij.advancedExpressionFolding.application.port.input.FoldingApplicationPort
import com.intellij.advancedExpressionFolding.application.port.input.PreviewFoldRegionsRequest
import com.intellij.advancedExpressionFolding.application.port.output.Storage
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.processor.cache.CacheExt.invalidateExpired
import com.intellij.advancedExpressionFolding.processor.cache.Keys
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings
import com.intellij.advancedExpressionFolding.settings.IConfig
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaFile

class FoldingApplication(
    private val configSupplier: () -> IConfig = { AdvancedExpressionFoldingSettings.getInstance().state },
    private val storageProvider: () -> Storage
) : FoldingApplicationPort {

    override fun build(request: BuildFoldRegionsRequest): Array<FoldingDescriptor> {
        val config = configSupplier()
        val storage = storageProvider()
        if (!config.globalOn || isFoldingFile(request.element)) {
            return storage.store(Expression.EMPTY_ARRAY, request.document)
        }

        val cachedDescriptors = if (config.memoryImprovement) {
            readCache(request.element, request.quick, request.document)
        } else {
            null
        }

        val foldingDescriptors = cachedDescriptors ?: collect(request.element, request.document)

        if (config.memoryImprovement && !request.quick && cachedDescriptors !== foldingDescriptors) {
            writeCache(request.element, foldingDescriptors)
        }

        return storage.store(foldingDescriptors, request.document)
    }

    override fun preview(request: PreviewFoldRegionsRequest): List<String> {
        val groupIds = Sets.newIdentityHashSet<FoldingGroup>()
        return collect(request.element, request.document).map { descriptor ->
            descriptor.group?.let(groupIds::add)
            buildString {
                append(descriptor.range.substring(request.document.text))
                append(" => ")
                append(descriptor.placeholderText)
                append('[')
                append(groupIds.size)
                append("-")
                append(descriptor.group?.run { toString().substringAfterLast('.') } ?: "null")
                append(']')
            }
        }
    }

    override fun isCollapsedByDefault(astNode: ASTNode): Boolean {
        return try {
            val element = astNode.psi
            val document = PsiDocumentManager.getInstance(astNode.psi.project).getDocument(astNode.psi.containingFile)
            if (document != null) {
                val expression = BuildExpressionExt.getNonSyntheticExpression(element, document)
                expression?.isCollapsedByDefault() == true
            } else {
                false
            }
        } catch (_: IndexNotReadyException) {
            false
        }
    }

    private fun readCache(
        element: PsiElement,
        quick: Boolean,
        document: Document
    ): Array<FoldingDescriptor>? {
        if (!quick) {
            (element as? PsiJavaFile)?.let { file ->
                if (!file.invalidateExpired(document, false)) {
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

    private fun collect(
        element: PsiElement,
        document: Document
    ): Array<FoldingDescriptor> {
        val allDescriptors = Lists.newArrayListWithCapacity<FoldingDescriptor>(DEFAULT_LIST_CAPACITY)
        BuildExpressionExt.collectFoldRegionsRecursively(element, document, Sets.newIdentityHashSet(), allDescriptors)
        return allDescriptors.toTypedArray()
    }

    private fun isFoldingFile(element: PsiElement): Boolean {
        return (element as? PsiJavaFile)?.name?.endsWith(FOLDED_FILE_SUFFIX) == true
    }

    companion object {
        private const val DEFAULT_LIST_CAPACITY = 1_000
        private const val FOLDED_FILE_SUFFIX = "-folded.java"
    }
}
