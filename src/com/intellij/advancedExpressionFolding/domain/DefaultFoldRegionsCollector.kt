package com.intellij.advancedExpressionFolding.domain

import com.google.common.collect.Lists
import com.google.common.collect.Sets
import com.intellij.advancedExpressionFolding.processor.asInstance
import com.intellij.advancedExpressionFolding.processor.core.BuildExpressionExt
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiJavaFile

class DefaultFoldRegionsCollector : FoldRegionsCollector {
    override fun collect(element: PsiElement, document: Document): Array<FoldingDescriptor> {
        val allDescriptors = Lists.newArrayListWithCapacity<FoldingDescriptor>(1_000)
        BuildExpressionExt.collectFoldRegionsRecursively(element, document, Sets.newIdentityHashSet(), allDescriptors)
        return allDescriptors.toTypedArray()
    }

    override fun preview(element: PsiElement, document: Document): List<String> {
        val groupIds = Sets.newIdentityHashSet<FoldingGroup>()
        return collect(element, document).map { descriptor ->
            descriptor.group?.let(groupIds::add)
            buildString {
                append(descriptor.range.substring(document.text))
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

    override fun isFoldingFile(element: PsiElement): Boolean =
        element.asInstance<PsiJavaFile>()?.name?.endsWith("-folded.java") == true

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
}
