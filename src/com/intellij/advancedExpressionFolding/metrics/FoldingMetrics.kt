package com.intellij.advancedExpressionFolding.metrics

import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange

/**
 * Represents aggregated folding metrics for a file.
 */
data class FoldingMetrics(
    val foldCount: Int,
    val linesSaved: Int,
    val charactersSaved: Int
)

/**
 * Lightweight descriptor information required for computing folding metrics.
 */
data class FoldingDescriptorInfo(
    val textRange: TextRange,
    val placeholderText: String?
)

object FoldingMetricsCalculator {
    fun calculate(document: Document, descriptors: Array<out FoldingDescriptor>): FoldingMetrics {
        return calculate(
            document,
            descriptors.map { descriptor -> FoldingDescriptorInfo(descriptor.range, descriptor.placeholderText) }
        )
    }

    fun calculate(document: Document, descriptors: Collection<FoldingDescriptorInfo>): FoldingMetrics {
        if (descriptors.isEmpty()) {
            return FoldingMetrics(0, 0, 0)
        }

        var foldCount = 0
        var totalLinesSaved = 0
        var totalCharactersSaved = 0
        val content = document.charsSequence
        val textLength = document.textLength

        descriptors.forEach { info ->
            val range = info.textRange
            if (range.isEmpty || range.startOffset < 0 || range.endOffset > textLength || range.startOffset >= range.endOffset) {
                return@forEach
            }

            foldCount++

            val placeholder = info.placeholderText.orEmpty()
            val original = content.subSequence(range.startOffset, range.endOffset)
            val originalLineBreaks = original.count { it == '\n' }
            val placeholderLineBreaks = placeholder.count { it == '\n' }
            val lineDiff = originalLineBreaks - placeholderLineBreaks
            if (lineDiff > 0) {
                totalLinesSaved += lineDiff
            }

            val characterDiff = range.length - placeholder.length
            if (characterDiff > 0) {
                totalCharactersSaved += characterDiff
            }
        }

        return FoldingMetrics(foldCount, totalLinesSaved, totalCharactersSaved)
    }
}
