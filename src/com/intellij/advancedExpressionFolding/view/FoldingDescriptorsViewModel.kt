package com.intellij.advancedExpressionFolding.view

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.util.TextRange

data class FoldingPreviewEntry(
    val groupName: String,
    val placeholderText: String?,
    val codeSnippet: String,
    val range: TextRange
)

data class FoldingPreviewGroup(
    val name: String,
    val entries: List<FoldingPreviewEntry>
)

object FoldingPreviewViewModelBuilder {

    fun fromDescriptors(document: Document, descriptors: List<FoldingDescriptor>): List<FoldingPreviewGroup> {
        if (descriptors.isEmpty()) {
            return emptyList()
        }
        val text = document.text
        val entries = descriptors.map { descriptor ->
            FoldingPreviewEntry(
                descriptor.group.groupNameOrFeature(descriptor.placeholderText, descriptor.element?.javaClass?.simpleName),
                descriptor.placeholderText?.takeUnless { it.isBlank() },
                descriptor.range.substringSafe(text),
                descriptor.range
            )
        }
        return group(entries)
    }

    fun group(entries: List<FoldingPreviewEntry>): List<FoldingPreviewGroup> {
        if (entries.isEmpty()) {
            return emptyList()
        }
        return entries.groupBy { it.groupName }
            .map { (groupName, descriptors) ->
                val sorted = descriptors.sortedBy { it.range.startOffset }
                val anchor = sorted.firstOrNull()?.range?.startOffset ?: Int.MAX_VALUE
                anchor to FoldingPreviewGroup(groupName, sorted)
            }
            .sortedBy { it.first }
            .map { it.second }
    }

    private fun TextRange.substringSafe(text: CharSequence): String {
        val boundedStart = startOffset.coerceIn(0, text.length)
        val boundedEnd = endOffset.coerceIn(boundedStart, text.length)
        return text.subSequence(boundedStart, boundedEnd).toString()
    }

    private fun FoldingGroup?.groupNameOrFeature(placeholder: String?, featureName: String?): String {
        val rawGroup = this?.toString()?.takeIf { it.isNotBlank() }
        val simplifiedGroup = rawGroup?.substringAfterLast('.')
        return when {
            !simplifiedGroup.isNullOrBlank() -> simplifiedGroup
            !featureName.isNullOrBlank() -> featureName
            !placeholder.isNullOrBlank() -> placeholder
            else -> "Ungrouped"
        }
    }
}
