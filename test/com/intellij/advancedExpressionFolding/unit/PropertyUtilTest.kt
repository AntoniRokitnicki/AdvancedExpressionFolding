package com.intellij.advancedExpressionFolding.unit

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
        assertEquals("iso3Country", guessPropertyName("getISO3Country"))
        assertEquals("iso3Language", guessPropertyName("getISO3Language"))
        assertEquals("urlValue", guessPropertyName("getURLValue"))
        assertEquals("htmlTag", guessPropertyName("getHTMLTag"))
    }
}
