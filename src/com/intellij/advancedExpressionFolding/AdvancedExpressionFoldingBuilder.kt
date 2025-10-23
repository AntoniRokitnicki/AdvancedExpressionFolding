package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.adapter.storage.StorageRegistry
import com.intellij.advancedExpressionFolding.application.port.input.BuildFoldRegionsRequest
import com.intellij.advancedExpressionFolding.application.port.input.FoldingApplicationPort
import com.intellij.advancedExpressionFolding.application.port.input.PreviewFoldRegionsRequest
import com.intellij.advancedExpressionFolding.application.service.FoldingApplication
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement

class AdvancedExpressionFoldingBuilder(
    private val application: FoldingApplicationPort = FoldingApplication(storageProvider = StorageRegistry::current)
) : FoldingBuilderEx() {

    override fun buildFoldRegions(
        element: PsiElement,
        document: Document,
        quick: Boolean
    ): Array<FoldingDescriptor> {
        if (debugFolding) {
            application.preview(PreviewFoldRegionsRequest(element, document))
        }
        return application.build(BuildFoldRegionsRequest(element, document, quick))
    }

    override fun getPlaceholderText(astNode: ASTNode) = null

    override fun isCollapsedByDefault(astNode: ASTNode): Boolean = application.isCollapsedByDefault(astNode)

    private val debugFolding = false
}
