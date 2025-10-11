package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.diff.FoldedCode
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorEx
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper

object FoldingTemporaryTestEditor {
    private const val FOLD = "fold"
    private val foldMarkerRegex = Regex("<${FOLD}\\stext='[^']*'(\\sexpand='[^']*')*>")

    fun getFoldedText(
        text: String,
        wrapper: FoldingDescriptorExWrapper
    ): FoldedCode {
        val content = removeFoldingMarkers(text)
        val builder = StringBuilder(content)
        val descriptors = wrapper.list.sortedWith(
            compareByDescending<FoldingDescriptorEx> { it.range.start }
                .thenByDescending { it.range.end }
        )
        for (descriptor in descriptors) {
            val placeholder = descriptor.placeholder ?: continue
            val start = descriptor.range.start
            val end = descriptor.range.end
            if (start <= end && start in 0..builder.length && end in 0..builder.length) {
                builder.replace(start, end, placeholder)
            }
        }
        return builder.toString()
    }

    private fun removeFoldingMarkers(expectedContent: String): String {
        return expectedContent.replace(foldMarkerRegex, "").replace("</${FOLD}>", "")
    }
}

