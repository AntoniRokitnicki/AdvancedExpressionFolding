package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.diff.FoldedCode
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorEx
import com.intellij.advancedExpressionFolding.diff.FoldingDescriptorExWrapper

object FoldingTemporaryTestEditor {
    fun getFoldedText(
        text: String,
        wrapper: FoldingDescriptorExWrapper
    ): FoldedCode {
        val sanitizedText = removeFoldingMarkers(text)
        if (wrapper.list.isEmpty()) {
            return sanitizedText
        }

        val sortedDescriptors = wrapper.list.sortedWith(
            compareBy<FoldingDescriptorEx> { it.range.start }.thenBy { it.range.end }
        )

        val result = StringBuilder(sanitizedText.length)
        var offset = 0
        val limit = sanitizedText.length

        for (descriptor in sortedDescriptors) {
            val start = descriptor.range.start
            val end = descriptor.range.end
            if (start < offset || start < 0 || end > limit || start > end) {
                continue
            }
            result.append(sanitizedText, offset, start)
            result.append(descriptor.placeholder.orEmpty())
            offset = end
        }

        if (offset < limit) {
            result.append(sanitizedText, offset, limit)
        }

        return result.toString()
    }

    private fun removeFoldingMarkers(expectedContent: String): String {
        return expectedContent.replace(Regex("<$FOLD\\stext='[^']*'(\\sexpand='[^']*')*>"), "")
            .replace("</$FOLD>", "")
    }

    private const val FOLD = "fold"
}
