package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.openapi.editor.impl.DocumentImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DocumentUtilTest {

    @Test
    fun findDotSkipsWhitespaceWhenSearchingForward() {
        val content = "foo   .bar"
        val document = DocumentImpl(content)

        val startOffset = content.indexOf('o', startIndex = 2)
        val result = DocumentUtil.findDot(document, startOffset, 1, false)

        assertEquals(4, result)
    }

    @Test
    fun findDotReturnsOffsetWhenScanningBackward() {
        val content = "foo.bar"
        val document = DocumentImpl(content)

        val startOffset = content.indexOf('b')
        val result = DocumentUtil.findDot(document, startOffset, -1, false)

        assertEquals(-1, result)
    }

    @Test
    fun findDotStopsWhenNonWhitespaceEncountered() {
        val content = "fooX.bar"
        val document = DocumentImpl(content)

        val startOffset = content.indexOf('o', startIndex = 2)
        val result = DocumentUtil.findDot(document, startOffset, 1, false)

        assertEquals(Int.MAX_VALUE, result)
    }

    @Test
    fun findDotExtendsOffsetWhenIncludingTrailingWhitespace() {
        val content = "foo.\n  bar"
        val document = DocumentImpl(content)

        val startOffset = content.indexOf('o', startIndex = 2)
        val result = DocumentUtil.findDot(document, startOffset, 1, true)

        assertEquals(4, result)
    }
}
