package com.intellij.advancedExpressionFolding.processor.util

import com.intellij.openapi.editor.impl.DocumentImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HelperTest {

    @Test
    fun `findDot moves left to nearest delimiter`() {
        val text = "foo.bar"
        val document = DocumentImpl(text)

        val offset = Helper.findDot(document, text.indexOf('b'), -1, false)

        assertEquals(-1, offset)
    }

    @Test
    fun `findDot moves right to nearest delimiter`() {
        val text = "foo.bar.baz"
        val document = DocumentImpl(text)

        val offset = Helper.findDot(document, text.indexOf('r'), 1, false)

        assertEquals(1, offset)
    }

    @Test
    fun `findDot includes preceding newline when requested`() {
        val text = "foo\n    .bar"
        val document = DocumentImpl(text)

        val offset = Helper.findDot(document, text.indexOf('b'), -1, true)

        assertEquals(-6, offset)
        assertEquals('\n', document.charsSequence[text.indexOf('b') + offset])
    }

    @Test
    fun `findDot aborts when encountering non whitespace characters`() {
        val text = "foo->bar"
        val document = DocumentImpl(text)

        val offset = Helper.findDot(document, text.indexOf('b'), -1, false)

        assertEquals(Int.MAX_VALUE, offset)
    }
}
