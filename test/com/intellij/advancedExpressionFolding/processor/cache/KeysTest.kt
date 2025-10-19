package com.intellij.advancedExpressionFolding.processor.cache

import com.intellij.advancedExpressionFolding.BaseTest
import com.intellij.openapi.application.runReadAction
import org.jetbrains.annotations.TestOnly
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@TestOnly
class KeysTest : BaseTest() {

    @Test
    fun validateAllKeysAreReflected() {
        val expected = setOf(
            Keys.BUILDER,
            Keys.CLASS_TYPE_KEY,
            Keys.FIELD_META_DATA_KEY,
            Keys.IGNORED,
            Keys.FIELD_KEY,
            Keys.FULL_CACHE,
            Keys.METHOD_TO_PARENT_CLASS_KEY,
            Keys.getKey(true),
            Keys.getKey(false),
            Keys.getVersionKey(true),
            Keys.getVersionKey(false),
        )

        val allKeys = runReadAction { Keys.allKeys }

        assertEquals(expected, allKeys)
    }
}
