package com.intellij.advancedExpressionFolding.view

import com.intellij.openapi.util.TextRange
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FoldingPreviewViewModelBuilderTest {

    @Test
    fun `groups entries by name and preserves ordering`() {
        val entries = listOf(
            FoldingPreviewEntry("GroupB", "b", "code b", TextRange(20, 25)),
            FoldingPreviewEntry("GroupA", "a1", "code a1", TextRange(0, 5)),
            FoldingPreviewEntry("GroupA", "a2", "code a2", TextRange(10, 15))
        )

        val groups = FoldingPreviewViewModelBuilder.group(entries)

        assertEquals(listOf("GroupA", "GroupB"), groups.map { it.name })
        assertEquals(listOf(TextRange(0, 5), TextRange(10, 15)), groups[0].entries.map { it.range })
    }

    @Test
    fun `empty groups when descriptors missing`() {
        val groups = FoldingPreviewViewModelBuilder.group(emptyList())
        assertEquals(emptyList<FoldingPreviewGroup>(), groups)
    }
}
