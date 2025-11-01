package com.intellij.advancedExpressionFolding.view.sandbox

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DslSandboxEngineTest {

    private val engine = DslSandboxEngine()

    @Test
    fun `executes happy path script`() {
        val frames = engine.execute(
            """
            SET city NeoTokyo
            SET cycles 1
            INCR cycles 2
            APPEND log Boot
            DELETE city
            """.trimIndent()
        )

        assertEquals(6, frames.size)
        assertEquals("3", frames[3].snapshot["cycles"])
        assertEquals(null, frames.last().snapshot["city"])
        assertTrue(frames.none { it.isError })
    }

    @Test
    fun `stops on error and preserves prior state`() {
        val frames = engine.execute(
            """
            SET counter 1
            INCR counter nope
            SET counter 5
            """.trimIndent()
        )

        assertEquals(3, frames.size)
        assertTrue(frames.last().isError)
        assertEquals("1", frames.last().snapshot["counter"])
        assertTrue(frames.last().message.startsWith("Error:"))
    }
}
