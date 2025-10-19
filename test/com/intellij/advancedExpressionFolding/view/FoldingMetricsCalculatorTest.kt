package com.intellij.advancedExpressionFolding.view

import com.intellij.advancedExpressionFolding.metrics.FoldingDescriptorInfo
import com.intellij.advancedExpressionFolding.metrics.FoldingMetricsCalculator
import com.intellij.openapi.editor.impl.DocumentImpl
import com.intellij.openapi.util.TextRange
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FoldingMetricsCalculatorTest {
    @Test
    fun `calculates approximate savings from descriptors`() {
        val content = "line1\nline2\nline3"
        val document = DocumentImpl(content)

        val descriptors = listOf(
            FoldingDescriptorInfo(TextRange(0, 11), "..."),
            FoldingDescriptorInfo(TextRange(12, content.length), "l3")
        )

        val metrics = FoldingMetricsCalculator.calculate(document, descriptors)

        assertEquals(2, metrics.foldCount)
        assertEquals(1, metrics.linesSaved)
        assertEquals(11, metrics.charactersSaved)
    }
}
