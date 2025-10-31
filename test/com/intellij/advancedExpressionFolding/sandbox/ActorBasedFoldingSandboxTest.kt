package com.intellij.advancedExpressionFolding.sandbox

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ActorBasedFoldingSandboxTest {

    private var sandbox: ActorBasedFoldingSandbox? = null

    @AfterEach
    fun tearDown() {
        sandbox?.dispose()
    }

    @Test
    fun `preview request updates shared state`() = runBlocking {
        sandbox = ActorBasedFoldingSandbox(coroutineScope = this, foldingEngine = SandboxFoldingEngine { sample, rules ->
            SandboxPreview(
                rawText = sample,
                foldedText = sample.uppercase(),
                appliedRules = rules.toSortedMap(),
                metadata = mapOf("engine" to "test"),
            )
        })

        sandbox!!.loadSample("number + 1")
        sandbox!!.setRule("collapse.plus", true)
        sandbox!!.setRule("collapse.literals", true)

        val preview = sandbox!!.requestPreview()

        assertEquals("NUMBER + 1", preview.foldedText)
        assertEquals(2, preview.appliedRules.size)
        assertEquals(preview, sandbox!!.state.value.lastPreview)
        assertNull(sandbox!!.state.value.lastError)
    }

    @Test
    fun `listeners receive sequential snapshots including failures`() = runBlocking {
        sandbox = ActorBasedFoldingSandbox(coroutineScope = this, foldingEngine = SandboxFoldingEngine { sample, _ ->
            if (sample.contains("!") ) {
                throw IllegalStateException("boom")
            }
            SandboxPreview(
                rawText = sample,
                foldedText = sample.reversed(),
                appliedRules = emptyMap(),
            )
        })

        val versions = mutableListOf<Long>()
        sandbox!!.addListener(SandboxListener { state ->
            versions.add(state.version)
        })

        sandbox!!.loadSample("abc")
        sandbox!!.requestPreview()
        sandbox!!.loadSample("!")

        try {
            sandbox!!.requestPreview()
        } catch (expected: IllegalStateException) {
            // expected
        }

        assertTrue(versions.size >= 3)
        assertTrue(versions.zipWithNext().all { (left, right) -> right > left })
        val lastState = sandbox!!.state.value
        assertNotNull(lastState.lastError)
        assertEquals("!", lastState.sample)
        assertNull(lastState.lastPreview)
    }

    @Test
    fun `disabling rule removes it from state`() = runBlocking {
        sandbox = ActorBasedFoldingSandbox(coroutineScope = this, foldingEngine = SandboxFoldingEngine { sample, rules ->
            SandboxPreview(sample, sample, rules)
        })

        sandbox!!.setRule("feature.one", true)
        sandbox!!.setRule("feature.two", true)
        sandbox!!.setRule("feature.one", false)

        val ruleSnapshot = sandbox!!.state.value.rules
        assertEquals(setOf("feature.two"), ruleSnapshot.keys)

        val preview = sandbox!!.requestPreview(Dispatchers.Default)
        assertEquals("feature.two", preview.appliedRules.keys.single())
    }
}
