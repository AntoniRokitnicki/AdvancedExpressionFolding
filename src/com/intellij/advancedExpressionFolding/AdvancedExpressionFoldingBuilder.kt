package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.application.FoldingApplicationServiceFactory
import com.intellij.advancedExpressionFolding.settings.AdvancedExpressionFoldingSettings.Companion.getInstance
import com.intellij.advancedExpressionFolding.settings.IConfig
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

class AdvancedExpressionFoldingBuilder(
    private val config: IConfig = getInstance().state,
) : FoldingBuilderEx(), IConfig by config {

    override fun buildFoldRegions(element: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val service = FoldingApplicationServiceFactory.create(config)
        if (debugFolding) {
            service.preview(element, document)
        }
        return service.buildFoldRegions(element, document, quick)
    }

    fun preview(element: PsiElement, document: Document): List<String> =
        FoldingApplicationServiceFactory.create(config).preview(element, document)

    override fun getPlaceholderText(astNode: ASTNode) = null

    override fun isCollapsedByDefault(astNode: ASTNode): Boolean =
        FoldingApplicationServiceFactory.create(config).isCollapsedByDefault(astNode)

    private val debugFolding = false
}
