package com.intellij.advancedExpressionFolding

import com.intellij.advancedExpressionFolding.processor.util.PropertyUtil.guessPropertyName
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PropertyUtilTest {
    @Test
    fun checkGuessPropertyName() {
        assertEquals("", guessPropertyName(""))
        assertEquals("", guessPropertyName("get"))
        assertEquals("", guessPropertyName("set"))
        assertEquals("", guessPropertyName("is"))
        assertEquals("length", guessPropertyName("length"))
        assertEquals("name", guessPropertyName("getName"))
        assertEquals("name", guessPropertyName("setName"))
        assertEquals("enabled", guessPropertyName("isEnabled"))
        assertEquals("enabledByDefault", guessPropertyName("isEnabledByDefault"))
        assertEquals("html", guessPropertyName("getHTML"))
        assertEquals("htmlText", guessPropertyName("isHTMLText"))
    }
}
