package com.intellij.advancedExpressionFolding.application

import com.intellij.advancedExpressionFolding.application.port.`in`.BuildFoldRegionsUseCase
import com.intellij.advancedExpressionFolding.application.port.`in`.PreviewFoldRegionsUseCase
import com.intellij.advancedExpressionFolding.application.port.`in`.ResolveCollapsedByDefaultUseCase
import com.intellij.advancedExpressionFolding.application.port.out.FoldingCache
import com.intellij.advancedExpressionFolding.application.port.out.Storage
import com.intellij.advancedExpressionFolding.domain.FoldRegionsCollector
import com.intellij.advancedExpressionFolding.expression.Expression
import com.intellij.advancedExpressionFolding.settings.IConfig
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

class FoldingApplicationService(
    private val config: IConfig,
    private val collector: FoldRegionsCollector,
    private val cache: FoldingCache,
    private val storage: Storage,
) : BuildFoldRegionsUseCase,
    PreviewFoldRegionsUseCase,
    ResolveCollapsedByDefaultUseCase {

    override fun buildFoldRegions(element: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        if (!config.globalOn || collector.isFoldingFile(element)) {
            return storage.store(Expression.EMPTY_ARRAY, document)
        }

        val cachedDescriptors = if (config.memoryImprovement) {
            cache.read(element, quick, document)
        } else {
            null
        }

        val foldingDescriptors = cachedDescriptors ?: collector.collect(element, document)
        if (config.memoryImprovement && !quick && cachedDescriptors !== foldingDescriptors) {
            cache.write(element, foldingDescriptors)
        }
        return storage.store(foldingDescriptors, document)
    }

    override fun preview(element: PsiElement, document: Document): List<String> =
        collector.preview(element, document)

    override fun isCollapsedByDefault(astNode: ASTNode): Boolean = collector.isCollapsedByDefault(astNode)
}
